package com.spring.soapws.configurations;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * The <code>WebServiceConfig</code> class is annotated with <code>@EnableWs</code> that
 * enables SOAP Web Service features in the application, such as the use of the <code>@Endpoint</code> annotation.
 * <p>
 * The <code>@Configuration</code> annotation indicates that the class can be used by
 * the Spring IoC container as a source of bean definitions.
 * The <code>WsConfigurerAdapter</code> class implements the <code>WsConfigurer</code> interface, and
 * it provides default implementations of several methods.
 * <p>
 * You need to specify bean names for <code>MessageDispatcherServlet</code> and <code>DefaultWsdl11Definition</code>.
 * Bean names determine the URL under which the web service and the generated WSDL file are available.
 * In this case, the WSDL will be available under <code>http://&#60;host&#62;:&#60;port&#62;/ws/countries.wsdl</code>.
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    
    /**
     * <p>
     * Spring WS uses a different servlet type for handling SOAP messages: <code>MessageDispatcherServlet</code>.
     * <p>
     * It is important to inject and set <code>ApplicationContext</code> to <code>MessageDispatcherServlet</code>.<br>
     * Without that, Spring WS will not automatically detect Spring beans.
     * <p>
     * Naming this bean <code>messageDispatcherServlet</code> does not replace Spring Boot’s default <code>DispatcherServlet</code> bean.
     * Instead, it defines a second servlet with the name <code>messageDispatcherServlet</code> and the URL mapping /ws/*.
     *
     * @param applicationContext the <code>ApplicationContext</code> to be injected and set to <code>MessageDispatcherServlet</code>,
     *                           by which Spring WS will automatically detect Spring beans.
     * @return the <code>ServletRegistrationBean</code> instance, which is used to register the <code>MessageDispatcherServlet</code>.
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        // It does the WSDL location servlet transformation.
        // If you visit http://localhost:8080/ws/countries.wsdl, the soap:address will have the proper address.
        // If you instead visit the WSDL from the public facing IP address assigned to your machine, you will see that address instead.
        // This is useful when you want to publish your WSDL to a public facing IP address, but you want the service to be available on localhost.
        servlet.setTransformWsdlLocations(true);
        
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }
    
    /**
     * <code>DefaultWsdl11Definition</code> exposes a standard WSDL 1.1 by using XsdSchema.
     *
     * @param countriesSchema
     * @return
     */
    @Bean(name = "countries")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
        
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
        wsdl11Definition.setSchema(countriesSchema);
        
        return wsdl11Definition;
    }
    
    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
    }
    
}