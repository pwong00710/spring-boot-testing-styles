package com.zat.easygo.cucumber.api.response;

import com.zat.easygo.cucumber.domain.MRT;
import com.zat.easygo.cucumber.domain.MRT;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LogonResponse extends BaseResponse {
    
    String hkidkyc;
    String addressproofkyc;
    String notilang;
    String subscribedwelcomepack;
    String docType;
    Double cpbal;
    List<MRT> mrtlist = new ArrayList<>();
    String clubid;
    String language;
    String aftergraceperiod;
    String sid;
    String result;
    String tier;
    String name;
    String tngkyc;
    String primrt;
}
