package com.spring.soapws.configurations;

import org.springframework.context.ApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

public class CustomMessageDispatcherServlet extends MessageDispatcherServlet {
    
    /**
     * <code>initStrategies</code> method, which is used to initialize the strategies of
     * the <code>MessageDispatcherServlet</code> overriden to set the <code>SoapMessageFactory</code> bean name.
     *
     * @param context the <code>ApplicationContext</code> to be injected and set to <code>MessageDispatcherServlet</code>,
     */
    @Override
    protected void initStrategies(ApplicationContext context) {
        super.initStrategies(context);
        setMessageFactoryBeanName("soapMessageFactory");
    }
    
}