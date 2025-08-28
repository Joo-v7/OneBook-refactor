package com.nhnacademy.taskapi.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

import static java.time.YearMonth.now;

@Slf4j
@RequiredArgsConstructor
@Component
public class BirthdayCouponScheduler {

    private final JobLauncher jobLauncher;
    private final Job birthdayCouponJob;

    @Scheduled(cron = "0 5 0 1 * *", zone = "Asia/Seoul")
    public void birthdayCoupon() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        var ym = now(ZoneId.of("Asia/Seoul"));

        var params = new JobParametersBuilder()
                .addString("policyCode", "BIRTHDAY")
                .addString("issueYearMonth", ym.toString()) // e.g. 2025-08
                .addLong("ts", System.currentTimeMillis())  // 재실행 구분자
                .toJobParameters();

        jobLauncher.run(birthdayCouponJob, params);
    }
}
