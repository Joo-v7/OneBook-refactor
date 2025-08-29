package com.nhnacademy.taskapi.batch;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@SpringBatchTest
@TestPropertySource(properties = {
        "spring.batch.job.enabled=false",
        "spring.datasource.url=jdbc:mysql://localhost:3306/onebook_refactor?useSSL=false&serverTimezone=Asia/Seoul",
        "spring.datasource.username=root",
        "spring.datasource.password=20182229",
        "spring.jpa.hibernate.ddl-auto=none",
        "spring.batch.jdbc.initialize-schema=always"
})
class BirthdayCouponBatchTest {

    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job birthdayCouponJob;

    @Test
    void run_current_month_full() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("policyCode", "BIRTHDAY")
                .addLong("run.id", System.currentTimeMillis())
                .toJobParameters();

        JobExecution exec = jobLauncher.run(birthdayCouponJob, params);
        org.assertj.core.api.Assertions.assertThat(exec.getExitStatus())
                .isEqualTo(ExitStatus.COMPLETED);
    }
}

