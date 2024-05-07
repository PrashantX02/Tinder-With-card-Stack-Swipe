package com.example.tinder;

public class itemmodel {

    String name,Address;
    int image;

    public itemmodel(String name,String address,int image){
        this.name = name;
        this.Address = address;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
