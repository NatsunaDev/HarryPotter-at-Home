package com.example.harrypotterathomev2;

import java.util.Random;

public class Wand {
    private int size;
    private Core core;

    public Wand(int size, Core core) {

        this.size = size;
        this.core = core;
    }


    public int getSize() {
        return size;
    }
    public  Core getCore() {
        return core;
    }

    public static Core randomCore() {//on retourne un coeur aléatoire
        int pick = new Random().nextInt(Core.values().length);//on choisit un nombre aléatoire entre 0 et 6
        return Core.values()[pick];
    }

    public static int randomSize() {//on retourne une taille aléatoire
        int size = (int) ((Math.random()*(30-10))+10);//un nombre aléatoire entre 10 et 30
        return size;
    }

}

enum Core {//enumération des coeurs
    PHOENIX_FEATHER,
    DRAGON_HEARTSTRING,
    UNICORN_TAIL_HAIR,
    VEELA_HAIR,
    THESTRAL_TAIL_HAIR,
    TROLL_WHISKER,
    CORAL;
}

