package com.project.CryptoTracker.models;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
public class Coin{

    private String symbol ;
    private String name ;
    private String image ;
    private String price ;
    private String marketCap ;
    private String priceChangePercentage ;
    private String circulatingSupply;


    @Autowired
    public Coin(){

    }


    public Coin(String symbol, String name, String image, String price, String marketCap, String priceChangePercentage,
            String circulatingSupply) {
        this.symbol = symbol;
        this.name = name;
        this.image = image;
        this.price = price;
        this.marketCap = marketCap;
        this.priceChangePercentage = priceChangePercentage;
        this.circulatingSupply = circulatingSupply;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getPriceChangePercentage() {
        return priceChangePercentage;
    }

    public void setPriceChangePercentage(String priceChangePercentage) {
        this.priceChangePercentage = priceChangePercentage;
    }

    public String getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(String circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    

    



}