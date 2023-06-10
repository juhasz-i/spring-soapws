package com.spring.soapws.client;

import com.spring.soapws.generated.GetCountryRequest;
import com.spring.soapws.generated.GetCountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * The {@code CountryClient} extends {@code WebServiceGatewaySupport} which provides the
 * base class for the creation of web service client.
 */
public class CountryClient extends WebServiceGatewaySupport {
    
    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);
    
    
    /**
     * This method calls the web service and returns the response.
     * <p>
     * It uses the {@code WebServiceTemplate} supplied by the {@code WebServiceGatewaySupport} class to do the
     * actual SOAP exchange and marshals a request object to XML and unmarshal the response to an object again.
     * <p>
     * It passes the  {@code GetCountryRequest} request object (as well as a {@code SoapActionCallback} to pass
     * on a {@code SOAPAction} header with the request) as the WSDL described that it needed this header
     * in the <soap:operation/> elements.
     *
     * @param country country name.
     * @return {@code GetCountryResponse} which is the response from the web service.
     */
    public GetCountryResponse getCountry(String country) {
        
        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);
        
        log.info("Requesting location for " + country);
        
        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(
                "http://localhost:8080/ws/countries",
                request,
                new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/GetCountryRequest")    // The SoapActionCallback sets the SOAPAction header on the outgoing request.
        );
        
        return response;
    }
    
}