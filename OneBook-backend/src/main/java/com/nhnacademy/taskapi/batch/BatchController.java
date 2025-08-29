package com.nhnacademy.taskapi.batch;

import com.nhnacademy.taskapi.aop.TimeTrace;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/batch/test")
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job birthdayCouponJob;

    @TimeTrace
    @GetMapping
    public ResponseEntity<String> test() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("run", System.currentTimeMillis()) // 매번 새 인스턴스로 돌리기 위함
                .toJobParameters();

        var exe = jobLauncher.run(birthdayCouponJob, params);

        log.info("Birthday Batch Started");
        return ResponseEntity.ok("status=" + exe.getStatus());
    }
}
