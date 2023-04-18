package com.project.CryptoTracker.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssetData {
    private String coin;
    private Double amount;
    private Double price;

    @Autowired
    public AssetData(){

    }

    public AssetData(String coin, Double amount, Double price) {
        this.coin = coin;
        this.amount = amount;
        this.price = price;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    

    
}
