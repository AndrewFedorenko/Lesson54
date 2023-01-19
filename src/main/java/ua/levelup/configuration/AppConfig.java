package ua.levelup.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;
@Configuration
@ComponentScan("ua.levelup.controllers")
public class AppConfig {
    @Bean
    public DataSource dataSource(
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password,
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.driver-class-name}") String dbDriver) {

        return DataSourceBuilder.create().username(username).password(password).url(url).driverClassName(dbDriver).build();
    }
}
