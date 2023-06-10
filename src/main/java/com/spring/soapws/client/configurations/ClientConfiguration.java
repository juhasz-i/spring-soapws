package com.spring.soapws.client.configurations;

import com.spring.soapws.client.CountryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientConfiguration {
    
    @Bean
    public Jaxb2Marshaller marshaller() {
        
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // It is the package where the generated classes are located and must match the package name in the pom.xml file.
        marshaller.setContextPath("com.spring.soapws.generated");
        
        return marshaller;
    }
    
    /**
     * The {@code countryClient} method creates an instance of {@code CountryClient} and sets its default URI.
     *
     * @param marshaller the {@code Jaxb2Marshaller} instance.
     * @return the {@code CountryClient} instance which is used to call the SOAP web service.
     */
    @Bean
    public CountryClient countryClient(Jaxb2Marshaller marshaller) {
        
        CountryClient client = new CountryClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        
        return client;
    }
    
}