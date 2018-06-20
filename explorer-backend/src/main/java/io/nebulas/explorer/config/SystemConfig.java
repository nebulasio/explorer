package io.nebulas.explorer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * class for system configuration
 */
@Configuration
public class SystemConfig {

    private static final int FACTOR = 4;
    private static final int THREAD_ALIVE_SECONDS = 600;
    private static final int QUEUE_CAPACITY = 5000;
    @Bean
    public ThreadPoolTaskExecutor systemTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * FACTOR);
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * FACTOR);
        executor.setKeepAliveSeconds(THREAD_ALIVE_SECONDS);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("nebulas-task");
        executor.initialize();
        return executor;
    }
}
