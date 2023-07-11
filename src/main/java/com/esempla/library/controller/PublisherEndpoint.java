package com.esempla.library.controller;

import com.esempla.library.repository.PublisherRepository;
import com.esempla.library.service.PublisherService;
import com.library.spring.soap.api.soap.GetPublisherRequest;
import com.library.spring.soap.api.soap.GetPublisherResponse;
import com.esempla.library.model.Publisher;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PublisherEndpoint {
    private static final String NAMESPACE_URI = "http://www.library.com/spring/soap/api/soap";
    private final PublisherService publisherService;

    public PublisherEndpoint(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPublisherRequest")
    @ResponsePayload
    public GetPublisherResponse getCountry(@RequestPayload GetPublisherRequest request) {
        GetPublisherResponse response = new GetPublisherResponse();
        Publisher publisher=publisherService.getById(request.getId());
        com.library.spring.soap.api.soap.Publisher publisher2=new com.library.spring.soap.api.soap.Publisher();
        publisher2.setId(publisher.getId());
        publisher2.setName(publisher.getName());
        response.setPublisher(publisher2);
        return response;
    }
}