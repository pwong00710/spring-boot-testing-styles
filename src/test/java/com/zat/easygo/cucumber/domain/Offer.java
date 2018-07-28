package com.zat.easygo.cucumber.domain;

import lombok.Data;

@Data
public class Offer {
    Integer offerId;
    Integer normalPrice;
    Integer cpPrice;
    
    public Offer(Integer offerId, Integer normalPrice, Integer cpPrice) {
        this.offerId = offerId;
        this.normalPrice = normalPrice;
        this.cpPrice = cpPrice;
    }
}
