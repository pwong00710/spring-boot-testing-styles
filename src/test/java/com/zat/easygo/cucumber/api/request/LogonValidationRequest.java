package com.zat.easygo.cucumber.api.request;

import com.zat.easygo.cucumber.common.Constants;
import com.zat.easygo.cucumber.type.Action;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogonValidationRequest extends BasePRequest {
    
    public LogonValidationRequest(String sid, String lang, String platform) {
        this.sid = sid;
        this.lang = lang;
        this.platform = platform;

        mrt = Constants.EMPTY_STRING;
        action = Action.SESSION_VALIDATION.code();
    }
}
