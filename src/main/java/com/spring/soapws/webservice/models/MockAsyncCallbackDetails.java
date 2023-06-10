package com.spring.soapws.webservice.models;

public class MockAsyncCallbackDetails {
    
    private final long timestamp = System.currentTimeMillis();
    
    private final String mockAsyncMessageXml;
    
    private final String mockRequestId;
    
    private final long delayInMilliseconds;
    
    
    public MockAsyncCallbackDetails(String mockAsyncMessageXml, String mockRequestId, long delayInMilliseconds) {
        this.mockAsyncMessageXml = mockAsyncMessageXml;
        this.mockRequestId = mockRequestId;
        this.delayInMilliseconds = delayInMilliseconds;
    }
    
    
    public String getMockAsyncMessageXml() {
        return mockAsyncMessageXml;
    }
    
    public String getMockRequestId() {
        return mockRequestId;
    }
    
    public long getDelayInMilliseconds() {
        return delayInMilliseconds;
    }
    
}
