package com.nhnacademy.taskapi.batch;

import com.nhnacademy.taskapi.coupon.domain.entity.coupons.Coupon;
import com.nhnacademy.taskapi.coupon.domain.entity.coupons.IssuedCoupon;
import com.nhnacademy.taskapi.coupon.domain.entity.policies.PricePolicyForBook;
import com.nhnacademy.taskapi.coupon.domain.entity.status.CouponStatus;
import com.nhnacademy.taskapi.coupon.domain.entity.status.PolicyStatus;
import com.nhnacademy.taskapi.coupon.repository.coupons.CouponRepository;
import com.nhnacademy.taskapi.coupon.repository.policies.PricePoliciesForBookRepository;
import com.nhnacademy.taskapi.coupon.repository.status.CouponStatusRepository;
import com.nhnacademy.taskapi.coupon.repository.status.PolicyStatusRepository;
import com.nhnacademy.taskapi.member.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Configuration
class BirthdayCouponBatchConfig extends DefaultBatchConfiguration {

    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext private EntityManager entityManager;

    // 정책/상태/카테고리 레포
    private final CouponStatusRepository couponStatusRepository;
    private final PolicyStatusRepository policyStatusRepository;
    private final CouponRepository couponRepository;
    private final PricePoliciesForBookRepository pricePoliciesForBookRepository;

    private static final int CHUNK_SIZE = 1000;

   // birthday Job
    @Bean
    public Job birthdayCouponJob(
            JobRepository jobRepository,
            Step createBirthdayPolicyStep,
            Step issueBirthdayCouponStep) {
        return new JobBuilder("birthdayCouponJob", jobRepository)
                .start(createBirthdayPolicyStep) // 이번달 생일 쿠폰 정책 생성
                .next(issueBirthdayCouponStep) // 생일인 회원에게 쿠폰 발급
                .build();
    }

    // Step - 이번달에 사용할 생일 쿠폰 정책 생성
    @Bean
    public Step createBirthdayPolicyStep(JobRepository repo, PlatformTransactionManager transactionManager) {
        return new StepBuilder("createBirthdayPolicyStep", repo)
                // Spring Batch 5: tasklet(Tasklet, TX) 사용 + 반드시 RepeatStatus 반환
                .tasklet((contribution, chunkContext) -> {
                    PolicyStatus policyStatus = policyStatusRepository.findByName("use");

                    // 시간/날짜
                    ZoneId korea = ZoneId.of("Asia/Seoul");
                    YearMonth ym = YearMonth.now(korea);
                    LocalDateTime now = LocalDateTime.now(korea);
                    LocalDateTime monthStart = ym.atDay(1).atStartOfDay();
                    LocalDateTime monthEnd = ym.atEndOfMonth().atTime(23, 59, 59);

                    // 생일 쿠폰 정책 생성
                    PricePolicyForBook pricePolicyForBook = PricePolicyForBook.birthdayPricePolicyForBook(
                            5000,
                            5000,
                            monthStart,
                            monthEnd,
                            now.getDayOfYear() + " Birthday Coupon Price Policy",
                            "생일 쿠폰 정책",
                            policyStatus
                    );

                    entityManager.persist(pricePolicyForBook);

                    ExecutionContext executionContext = chunkContext.getStepContext()
                            .getStepExecution().getJobExecution().getExecutionContext();

                    // 발급된 생일 쿠폰 정책 이름을 ExecutionContext에 저장
                    executionContext.putString("pricePolicyForBookName", pricePolicyForBook.getName());

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    // Step - 생일인 회원에게 쿠폰 발급
    @Bean
    public Step issueBirthdayCouponStep(
            JobRepository repo,
            PlatformTransactionManager platformTransactionManager,
            JpaPagingItemReader<Member> birthdayMemberReader,
            ItemProcessor<Member, IssuedCoupon> birthdayIssueProcessor,
            ItemWriter<IssuedCoupon> birthdayIssueWriter
    ) {
        return new StepBuilder("issueBirthdayCouponStep", repo)
                .<Member, IssuedCoupon>chunk(CHUNK_SIZE, platformTransactionManager)
                .reader(birthdayMemberReader)
                .processor(birthdayIssueProcessor)
                .writer(birthdayIssueWriter)
                .build();
    }

    // reader - 생일인 멤버 읽기
    @Bean
    @StepScope
    public JpaPagingItemReader<Member> birthdayMemberReader() {
        return new JpaPagingItemReaderBuilder<Member>()
                .name("birthdayMemberReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(CHUNK_SIZE)
                .queryString("SELECT m FROM Member m WHERE MONTH(m.dateOfBirth) = MONTH(:today)")
                .parameterValues(Collections.singletonMap("today", LocalDate.now()))
                .build();
    }

    // processor - 쿠폰 생성 후 멤버에게 쿠폰 발급
    @Bean
    @StepScope
    public ItemProcessor<Member, IssuedCoupon> birthdayIssueProcessor(@Value("#{jobExecutionContext['pricePolicyForBookName']}") String policyName) {
        return member -> {
            // 생일 쿠폰 정책에 따른 생일 쿠폰 생성
            CouponStatus couponStatus = couponStatusRepository.findByName("issued");
            PricePolicyForBook pricePolicyForBook = pricePoliciesForBookRepository.findByName(policyName);

            Coupon coupon = Coupon.createPriceCouponForBook(
                    pricePolicyForBook,
                    couponStatus,
                    LocalDateTime.now()
            );

            // 멤버에게 쿠폰 발급
            return IssuedCoupon.createIssuedCoupon(coupon, member);
        };

    }

    // writer - 영속화
    @Bean
    public ItemWriter<IssuedCoupon> birthdayIssueWriter() {
        return issuedCoupons -> {
            // 발급된 쿠폰 저장 로직.
            for (IssuedCoupon ic : issuedCoupons) {
                entityManager.persist(ic.getCoupon());
                entityManager.persist(ic);
            }
        };
    }
}
