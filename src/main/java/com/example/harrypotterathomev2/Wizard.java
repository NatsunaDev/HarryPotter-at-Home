package com.example.harrypotterathomev2;

import java.util.ArrayList;
import java.util.Random;

public class Wizard extends Character {

    public Pet pet;
    private Wand wand;
    private House house;
    ArrayList<Spell> knownSpells = new ArrayList<>();

    public Wizard(String name, int hp, int level) {
        super(name, hp, level);
    }

    public void setWand(int size, Core core){
        this.wand = new Wand(size, core);
    }

    public Wand getWand() {return wand;}

    public void setHouse(House randomHouse){
        this.house = randomHouse;
    }

    public String getWizardHouse(){
        return house.getHouseName();
    }

    public void setPet(Pet pet) { // set pet
        this.pet = pet;
    }
    public Pet getPet(){ // get pet
        return pet;
    }

    public int wizardAttack(Spell spell){

        /// le sorcier attaque, en fonction de ses chances de réussite, soit il fait perdre des HP, soit il rate
        int chanceReussite = spell.successChance; // 50% de réussite
        int random = new Random().nextInt(100-1+1)+1; // random entre 1 et 100
        if (random < chanceReussite){// si le random est inférieur à la chance de réussite, alors le sort est lancé
            return attack(spell.damage);// on lance l'attaque
        }
        return 0;
    }
    public String getSpellName(Spell spell){
        return spell.spellName;
    }// get spell name

    public static Pet randomPet() {
        int pick = new Random().nextInt(Pet.values().length);// random entre 0 et 3
        return Pet.values()[pick];// on retourne le pet correspondant au random
    }
}

enum Pet {// enum des pets
    OWL,
    RAT,
    CAT,
    TOAD
}



