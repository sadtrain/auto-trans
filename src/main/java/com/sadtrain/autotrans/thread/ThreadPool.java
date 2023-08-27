package com.sadtrain.autotrans.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class ThreadPool {

    @Bean
    public ExecutorService defaultExecutorService() {
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
        return new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),handler);
    }

}
