package com.zat.easygo.cucumber.api.client;

import com.zat.easygo.cucumber.api.request.GetUserProfileRequest;
import com.zat.easygo.cucumber.api.request.LogonRequest;
import com.zat.easygo.cucumber.api.request.LogonValidationRequest;
import com.zat.easygo.cucumber.api.request.PaymentByCPRequest;
import com.zat.easygo.cucumber.api.response.GetUserProfileResponse;
import com.zat.easygo.cucumber.api.response.LogonResponse;
import com.zat.easygo.cucumber.api.response.LogonValidationResponse;
import com.zat.easygo.cucumber.api.response.PaymentByCPResponse;
import com.zat.easygo.cucumber.api.request.LogonRequest;
import com.zat.easygo.cucumber.api.request.LogonValidationRequest;
import com.zat.easygo.cucumber.api.request.PaymentByCPRequest;
import com.zat.easygo.cucumber.api.response.LogonResponse;
import com.zat.easygo.cucumber.api.response.LogonValidationResponse;
import com.zat.easygo.cucumber.api.response.PaymentByCPResponse;

public interface EasyGoClient {
    LogonResponse logon(LogonRequest request);
    LogonValidationResponse validateToken(LogonValidationRequest request);
    GetUserProfileResponse getUserProfile(GetUserProfileRequest request);
    PaymentByCPResponse paymentByCP(PaymentByCPRequest request);
}
