package com.example.harrypotterathomev2;

public class Spell extends AbstractSpell {
    //// tableau de sort avec chacun leur pourcentage de réussite
    public String spellName;
    public int successChance;
    public int damage;


    public Spell(String spellName, int successChance, int damage){//constructor
        this.spellName = spellName;
        this.successChance = successChance;
        this.damage = damage;
    }

    public int getSpellDamageByHouseName(String houseName){//fonction qui renvoie les dégats du sort en fonction de la maison
        if (houseName == "Slytherin") return damage + 20;//si la maison est Slytherin les degats sont augmentés de 30 par rapport aux autres maisons
        if (houseName == "Gryffindor") return damage - 10;
        if (houseName == "Ravenclaw") return damage - 10;
        if (houseName == "Hufflepuff") return damage - 10;
        return damage;//sinon on retourne la valeur de la potion
    }

}
