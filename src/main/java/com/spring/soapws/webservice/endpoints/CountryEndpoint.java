package com.spring.soapws.webservice.endpoints;

import com.spring.soapws.generated.GetCountryRequest;
import com.spring.soapws.generated.GetCountryResponse;
import com.spring.soapws.webservice.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * The @Endpoint annotation registers the class with Spring WS as a potential candidate for processing incoming SOAP messages.
 */
@Endpoint
public class CountryEndpoint {
    
    // The namespace URI serves two purposes:
    // - it identifies the XML schema to be used for validating incoming messages, and
    // - it identifies the WSDL to be used for describing the web service.
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    
    private CountryRepository countryRepository;
    
    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    
    /**
     * The <code>getCountry</code> method is the endpoint method that will be invoked when a request with
     * the <code>localPart</code> <code>getCountryRequest</code>
     * is received (the <code>localPart</code> is the name of the root <code>XML</code> element in the incoming request)
     * and the namespace matches the value of the namespace attribute.
     * <p>
     * The <code>@PayloadRoot</code> annotation is then used by Spring WS to pick the handler method, based on
     * the message’s namespace and <code>localPart</code>.
     * The <code>@RequestPayload</code> annotation indicates that the incoming message will be mapped to
     * the method’s request parameter.
     * The <code>@ResponsePayload</code> annotation makes Spring WS map the returned value to the response payload.
     *
     * @param request the incoming message will be mapped to the method’s request parameter.
     * @return the returned value will be mapped to the response payload (the response message).
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
        
        return response;
    }
    
}