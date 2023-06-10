package com.spring.soapws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <code>@SpringBootApplication</code> is a convenience annotation that adds all of the following:
 * <ul>
 * <li><code>@Configuration</code>: Tags the class as a source of bean definitions for the application context.
 * <li><code>@EnableAutoConfiguration</code>: Tells Spring Boot to start adding beans based on classpath settings,
 * other beans, and various property settings.
 * For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and
 * activates key behaviors, such as setting up a DispatcherServlet.
 * <li><code>@ComponentScan</code>: Tells Spring to look for other components, configurations, and services in
 * the <code>com/example package</code>, letting it find the controllers.
 * </ul>
 */
@SpringBootApplication
public class SpringSoapwsApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringSoapwsApplication.class, args);
    }
    
}
