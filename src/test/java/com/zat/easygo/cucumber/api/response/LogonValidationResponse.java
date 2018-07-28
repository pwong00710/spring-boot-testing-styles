package com.zat.easygo.cucumber.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zat.easygo.cucumber.domain.Profile;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LogonValidationResponse extends BaseResponse {
    
    public String clubid;
}