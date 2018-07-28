package com.zat.easygo.cucumber.step.common;

import com.zat.easygo.cucumber.api.client.EasyGoClient;
import com.zat.easygo.cucumber.api.request.LogonRequest;
import com.zat.easygo.cucumber.api.request.LogonValidationRequest;
import com.zat.easygo.cucumber.api.response.LogonResponse;
import com.zat.easygo.cucumber.api.response.LogonValidationResponse;
import com.zat.easygo.cucumber.common.Constants;
import com.zat.easygo.cucumber.common.StepSharedVariables;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * This class demonstrates how to test a controller using Spring Boot Test
 * (what makes it much closer to a Integration Test)
 *
 * @author moises.macero
 */
public class EasyGoBaseStepDefs {

    private List<LogonRequest> logonRequests;
    private List<LogonResponse> logonResponses;
    private Map<String, LogonRequest> logonResponseMap;

    private EasyGoClient client;

    @Autowired
    private StepSharedVariables stepSharedVariables;

    @Before("@logon")
    public void setup() {
        logonRequests = stepSharedVariables.getLogonRequests();
        logonResponses = stepSharedVariables.getLogonResponses();
        logonResponseMap = stepSharedVariables.getLogonResponseMap();
        
        logonRequests.clear();
        logonResponses.clear();
        logonResponseMap.clear();
        
        client = stepSharedVariables.getClient();
    }

    //    Scenario: Success to logon to EasyGo SIM
    //    Given logon to system with following valid information:
    //      |username |password  |lang    |platform
    //      |multi    |csd9      |eng     |android
    //      |multi    |csd9      |chi     |android
    //      |multi    |csd9      |eng     |ios
    //      |multi    |csd9      |chi     |ios
    //    When logon to system with above details
    //    Then logon success    
    
    @Given("^logon to system with following valid information:$")
    public void logon_to_system_with_following_valid_information(DataTable dataTable) {
        logonRequests = dataTable.asList(LogonRequest.class);
    }

    @When("^logon to system with above details$")
    public void logon_to_system_with_above_details() {
        logonRequests.stream().forEach(logonRequest -> {
            LogonResponse response = client.logon(logonRequest);
            logonResponses.add(response);
            logonResponseMap.put(response.getSid(), logonRequest);
        });
    }

    @Then("^logon success$")
    public void logon_success() {
        logonResponses.stream().map(LogonResponse::getCode).forEach(code -> Assert.assertThat(code, is(equalTo(Constants.CODE_SUCCESS))));
    }

    //    Scenario: Fail to logon to EasyGo SIM
    //    Given logon to system with following invalid information:
    //      |username |password  |lang    |platform |
    //      |multi    |csd8      |eng     |android  |
    //      |single   |csd9      |chi     |android  |
    //    When logon to system with above details
    //    Then logon fail

//    @Given("^logon to system with following invalid information:$")
//    public void logon_to_system_with_following_invalid_information(DataTable dataTable) {
//        logonRequests = dataTable.asList(LogonRequest.class);
//    }
//
//    @Then("^logon fail$")
//    public void logon_fail() {
//        logonResponses.stream().map(LogonResponse::getCode).forEach(code -> Assert.assertThat(code, is(not(equalTo(Constants.CODE_SUCCESS)))));
//    }

    //    Scenario: Validate a valid logon token
    //    Given logon to system with following valid information:
    //       |username |password  |lang    |platform |
    //       |multi    |csd9      |eng     |android  |
    //    When logon to system with above details
    //    And logon success
    //    Then logon token is valid

    @Then("^logon token is valid$")
    public void logon_token_is_valid() {
        logonResponses.stream().forEach(logonResponse -> {
            LogonRequest logonRequest = logonResponseMap.get(logonResponse.getSid());
            LogonValidationRequest logonValidationRequest = new LogonValidationRequest(logonResponse.getSid(), logonRequest.getLang(), logonRequest.getPlatform());
            LogonValidationResponse logonValidationResponse = client.validateToken(logonValidationRequest);
            Assert.assertThat(logonValidationResponse.getCode(), is(equalTo(Constants.CODE_SUCCESS)));
        });
    }
}