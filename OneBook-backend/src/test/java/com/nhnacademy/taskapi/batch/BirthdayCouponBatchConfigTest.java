package com.nhnacademy.taskapi.batch;

import com.nhnacademy.taskapi.coupon.domain.entity.status.CouponStatus;
import com.nhnacademy.taskapi.coupon.domain.entity.status.PolicyStatus;
import com.nhnacademy.taskapi.coupon.repository.status.CouponStatusRepository;
import com.nhnacademy.taskapi.coupon.repository.status.PolicyStatusRepository;
import com.nhnacademy.taskapi.grade.domain.Grade;
import com.nhnacademy.taskapi.grade.repository.GradeRepository;
import com.nhnacademy.taskapi.member.domain.Member;
import com.nhnacademy.taskapi.member.repository.MemberRepository;
import com.nhnacademy.taskapi.roles.domain.Role;
import com.nhnacademy.taskapi.roles.repository.RoleRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Slf4j
@SpringBootTest
@SpringBatchTest
@TestPropertySource(properties = {
        "spring.batch.job.enabled=false"
})
@Sql(
        scripts = {
                "classpath:org/springframework/batch/core/schema-drop-h2.sql", // 있으면 드롭
                "classpath:org/springframework/batch/core/schema-h2.sql"       // 생성
        },
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class BirthdayCouponBatchConfigTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private CouponStatusRepository couponStatusRepository;

    @Autowired
    private PolicyStatusRepository policyStatusRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job birthdayCouponJob;

    @BeforeEach
    public void setUp() {
        // 배치 테스트용 멤버 생성
        Grade grade = Grade.create("ROYAL", 10, "일반 등급");
        Role role = Role.createRole("MEMBER", "일반 회원");

        Grade savedGrade = gradeRepository.save(grade);
        Role savedRole = roleRepository.save(role);

        YearMonth ym = YearMonth.now();
        int thisMonth = ym.getMonthValue();
        int nextMonth = ym.plusMonths(1).getMonthValue();
        int prevMonth = ym.minusMonths(1).getMonthValue();

        // 이번 달 3명, 다른 달 2명
        Member m1 = Member.createNewMember(savedGrade, "김주혁1", "u1", "pw", LocalDate.of(1990, thisMonth, 10), Member.Gender.M, "u1@test.com", "01011110001", savedRole);
        Member m2 = Member.createNewMember(savedGrade, "김주혁2", "u2", "pw", LocalDate.of(1991, thisMonth, 15), Member.Gender.M, "u2@test.com", "01011110002", savedRole);
        Member m3 = Member.createNewMember(savedGrade, "김주혁3", "u3", "pw", LocalDate.of(1992, thisMonth, 20), Member.Gender.M, "u3@test.com", "01011110003", savedRole);
        Member m4 = Member.createNewMember(savedGrade, "아무개4", "u4", "pw", LocalDate.of(1993, nextMonth, 5),  Member.Gender.M, "u4@test.com", "01011110004", savedRole);
        Member m5 = Member.createNewMember(savedGrade, "아무개5", "u5", "pw", LocalDate.of(1994, prevMonth, 25), Member.Gender.M, "u5@test.com", "01011110005", savedRole);

        m1.setStatus(Member.Status.ACTIVE);
        m2.setStatus(Member.Status.ACTIVE);
        m3.setStatus(Member.Status.ACTIVE);
        m4.setStatus(Member.Status.ACTIVE);
        m5.setStatus(Member.Status.ACTIVE);

        memberRepository.saveAll(List.of(m1, m2, m3, m4, m5));

        // 쿠폰 정책 & 쿠폰 생성을 위한 준비
        CouponStatus couponStatus = new CouponStatus("issued");
        PolicyStatus policyStatus = new PolicyStatus("use");

        couponStatusRepository.save(couponStatus);
        policyStatusRepository.save(policyStatus);
    }

    @Test
    @DisplayName("birthdayCouponJob 실행")
    void birthdayCouponJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("test", System.currentTimeMillis())
                .toJobParameters();

        JobExecution execution = jobLauncher.run(birthdayCouponJob, jobParameters);

        Assertions.assertThat(execution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        Long issuedCount = entityManager.createQuery("select count(ic) from IssuedCoupon ic", Long.class)
                .getSingleResult();

        Assertions.assertThat(issuedCount).isEqualTo(3L);

    }


}