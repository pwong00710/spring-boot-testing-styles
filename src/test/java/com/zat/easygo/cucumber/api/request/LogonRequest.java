package com.zat.easygo.cucumber.api.request;

import com.zat.easygo.cucumber.type.Action;
import com.zat.easygo.cucumber.type.Action;
import lombok.Data;

@Data
public class LogonRequest {
    String p;
    String u;
    String action;
    String lang;
    String platform;
    
    public LogonRequest(String u, String p, String lang, String platform) {
        this.u = u;
        this.p = p;
        this.lang = lang;
        this.platform = platform;

        action = Action.LOGIN.code();
    }
}
