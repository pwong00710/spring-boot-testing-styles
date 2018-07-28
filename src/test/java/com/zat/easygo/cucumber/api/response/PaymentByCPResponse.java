package com.zat.easygo.cucumber.api.response;

import com.zat.easygo.cucumber.domain.Profile;
import cucumber.api.java.eo.Se;
import cucumber.api.java.sl.In;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PaymentByCPResponse extends BaseResponse {
    
    String ref;
    Integer cpbal;
}