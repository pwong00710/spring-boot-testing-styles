package com.zat.easygo.cucumber.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseResponse {
    
    String result;
    String code;
    String description;
}
