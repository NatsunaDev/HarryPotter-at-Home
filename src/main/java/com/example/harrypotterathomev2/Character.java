package com.example.harrypotterathomev2;

public abstract class Character {
    public String name;
    public int hp, lvl;
    public Character(String name, int hp, int lvl) {//constructor
        this.name = name;
        this.hp = hp;
        this.lvl = lvl;
    }
    public void setHp(int amount, boolean damage){//true = damage, false = heal
        if(!damage) this.hp += amount;//si c'est pas un dommage, on heal
        else this.hp -= amount;//sinon on prend des dégats
    }

    public void usePotion(Potion potion, String houseName){
        setHp(potion.getHealthPointByHouseName(houseName), false);//on utilise la potion
    }
    public void takeDamage(int amount, String wizardHouse){

        setHp(amount, true );//on prend des dégats
    }

    public int attack(int damage){//fonction d'attaque
        return damage;
    }


}