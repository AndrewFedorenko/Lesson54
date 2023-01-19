package ua.levelup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ua.levelup.configuration.AppConfig;

@SpringBootApplication
@Import(AppConfig.class)
public class WebApplication {

    private static final Logger LOGGER = LogManager.getLogger(WebApplication.class);
    public static void main(String[] args) {
        LOGGER.debug("RUN WebApplication!");
        SpringApplication.run(WebApplication.class, args);
    }

}