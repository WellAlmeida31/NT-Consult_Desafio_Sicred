package br.com.ntconsult.assembleia.infrastructure.web.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

    @Value(value = "${async.context.thread-name-prefix}")
    private String DEFAULT_POOL;
    @Value(value = "${async.context.core-pool-size}")
    private int CORE_POOL_SIZE;
    @Value(value = "${async.context.max-pool-size}")
    private int MAX_POOL_SIZE;
    @Value("#{systemProperties['async.context.queue-capacity'] ?: T(Integer).MAX_VALUE}")
    private int QUEUE_CAPACITY;

    public Executor getExecutor(String prefixThreadName, int threads, int maxSize, int queueCapacity){
        final ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(threads);
        pool.setMaxPoolSize(maxSize);
        pool.setQueueCapacity(queueCapacity);
        pool.setAllowCoreThreadTimeOut(true);
        pool.setThreadNamePrefix(prefixThreadName + "-");
        pool.initialize();
        return pool;
    }

    @Override
    public Executor getAsyncExecutor(){
        return getExecutor(DEFAULT_POOL, CORE_POOL_SIZE, MAX_POOL_SIZE, QUEUE_CAPACITY);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler(){
        return new SimpleAsyncUncaughtExceptionHandler();
    }

}
