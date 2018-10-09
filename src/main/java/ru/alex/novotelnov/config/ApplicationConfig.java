package ru.alex.novotelnov.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Description:
 *      defined shared resources visible to all other web component
 */
@Configuration
@ComponentScan(basePackages="ru.alex.novotelnov")
@Import(value={
        DataAccessConfig.class,
        WebMvcConfig.class,
        WebFlowConfig.class
})
public class ApplicationConfig {

}
