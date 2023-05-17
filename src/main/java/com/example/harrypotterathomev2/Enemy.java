package com.example.harrypotterathomev2;

public class Enemy extends AbstractEnemy {
    private int damage;
    private Place place;

    public Enemy(String name, int damage, int hp, Place place, int level){//constructor
        super(name, hp, level);
        this.place = place;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}

enum Place {//enumération des lieux
    TOILET_OF_DUNGEON,
    CHAMBER_SECRETS,
    FORBIDDEN_FOREST_LAKE,
    GRAVEYARD_OF_LITTLE_HANGLETON,
    EXAMEN_CLASS_OF_POUDLARD,
    ASTRONOMY_TOWER,
    POUDLARD,

}