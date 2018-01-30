package io.nebulas.explorer;

import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.service.LeaderWrapper;
import io.nebulas.explorer.service.SysService;
import io.nebulas.explorer.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-22
 */
@Slf4j
@SpringBootApplication
@EnableScheduling
public class ExplorerApplication {

    @Autowired
    private String applicationId;

    public static void main(String[] args) {
        SpringApplication.run(ExplorerApplication.class, args);
    }

    @Bean
    CommandLineRunner init(final YAMLConfig myConfig, final SysService sysService, final LeaderWrapper leaderWrapper) {

        return arg -> {
            log.info("using environment: {}", myConfig.getEnvironment());

            if (leaderWrapper.tryToAcquireLock()) {
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                scheduler.schedule(() -> sysService.init(), 1500L, TimeUnit.MILLISECONDS);
            }
        };

    }

    @Bean
    public String applicationId() {
        return IdGenerator.getId();
    }

    @PreDestroy
    void destroy() {
        log.info("destroying application {} ....", applicationId);
    }

}
