package com.zat.easygo.cucumber.step.logon;

import com.zat.easygo.cucumber.MvcTestsApplication;
import com.zat.easygo.cucumber.api.client.EasyGoClient;
import com.zat.easygo.cucumber.api.request.LogonRequest;
import com.zat.easygo.cucumber.api.request.LogonValidationRequest;
import com.zat.easygo.cucumber.api.response.LogonResponse;
import com.zat.easygo.cucumber.api.response.LogonValidationResponse;
import com.zat.easygo.cucumber.common.Constants;
import com.zat.easygo.cucumber.common.StepSharedVariables;
import com.zat.easygo.cucumber.MvcTestsApplication;
import com.zat.easygo.cucumber.api.client.EasyGoClient;
import com.zat.easygo.cucumber.api.request.LogonRequest;
import com.zat.easygo.cucumber.api.response.LogonResponse;
import com.zat.easygo.cucumber.common.Constants;
import com.zat.easygo.cucumber.common.StepSharedVariables;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MvcTestsApplication.class)
public class EasyGoLogonStepDefs {

    @Autowired
    private StepSharedVariables stepSharedVariables;

    private List<LogonRequest> logonRequests;
    private List<LogonResponse> logonResponses;

    private EasyGoClient client;

    @Before("@logon")
    public void setup() {
        logonRequests = stepSharedVariables.getLogonRequests();
        logonResponses = stepSharedVariables.getLogonResponses();

        logonRequests.clear();
        logonResponses.clear();

        client = stepSharedVariables.getClient();
    }

    @Given("^logon to system with following invalid information:$")
    public void logon_to_system_with_following_invalid_information(DataTable dataTable) {
        logonRequests = dataTable.asList(LogonRequest.class);
    }

    @Then("^logon fail$")
    public void logon_fail() {
        logonResponses.stream().map(LogonResponse::getCode).forEach(code -> Assert.assertThat(code, is(not(equalTo(Constants.CODE_SUCCESS)))));
    }
}
