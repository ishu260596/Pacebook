package com.ishwar_arcore.pacebook.views.marketplace;

public class MarketPlaceModel {

    private String price;
    private int image;

    public MarketPlaceModel(String price, int image) {
        this.price = price;
        this.image=image;
    }


    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
