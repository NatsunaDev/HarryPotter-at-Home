package com.example.harrypotterathomev2;

import java.util.Scanner;

public class GameLogic {
    static Scanner scanner = new Scanner(System.in);//Get a  input


    //Get a  input
    public static int readInt(String prompt, int userChoices) {//Permet de choisir un chiffre entre 1 et le nombre de choix

        int input;
        do {
            System.out.println(prompt);
            try {//On essaye de lire un chiffre
                input = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                input = -1;
                System.out.println("Please choose a INTEGER !"); //Si l'utilisateur rentre autre chose qu'un chiffre
            }
        } while (input < 1 || input > userChoices);//Tant que l'utilisateur ne rentre pas un chiffre entre 1 et le nombre de choix
        return input;
    }

    public static void clearConsole(){//Permet de clear la console
        for(int i = 0; i < 100; i++)
            System.out.println("");
    }

    public static void Separator(int n){//Permet de faire un séparateur
        for(int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static void Header(String title){//Permet de faire un header
        Separator(title.length());
        System.out.println(title);
        Separator(title.length());

    }
    public static void toContinue() {//Permet de faire une pause
        String confirm = "";
        while (!confirm.equals("y")) {//Tant que l'utilisateur ne rentre pas y
            System.out.println("Press : y to continue");//On lui demande de rentrer y
            confirm = scanner.nextLine();
        }
    }

    public static void fight(Wizard wizard, Enemy enemyName) {//Fonction de combat
        int wizardAttack;
        int enemyAttack;
        int input = 0;
        do {
            System.out.println("Which attack would you like to use");//On demande à l'utilisateur quelle attaque il veut utiliser
            System.out.println("Your available attacks: ");//On affiche les attaques disponible
            int numOfQuestion = 1;
            int numOfIndex = 0;
            for (int i = 0; i < wizard.knownSpells.size(); i++) {//On affiche les attaques disponible
                System.out.println("(" + numOfQuestion + ") " + wizard.getSpellName(wizard.knownSpells.get(numOfIndex)));
                numOfQuestion++;
                numOfIndex++;
            }
            //On peut ajouter l'option de prendre une potion ici
            input = readInt("->", numOfQuestion);//On demande à l'utilisateur quelle attaque il veut utiliser
            int selection = input-1;
            clearConsole();
            System.out.println("You use "+ wizard.getSpellName(wizard.knownSpells.get(selection)));//On affiche l'attaque que l'utilisateur a choisi
            Separator(30);
            toContinue();
            wizardAttack = wizard.wizardAttack(wizard.knownSpells.get(selection));//On lance l'attaque
            if (wizardAttack > 0){
                /// l'enemie perd des points de vies
                enemyName.setHp(wizardAttack, true);
                System.out.println("Your attack has hit, it remains " + enemyName.hp + "");//On affiche les points de vie restant à l'ennemie
            }
            else {
                System.out.println("Your attack failed");//Si l'attaque a échoué
            }
            if(enemyName.hp >= 0 ){//Si l'ennemie a encore des points de vie
                System.out.println(enemyName.name+ " attack you !");//On affiche que l'ennemie attaque
                enemyAttack = enemyName.attack(enemyName.getDamage());//On lance l'attaque de l'ennemie
                wizard.setHp(enemyAttack, true);//On enlève les points de vie à l'utilisateur
            }else
            System.out.println("You have "+ wizard.hp + "hp left");//On affiche les points de vie restant à l'utilisateur
            if(wizard.getWizardHouse() == "Gryffindor"){//Si l'utilisateur est de la maison Gryffindor
                wizard.hp += 25;//On ajoute 25 points de vie
                System.out.println("Thanks to your house the enemy's attack is 25 hp less effective");
                System.out.println("You therefore remain at " + wizard.hp + "hp");
            }
        }
        while (wizard.hp > 0 && enemyName.hp > 0);//Tant que l'utilisateur et l'ennemie ont des points de vie
        if(wizard.hp > 0) {//Si l'utilisateur a encore des points de vie
            System.out.println("You win !");
            Separator(16);
            toContinue();
            clearConsole();
            wizard.lvl = wizard.lvl+1;//On augmente le niveau de l'utilisateur
            Separator(24);
            Separator(24);
            System.out.println("You move to the level " + wizard.lvl);//On affiche le niveau de l'utilisateur
            Separator(24);
            Separator(24);
        }else {
            System.out.println("YOU ARE DEAD...");//Si l'utilisateur n'a plus de points de vie
            System.exit(1); // Vous etes mort

        }
    }




    public static void startGame() {
        boolean nameSet = false;
        String name;

        clearConsole();
        Separator(35);
        Separator(30);
        System.out.println("WELCOME TO HARRY POTTER AT HOME");
        System.out.println("ENJOY YOUR GAME");
        Separator(30);
        Separator(35);
        toContinue();

        do {
            clearConsole();
            System.out.println("Hagrid : You're a Wizard, insert your name!");//On demande à l'utilisateur de rentrer son nom
            name = scanner.next();//On récupère le nom de l'utilisateur
            clearConsole();
            System.out.println("Hagrid : You're Wizard name is " + name + ".\nIs that correct? ");//On affiche le nom de l'utilisateur
            System.out.println("(1) Yes !");
            System.out.println("(2) No, I want to change my wizard name.");
            int input = readInt("->", 2);//On demande à l'utilisateur si le nom est correct
            if (input == 1)
                nameSet = true;
        } while (!nameSet);//Tant que le nom n'est pas correct

        Wizard wizard = new Wizard(name, 100, 1);//On crée un nouveau personnage avec le nom de l'utilisateur
        wizard.setWand(wizard.getWand().randomSize(), wizard.getWand().randomCore());//On lui donne un bâton


        wizard.setPet(wizard.getPet());//On lui donne un animal de compagnie

        wizard.setHouse(SortingHat.randomHouse((int) ((Math.random() * (4 - 1)) + 1)));//On lui donne une maison

        System.out.println("Storting Hat : Your are in the (" + wizard.getWizardHouse() + ") house");//On affiche la maison de l'utilisateur
        System.out.println("Storting Hat : Your have the (" + wizard.getWand().getSize() + "cm) of (" + wizard.getWand().getCore() + ") wand");//On affiche la baguette de l'utilisateur
        System.out.println("Storting Hat : You have a (" + Wizard.randomPet() + ") house");//On affiche l'animal de compagnie de l'utilisateur
        Separator(50);
        toContinue();
        /// niveau 1 : Wingardium Leviosa seulement
        wizard.knownSpells.add(new Spell("Wingardium Leviosa", 75, 140));//On ajoute la première attaque à l'utilisateur
        wizard.knownSpells.add(new Spell("Expelliarmus", 55, 25));//On ajoute la deuxième attaque à l'utilisateur
        /// premier enemie qui spawn
        Enemy troll = new Enemy("Troll", 25, 150, Place.TOILET_OF_DUNGEON, 1);//On crée un premier ennemie

        System.out.println("A troll spawn with " + troll.hp + "hp !! Which spell would you use ? ");
        int input;
        do {
            System.out.println("What do you want to do ?");//On demande à l'utilisateur ce qu'il veut faire
            System.out.println("(1) Attack !");
            System.out.println("(2) Take a potion.");
            System.out.println("(3) Surrender !");
            input = readInt("->", 3);
            if (input == 1) {//Si l'utilisateur veut attaquer
                fight(wizard, troll);//On lance le combat avec le premiere ennemie
                System.out.println("Which spell would you use ? ");//On demande à l'utilisateur quelle attaque il veut utiliser
            } else if (input == 2) {//Si l'utilisateur veut utiliser une potion
                wizard.hp = +50;//On ajoute 50 points de vie à l'utilisateur
            } else {
                System.out.println("You surrend..");//Si l'utilisateur abandonne
                System.exit(2); // Status 2 :Vous avez abondonner fin du programme
            }
        }
            while (input != 2) ;//Tant que l'utilisateur n'a pas choisi de potion


            if (wizard.getWizardHouse() == "Gryffindor") {//Si l'utilisateur est dans la maison Gryffindor
                wizard.knownSpells.add(new Spell("Sword of Gryffindor", 70, 400));//On ajoute une nouvelle attaque speciallement pour lui
            } else
                wizard.knownSpells.add(new Spell("Accio", 70, 200));//Sinon on ajoute une autre attaque
            System.out.println("Congratulation you have a new spell(" + wizard.getSpellName(wizard.knownSpells.get(2)) + ") !");//On affiche la nouvelle attaque de l'utilisateur
            Separator(50);//On affiche un séparateur
            Enemy basilisk = new Enemy("Basilisk", 35, 250, Place.CHAMBER_SECRETS, 2);//On crée un deuxieme ennemie
            System.out.println("A Basilisk spawn with " + basilisk.hp + "hp");
            int input2;
            do {
                System.out.println("What do you want to do ?");
                System.out.println("(1) Attack !");
                System.out.println("(2) Take a potion.");
                System.out.println("(3) Surrender !");
                input2 = readInt("->", 3);
                if (input2 == 1) {
                    fight(wizard, basilisk);
                    System.out.println("Which spell would you use ? ");
                } else if (input2 == 2) {
                    wizard.hp = +50;
                } else {
                    System.out.println("You surrend..");
                    System.exit(2); // Status 2 :Vous avez abondonner fin du programme
                }
            } while (input2 != 2);

            wizard.knownSpells.add(new Spell("Expecto Patronum", 70, 200));
            System.out.println("Congratulation you have a new spell(" + wizard.getSpellName(wizard.knownSpells.get(3)) + ") !");
            Separator(50);
            Enemy dementor = new Enemy("Dementor", 50, 300, Place.FORBIDDEN_FOREST_LAKE, 3);
            System.out.println("Dementor is here, he have " + dementor.hp + "hp");
            int input3;
            do {
                System.out.println("What do you want to do ?");
                System.out.println("(1) Attack !");
                System.out.println("(2) Take a potion.");
                System.out.println("(3) Surrender !");
                input3 = readInt("->", 3);
                if (input3 == 1) {
                    fight(wizard, dementor);
                    System.out.println("Which spell would you use ? ");
                } else if (input3 == 2) {
                    wizard.hp = +50;
                } else {
                    System.out.println("You surrend..");
                    System.exit(2); // Status 2 :Vous avez abondonner fin du programme
                }
            } while (input3 != 2);


            Enemy group1 = new Enemy("Voldemort and Peter Pettigrow", 80, 150, Place.GRAVEYARD_OF_LITTLE_HANGLETON, 4);
            System.out.println("Voldemort and Peter Pettigrow on spawn, il a " + group1.hp + "hp");
            int input4;
            do {
                System.out.println("What do you want to do ?");
                System.out.println("(1) Attack !");
                System.out.println("(2) Take a potion.");
                System.out.println("(3) Surrender !");
                input4 = readInt("->", 3);
                if (input4 == 1) {
                    fight(wizard, group1);
                    System.out.println("Which spell would you use ? ");
                } else if (input4 == 2) {
                    wizard.hp = +50;
                } else {
                    System.out.println("You surrend..");
                    System.exit(2); // Status 2 :Vous avez abondonner fin du programme
                }
            } while (input4 != 2);


            Enemy dolores = new Enemy("Dolores Ombrage", 25, 150, Place.EXAMEN_CLASS_OF_POUDLARD, 5);
            System.out.println("Dolores Ombrage is here, he have " + dolores.hp + "hp");
            int input5;
            do {
                System.out.println("What do you want to do ?");
                System.out.println("(1) Attack !");
                System.out.println("(2) Take a potion.");
                System.out.println("(3) Surrender !");
                input5 = readInt("->", 3);
                if (input5 == 1) {
                    fight(wizard, dolores);
                    System.out.println("Which spell would you use ? ");
                } else if (input5 == 2) {
                    wizard.hp = +50;
                } else {
                    System.out.println("You surrend..");
                    System.exit(2); // Status 2 :Vous avez abondonner fin du programme
                }
            } while (input5 != 2);


            wizard.knownSpells.add(new Spell("Sectumsempra", 70, 120));
            System.out.println("Congratulation you have a new spell(" + wizard.getSpellName(wizard.knownSpells.get(4)) + ") !");
            Separator(50);
            Enemy deatheaters = new Enemy("Death Eaters", 100, 300, Place.ASTRONOMY_TOWER, 6);
            System.out.println("Death Eaters are here ! They have " + deatheaters.hp + "hp");
            int input6;
            do {
                System.out.println("What do you want to do ?");
                System.out.println("(1) Attack !");
                System.out.println("(2) Take a potion.");
                System.out.println("(3) Surrender !");
                input6 = readInt("->", 3);
                if (input6 == 1) {
                    fight(wizard, deatheaters);
                    System.out.println("Which spell would you use ? ");
                } else if (input6 == 2) {
                    wizard.hp = +50;
                } else {
                    System.out.println("You surrend..");
                    System.exit(2); // Status 2 :Vous avez abondonner fin du programme
                }
            } while (input6 != 2);


            Enemy groupe2 = new Enemy("Voldemort and Bellatrix Lestrange", 100, 500, Place.POUDLARD, 7);
            System.out.println("Voldemort and Bellatrix Lestrange are here" + groupe2.hp + "hp");
            int input7;
            do {
                System.out.println("What do you want to do ?");
                System.out.println("(1) Attack !");
                System.out.println("(2) Take a potion.");
                System.out.println("(3) Surrender !");
                input7 = readInt("->", 3);
                if (input7 == 1) {
                    fight(wizard, groupe2);
                    System.out.println("Which spell would you use ? ");
                } else if (input7 == 2) {
                    wizard.hp = +50;
                } else {
                    System.out.println("You surrend..");
                    System.exit(2); // Status 2 :Vous avez abondonner fin du programme
                }
            } while (input7 != 2);


            System.out.println("You finish the game ! Congratulation  ");
            System.exit(0); // Status 0 :Vous avez gagner fin du programme

        }
    }

