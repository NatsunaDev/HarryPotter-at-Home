package com.example.harrypotterathomev2;

public class Potion {
    private String name;
    private int HealthPoint;

    public void setHealthPoint(int newHealthPoint){
        HealthPoint = newHealthPoint;
    }//setter

    public int getHealthPointByHouseName(String houseName) {//getter
        if (houseName == "Hufflepuff") {//si la maison est Hufflepuff
            System.out.println("Grace a votre maison vos potions sont plus efficaces");//on affiche un message
            return HealthPoint + 20;//on retourne la valeur de la potion + 20
        }
        return HealthPoint;//sinon on retourne la valeur de la potion
    }


}
