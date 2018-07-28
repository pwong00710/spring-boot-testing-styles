package com.zat.easygo.cucumber.step.subscribe;

import com.zat.easygo.cucumber.MvcTestsApplication;
import com.zat.easygo.cucumber.api.client.EasyGoClient;
import com.zat.easygo.cucumber.api.request.GetUserProfileRequest;
import com.zat.easygo.cucumber.api.request.LogonRequest;
import com.zat.easygo.cucumber.api.request.PaymentByCPRequest;
import com.zat.easygo.cucumber.api.response.GetUserProfileResponse;
import com.zat.easygo.cucumber.api.response.LogonResponse;
import com.zat.easygo.cucumber.api.response.PaymentByCPResponse;
import com.zat.easygo.cucumber.common.Constants;
import com.zat.easygo.cucumber.common.StepSharedVariables;
import com.zat.easygo.cucumber.domain.Offer;
import com.zat.easygo.cucumber.domain.PaymentPayload;
import com.zat.easygo.cucumber.type.Category;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zat.easygo.cucumber.domain.Package;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MvcTestsApplication.class)
public class EasyGoSubscribeStepDefs {

    @Autowired
    private StepSharedVariables stepSharedVariables;

//    private List<LogonRequest> logonRequests;
    private List<LogonResponse> logonResponses;
    private Map<String, LogonRequest> logonResponseMap;

    private EasyGoClient client;
    
    private Map<String, Long> dataBalances = new HashMap<>(); 
    private Map<Integer, Offer> dataOffers = new HashMap<>();
    private Map<String, PaymentByCPResponse> paymentByCPResponses = new HashMap<>();

    @Before("@subscribe")
    public void setup() {
//        logonRequests = stepSharedVariables.getLogonRequests();
        logonResponses = stepSharedVariables.getLogonResponses();
        logonResponseMap = stepSharedVariables.getLogonResponseMap();

        client = stepSharedVariables.getClient();
        
        dataOffers.put(1, new Offer(25, 50, 220));
        dataOffers.put(2, new Offer(81, 100, 440));
        dataOffers.put(5, new Offer(82, 150, 660));
        dataOffers.put(6, new Offer(27, 200, 880));
        dataOffers.put(10, new Offer(83, 250, 1100));
        dataOffers.put(11, new Offer(84, 300, 1320));
        dataOffers.put(12, new Offer(85, 350, 1540));
        dataOffers.put(20, new Offer(86, 400, 1760));        
    }

//    Scenario Outline: Subscribe data service
//    Given logon token is valid
//    And query remaining usage of '<mrt>'
//    When subscribe <data>G local data plan for '<mrt>'
//    Then data plan have <data>G more for '<mrt>'
//
//    Examples:
//            |mrt          | data |
//            |52900195     | 2    |
//            |52900195     | 5    |
//            |52900195     | 6    |
//            |52900195     | 10   |
//            |52900195     | 11   |
//            |52900195     | 12   |
//            |52900195     | 20   |

    @And("^query remaining usage of '(.*?)'$")
    public void query_remaining_usage(String mrt) {
        Assert.assertThat(logonResponses.size(), is(1));

        loadDataBalances(mrt);
        
        Assert.assertThat(dataBalances, hasKey(mrt));
    }
    
    private void loadDataBalances(String mrt) {
        logonResponses.stream().forEach(logonResponse -> {
            LogonRequest logonRequest = logonResponseMap.get(logonResponse.getSid());
            GetUserProfileRequest getUserProfileRequest = new GetUserProfileRequest(logonResponse.getSid(), mrt,
                    logonRequest.getLang(), logonRequest.getP());
            GetUserProfileResponse getUserProfileResponse = client.getUserProfile(getUserProfileRequest);
            Package p = getUserProfileResponse.getPackages().stream().filter(o->
                    Category.data == Category.valueOf(o.getCategory())
            ).findFirst().get();
            dataBalances.put(mrt, p.getDatabalance());
        });        
    }
    
    @When("^subscribe (\\d+)G local data plan for '(.*?)'$")
    public void subscribe_local_data_plan(int size, String mrt) {
        Offer offer = dataOffers.get(size);
        List<String> vascodes = new ArrayList<>();
        vascodes.add(offer.getOfferId().toString());
        PaymentPayload paymentByCP = new PaymentPayload(vascodes, offer.getCpPrice());
        
        logonResponses.stream().forEach(logonResponse -> {
            LogonRequest logonRequest = logonResponseMap.get(logonResponse.getSid());
            PaymentByCPRequest paymentByCPRequest = new PaymentByCPRequest(logonResponse.getSid(),  mrt,
                    logonRequest.getLang(), logonRequest.getPlatform(), paymentByCP);
            PaymentByCPResponse paymentByCPResponse = client.paymentByCP(paymentByCPRequest);
            paymentByCPResponses.put(mrt, paymentByCPResponse);
            Assert.assertThat(paymentByCPResponse.getCode(), is(equalTo(Constants.CODE_SUCCESS)));
        });

        Assert.assertThat(paymentByCPResponses, hasKey(mrt));
    }
    
    @Then("^data plan have (\\d+)G more for '(.*?)'$")
    public void data_plan_have_more(int size, String mrt) {
        Long prevDataBalance = dataBalances.get(mrt);

        loadDataBalances(mrt);
        
        Long currDataBalance = dataBalances.get(mrt);

        Assert.assertThat(prevDataBalance+size, is(equalTo(currDataBalance)));
    }
    
}
