package com.zat.easygo.cucumber.api.client.impl;

import com.zat.easygo.cucumber.api.client.EasyGoClient;
import com.zat.easygo.cucumber.api.request.GetUserProfileRequest;
import com.zat.easygo.cucumber.api.request.LogonRequest;
import com.zat.easygo.cucumber.api.request.LogonValidationRequest;
import com.zat.easygo.cucumber.api.request.PaymentByCPRequest;
import com.zat.easygo.cucumber.api.response.GetUserProfileResponse;
import com.zat.easygo.cucumber.api.response.LogonResponse;
import com.zat.easygo.cucumber.api.response.LogonValidationResponse;
import com.zat.easygo.cucumber.api.response.PaymentByCPResponse;
import com.zat.easygo.cucumber.domain.PaymentPayload;
import com.zat.easygo.cucumber.api.client.EasyGoClient;
import com.zat.easygo.cucumber.api.request.LogonRequest;
import com.zat.easygo.cucumber.api.request.LogonValidationRequest;
import com.zat.easygo.cucumber.api.request.PaymentByCPRequest;
import com.zat.easygo.cucumber.api.response.LogonResponse;
import com.zat.easygo.cucumber.api.response.LogonValidationResponse;
import com.zat.easygo.cucumber.api.response.PaymentByCPResponse;
import com.zat.easygo.cucumber.domain.PaymentPayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * This class demonstrates how to test a controller using Spring Boot Test
 * (what makes it much closer to a Integration Test)
 *
 * @author moises.macero
 */
@Component
public class EasyGoClientImpl implements EasyGoClient {
    
    protected TestRestTemplate restTemplate = new TestRestTemplate();
    
    @Value("${easygo.service.port}")
    protected int port;

    @Value("${easygo.service.url}")
    protected String SERVER_URL;

    @Value("${easygo.service.endpoint}")
    protected String ENDPOINT;

    private String getEndpoint() {
        return SERVER_URL + ":" + port + ENDPOINT;
    }
    
    public LogonResponse logon(LogonRequest request) {
        String url = getEndpoint()+String.format("?p=%s&u=%s&action=%s&lang=%s&platform=%s",
                request.getP(), request.getU(), request.getAction(), request.getLang(), request.getPlatform());
        ResponseEntity<LogonResponse> response = restTemplate.getForEntity(url, LogonResponse.class);
        return response.getBody();
    }
    
    public LogonValidationResponse validateToken(LogonValidationRequest request) {
        String url = getEndpoint()+String.format("?action=%s&lang=%s&platform=%s&sid=%s&mrt=%s",
                request.getAction(), request.getLang(), request.getPlatform(), request.getSid(), request.getMrt());
        ResponseEntity<LogonValidationResponse> response = restTemplate.getForEntity(url, LogonValidationResponse.class);
        return response.getBody();
    }

    public GetUserProfileResponse getUserProfile(GetUserProfileRequest request) {
        String url = getEndpoint()+String.format("?action=%s&lang=%s&platform=%s&sid=%s&mrt=%s",
                request.getAction(), request.getLang(), request.getPlatform(), request.getSid(), request.getMrt());
        ResponseEntity<GetUserProfileResponse> response = restTemplate.getForEntity(url, GetUserProfileResponse.class);
        return response.getBody();
    }

    public PaymentByCPResponse paymentByCP(PaymentByCPRequest request) {
        String url = getEndpoint()+String.format("?action=%s&lang=%s&platform=%s&sid=%s&mrt=%s",
                request.getAction(), request.getLang(), request.getPlatform(), request.getSid(), request.getMrt());
        HttpEntity<PaymentPayload> httpEntity = new HttpEntity<>(request.getPaymentByCP());
        ResponseEntity<PaymentByCPResponse> response = restTemplate.postForEntity(url, httpEntity, PaymentByCPResponse.class);
        return response.getBody();
    }
}