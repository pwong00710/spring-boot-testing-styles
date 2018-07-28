package com.zat.easygo.cucumber.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Profile {
    public String ceks;
    public Long databalance;
    public String showusage;
    public String datausagedesc;
    public String cpbal;
    public Long dataentitlement;
    public String showexpiry;
    public String expiry;
    public Long datausage;
    public String dataunit;
}