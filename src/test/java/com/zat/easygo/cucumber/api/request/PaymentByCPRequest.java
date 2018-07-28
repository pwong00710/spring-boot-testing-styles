package com.zat.easygo.cucumber.api.request;

import com.zat.easygo.cucumber.domain.PaymentPayload;
import com.zat.easygo.cucumber.type.Action;
import com.zat.easygo.cucumber.type.Action;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentByCPRequest extends BasePRequest {

    PaymentPayload paymentByCP;
    
    public PaymentByCPRequest(String sid, String mrt, String lang, String platform, PaymentPayload paymentByCP) {
        this.sid = sid;
        this.mrt = mrt;
        this.lang = lang;
        this.platform = platform;
        this.paymentByCP = paymentByCP;
        
        action = Action.PAYMENT_BY_CP.code();
    }
}
