package com.project.CryptoTracker.models;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class Asset {
    private int id;
    private int userId;
    private String email;
    private String name;
    private Double price;
    private Double amount;
    private Date purchaseDate;
    private boolean sellOrBuy;
    private Double valueMade;
    private Double valueNow;

    @Autowired
    public Asset(){}

    public Asset(int id, int userId, String email, String name, Double price, Double amount, Date purchaseDate,
            boolean sellOrBuy, Double valueMade, Double valueNow) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.sellOrBuy = sellOrBuy;
        this.valueMade = valueMade;
        this.valueNow = valueNow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isSellOrBuy() {
        return sellOrBuy;
    }

    public void setSellOrBuy(boolean sellOrBuy) {
        this.sellOrBuy = sellOrBuy;
    }

    public Double getValueMade() {
        return valueMade;
    }

    public void setValueMade(Double valueMade) {
        this.valueMade = valueMade;
    }

    public Double getValueNow() {
        return valueNow;
    }

    public void setValueNow(Double valueNow) {
        this.valueNow = valueNow;
    }

    

       
}
