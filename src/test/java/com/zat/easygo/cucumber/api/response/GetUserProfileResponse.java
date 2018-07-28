package com.zat.easygo.cucumber.api.response;

import com.zat.easygo.cucumber.domain.Package;
import com.zat.easygo.cucumber.domain.Profile;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetUserProfileResponse extends BaseResponse {
    
    public Profile profile;
    public List<Package> packages = new ArrayList<>();
}