package io.nebulas.explorer;

import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.service.SysService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
public class ExplorerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExplorerApplication.class, args);
    }

    @Bean
    CommandLineRunner init(final YAMLConfig myConfig, final SysService sysService) {

        return arg -> {
            log.info("using environment: {}", myConfig.getEnvironment());
            log.info("page: {}", myConfig.getPage());

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.schedule(() -> sysService.init(), 1500L, TimeUnit.MILLISECONDS);
        };

    }
}
