package com.spring.soapws.webservice.repositories;

public interface iMockMessagingRepository {
    
    void enqueueMockAsyncMessage(String mockAsyncMessageXml, String mockRequestId, long delayInMilliseconds);
    
    
    
}
