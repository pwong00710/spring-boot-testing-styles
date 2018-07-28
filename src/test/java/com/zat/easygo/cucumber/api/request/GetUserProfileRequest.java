package com.zat.easygo.cucumber.api.request;

import com.zat.easygo.cucumber.type.Action;
import com.zat.easygo.cucumber.type.Action;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserProfileRequest extends BasePRequest {
    
    public GetUserProfileRequest(String sid, String mrt, String lang, String platform) {
        this.sid = sid;
        this.mrt = mrt;
        this.lang = lang;
        this.platform = platform;

        action = Action.GET_USER_PROFILE_AND_USAGE.code();
    }
}
