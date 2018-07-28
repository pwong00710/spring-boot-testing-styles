package com.zat.easygo.cucumber.common;

import com.zat.easygo.cucumber.api.client.EasyGoClient;
import com.zat.easygo.cucumber.api.request.LogonRequest;
import com.zat.easygo.cucumber.api.response.LogonResponse;
import com.zat.easygo.cucumber.api.response.LogonValidationResponse;
import com.zat.easygo.cucumber.api.response.LogonResponse;
import com.zat.easygo.cucumber.api.response.LogonValidationResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Scope("cucumber-glue")
@Component
@Data
public class StepSharedVariables {
    @Autowired
    EasyGoClient client;
    
    List<LogonRequest> logonRequests = new ArrayList<>();
    List<LogonResponse> logonResponses = new ArrayList<>();
    List<LogonValidationResponse> logonValidateResponses = new ArrayList<>();
    Map<String, LogonRequest> logonResponseMap = new HashMap<>();
}
