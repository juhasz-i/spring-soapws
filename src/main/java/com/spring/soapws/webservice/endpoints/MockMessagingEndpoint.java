package com.spring.soapws.webservice.endpoints;

import com.spring.soapws.generated.*;
import com.spring.soapws.webservice.repositories.MockMessagingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MockMessagingEndpoint {
    
    private static final String NAMESPACE_URI = "urn:com.namespace.mock";
    
    private final Logger log = LoggerFactory.getLogger(MockMessagingEndpoint.class);
    
    private final ObjectFactory objectFactory = new ObjectFactory();
    
    private MockMessagingRepository mockMessagingRepository;
    
    
    @Autowired
    public MockMessagingEndpoint(MockMessagingRepository mockMessagingRepository) {
        this.mockMessagingRepository = mockMessagingRepository;
    }
    
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "mockSoapExchange")
    @ResponsePayload
    public OperationResponse getMockMessage(@RequestPayload MockSoapExchange request) {
        
        log.info(request.getMockRequest().getValue());
        log.info(request.getAsyncResponse().getValue());
        
        mockMessagingRepository.enqueueMockAsyncMessage(
                request.getAsyncResponse().getValue(),
                request.getMockRequest().getId(),
                request.getAsyncResponse().getDelay()
        );
        
        OperationResponse response = objectFactory.createOperationResponse();
        response.setAcknowledge(request.getMockRequest().getAcknowledge());
        
        log.info(response.getAcknowledge());
        
        return response;
    }
    
}
