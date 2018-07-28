package com.zat.easygo.cucumber.domain;

import lombok.Data;

import java.util.List;

@Data
public class PaymentPayload {
    public List<String> vascode = null;
    public Integer total;
    public String name;
    
    public PaymentPayload(List<String> vascode, Integer total) {
        this.vascode = vascode;
        this.total = total;
        this.name = "easygo order packages";
    }
}