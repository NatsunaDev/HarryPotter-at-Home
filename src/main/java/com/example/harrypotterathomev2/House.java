package com.example.harrypotterathomev2;

public class House {//class
    private String houseName;//nom de la maison

    public House(String name) {
        this.houseName = name;
    }//constructor
    public String getHouseName(){
        return houseName;
    }//getter
}
