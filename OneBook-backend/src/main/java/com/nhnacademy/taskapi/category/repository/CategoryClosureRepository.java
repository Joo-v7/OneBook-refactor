package com.nhnacademy.taskapi.category.repository;

import com.nhnacademy.taskapi.category.domain.Category;
import com.nhnacademy.taskapi.category.domain.CategoryClosure;
import com.nhnacademy.taskapi.category.domain.CategoryClosureId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryClosureRepository extends JpaRepository<CategoryClosure, CategoryClosureId> {

    // 서브트리 조회 (루트 포함)
    @Query("""
        select d from Category d
        join CategoryClosure cc on cc.descendantId = d.categoryId
        where cc.ancestorId = :rootId
        order by cc.depth asc, d.name asc
        """)
    List<Category> findSubtree(@Param("rootId") int rootId);

    // 조상 경로 조회 (루트 -> 대상, 대상 자신 포함)
    @Query("""
        select a from Category a
        join CategoryClosure cc on cc.ancestorId = a.categoryId
        where cc.descendantId = :nodeId
        order by cc.depth asc
        """)
    List<Category> findAncestors(@Param("nodeId") int nodeId);

    // 새 노드: 자기 자신 경로(depth=0)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = """
        insert into category_closure(ancestor_id, descendant_id, depth)
        values (:n, :n, 0)
        """, nativeQuery = true)
    void insertSelf(@Param("n") int newId);

    // 새 노드: 부모 체인의 모든 조상을 연결
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = """
        insert into category_closure(ancestor_id, descendant_id, depth)
        select a.ancestor_id, :n, a.depth + 1
        from category_closure a
        where a.descendant_id = :p
        """, nativeQuery = true)
    void linkToParentChain(@Param("n") int newId, @Param("p") int parentId);

    // 이동: 기존 조상 경로 제거 (자기 자신(depth=0) 보존)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = """
    delete cc
    from category_closure cc
    join (
        select descendant_id
        from category_closure
        where ancestor_id = :n
    ) s on cc.descendant_id = s.descendant_id
    join (
        select ancestor_id
        from category_closure
        where descendant_id = :n
          and ancestor_id <> :n     -- 자기자신 제외
    ) a on cc.ancestor_id = a.ancestor_id
    """, nativeQuery = true)
    void unlinkOldAncestors(@Param("n") int nodeId);

    // 이동: 새 부모 체인 × 서브트리 연결 (CTE 제거)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = """
    insert into category_closure(ancestor_id, descendant_id, depth)
    select pa.ancestor_id, s.descendant_id, pa.depth + s.depth + 1
    from category_closure pa,
         (select descendant_id, depth
          from category_closure
          where ancestor_id = :n) as s
    where pa.descendant_id = :np
    """, nativeQuery = true)
    void linkToNewParentChain(@Param("n") int nodeId, @Param("np") int newParentId);

    // 헤더에 들어갈 카테고리용: 루트 + 루트의 직계만
    @Query(value = """
    select 
        r.category_id   as root_id,
        r.name          as root_name,
        r.status        as root_status,
        c.category_id   as child_id,
        c.name          as child_name,
        c.status        as child_status
    from categories r
    left join category_closure cc 
        on cc.ancestor_id = r.category_id 
       and cc.depth = 1
    left join categories c 
        on c.category_id = cc.descendant_id
    where r.category_parent_id is null
    order by r.name asc, c.name asc
    """, nativeQuery = true)
    List<Object[]> findHeaderCategories();
}
