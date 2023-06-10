package com.spring.soapws.webservice.repositories;

import com.spring.soapws.webservice.models.MockAsyncCallbackDetails;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MockMessagingRepository implements iMockMessagingRepository {
    
    private final List<MockAsyncCallbackDetails> mockAsyncCallbacks = new ArrayList<>();
    
    @PostConstruct
    public void initData() {
        
    }
    
    public void enqueueMockAsyncMessage(String mockAsyncMessageXml, String mockRequestId, long delayInMilliseconds) {
        mockAsyncCallbacks.add(new MockAsyncCallbackDetails(
                mockAsyncMessageXml,
                mockRequestId,
                delayInMilliseconds
        ));
    }
    

    
}
