package com.nhnacademy.taskapi.config.log;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class QueryCountLoggingFilter implements Filter {

    private static final String QUERY_COUNT_LOG_FORMAT = "STATUS_CODE: {}, METHOD: {}, URL: {}, TIME: {}초, QUERY_COUNT: {}";
    private static final String QUERY_COUNT_WARNING_LOG_FORMAT = "하나의 요청에 쿼리가 10번 이상 날라갔습니다. 쿼리 횟수 : {}";
    private static final int QUERY_COUNT_WARNING_STANDARD = 10;

    private final QueryCountInspector queryCountInspector;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        queryCountInspector.startCounter();
        long startTime = System.currentTimeMillis();
        try {
            chain.doFilter(request, response); // 실제 요청 처리
        } finally {
            QueryCountInspector.Counter counter = queryCountInspector.getQueryCount();
            long queryCount = counter != null ? counter.getCount() : -1;
            double duration = (System.currentTimeMillis() - counter.getTime()) / 1000.0;

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            log.info(QUERY_COUNT_LOG_FORMAT, httpResponse.getStatus(), httpRequest.getMethod(), httpRequest.getRequestURI(), duration, queryCount);
            if (queryCount >= QUERY_COUNT_WARNING_STANDARD) {
                log.warn(QUERY_COUNT_WARNING_LOG_FORMAT, queryCount);
            }
            queryCountInspector.clearCounter();
        }
    }
}