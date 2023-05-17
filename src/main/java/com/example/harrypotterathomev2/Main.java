package com.example.harrypotterathomev2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static final Alert.AlertType INFORMATION = null;
    private static final Alert.AlertType CONFIRMATION = null;
    private static final String[] ANIMALS = {"OWL", "RAT", "CAT", "TOAD"};
    private static final String[] HOUSES = {"Gryffondor", "Poufsouffle", "Serdaigle", "Serpentard"};
    private static final int MIN_WAND_LENGTH = 15;
    private static final int MAX_WAND_LENGTH = 40;

    private static final String[] SPELLS = {
            "Wingardium Leviosa",
            "Expelliarmus",
            "Sword of Gryffindor",
            "Accio",
            "Expecto Patronum",
            "Sectumsempra"
    };

    private static final String[] ENEMIES = {
            "Troll",
            "Basilisk",
            "Dementor",
            "Voldemort and Peter Pettigrow",
            "Dolores Umbridge",
            "Death Eaters",
            "Voldemort and Bellatrix Lestrange"
    };

    private static int hp = 100;
    private static int level = 1;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Harry Potter at Home");
        BorderPane root = new BorderPane();

        // Ajouter l'image au centre
        Image image;
        image = new Image("file:src/assets/Hogwarts-Logo-500x281.png");
        ImageView imageView = new ImageView(image);
        root.setCenter(imageView);

        // Créer le bouton "Start Game"
        Button startButton = new Button("Start Game");

        // Ajouter une action lorsqu'on clique sur le bouton "Start Game"
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Appeler la fonction permettant de lancer le jeu
                showConfimation(primaryStage, root);
            }
        });

        // Créer le bouton "Infos"
        Button infoButton = new Button("Infos");

        // Ajouter l'action du bouton "Infos"
        infoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Ouvrir une page d'information
                showInformation(primaryStage, root);
            }
        });

        // Créer le bouton "Exit"
        Button exitButton = new Button("Exit");

        // Ajouter une action lorsqu'on clique sur le bouton "Exit"
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Quitter le programme
                Platform.exit();
            }
        });

        // Créer la boîte pour les boutons
        HBox buttonBox = new HBox(10, startButton, infoButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);
        root.setBottom(buttonBox);

        // Créer la scène
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showConfimation(Stage primaryStage, BorderPane root) {
        Alert alert = new Alert(CONFIRMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);

        // Créer une boîte de dialogue pour saisir le nom du wizard
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Start Game");
        dialog.setHeaderText("Enter your Wizard's Name:");

        // Créer un champ de texte pour saisir le nom
        TextField nameTextField = new TextField();
        dialog.getDialogPane().setContent(nameTextField);

        // Ajouter les boutons OK et Annuler
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

        // Récupérer le résultat de la boîte de dialogue
        dialog.setResultConverter(buttonType -> {
            if (buttonType == okButton) {
                return nameTextField.getText();
            }
            return null;
        });

        // Afficher la boîte de dialogue
        String wizardName = dialog.showAndWait().orElse(null);

        if (wizardName != null && !wizardName.isEmpty()) {
            // Générer les attributs aléatoires
            String pet = getRandomAnimal();
            String house = getRandomHouse();
            int wandLength = getRandomWandLength();

            // Construire le message d'information
            String information = "Wizard's Name: " + wizardName + "\n" +
                    "Pet: " + pet + "\n" +
                    "House: " + house + "\n" +
                    "Wand Length: " + wandLength + " cm";

            alert.setContentText(information);
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                startTrollBattle(primaryStage, root);
            }
        } else {
            alert.setContentText("Please enter a valid Wizard's Name.");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }
    private void startTrollBattle(Stage primaryStage, BorderPane root) {
        // Créer la fenêtre de combat
        Stage trollBattleStage = new Stage();
        trollBattleStage.setTitle("Troll Battle");

        BorderPane battleRoot = new BorderPane();

        // Afficher l'image du troll
        Image trollImage = new Image("file:src/assets/Troll.png");
        ImageView trollImageView = new ImageView(trollImage);
        battleRoot.setCenter(trollImageView);

        // Définir le nom de l'ennemi troll
        String enemyName = "Troll";

        // Afficher le nom de l'ennemi troll dans le titre de la fenêtre
        trollBattleStage.setTitle(enemyName + " Battle");

        // Créer le menu déroulant pour choisir un sort
        ComboBox<String> spellComboBox = new ComboBox<>();
        spellComboBox.getItems().addAll(SPELLS);
        battleRoot.setBottom(spellComboBox);

        // Créer le bouton pour attaquer
        Button attackButton = new Button("Attack");

        // Gérer l'attaque lorsque le bouton est cliqué
        attackButton.setOnAction(event -> {
            // Récupérer le sort sélectionné
            String selectedSpell = spellComboBox.getValue();

            // Effectuer l'attaque et vérifier si le joueur a gagné
            boolean victory = performAttack(selectedSpell);

            if (victory) {
                // Afficher l'image de la victoire
                Image victoryImage = new Image("file:src/assets/victory.png");
                ImageView victoryImageView = new ImageView(victoryImage);
                battleRoot.setCenter(victoryImageView);

                // Désactiver le menu déroulant et le bouton d'attaque
                spellComboBox.setDisable(true);
                attackButton.setDisable(true);

                // Lancer le combat contre le prochain ennemi
                startEnemyBattle(primaryStage, root);
            }
        });

        battleRoot.setRight(attackButton);

        // Créer la scène de combat
        Scene battleScene = new Scene(battleRoot, 800, 600);
        trollBattleStage.setScene(battleScene);

        // Afficher la fenêtre de combat
        trollBattleStage.show();
    }

    private boolean performAttack(String selectedSpell) {
        // Gérer l'attaque en fonction du sort sélectionné
        // (Vous pouvez personnaliser cette logique selon les règles de votre jeu)

        // Calculer la chance de succès du sort (par exemple, 80%)
        double successChance = 0.8;

        // Générer un nombre aléatoire entre 0 et 1
        double randomValue = Math.random();

        if (randomValue <= successChance) {
            // Le sort a réussi, le joueur gagne
            return true;
        } else {
            // Le sort a échoué, le joueur perd des points de vie
            // (Vous pouvez ajuster la logique de défaite en fonction de votre jeu)
            hp -= 10;
            if (hp <= 0) {
                // Le joueur a perdu tous ses points de vie, défaite
                gameOver();
            }
            return false;
        }
    }
    private void handleVictory(Stage primaryStage, BorderPane root) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Victory");
        alert.setHeaderText(null);
        alert.setContentText("You have defeated the enemy!");

        ButtonType nextButton = new ButtonType("Next Enemy", ButtonBar.ButtonData.NEXT_FORWARD);
        alert.getButtonTypes().setAll(nextButton);

        // Gérer le clic sur le bouton "Next Enemy"
        EventHandler<ActionEvent> nextEventHandler = event -> {
            // Vérifier s'il y a d'autres ennemis à combattre
            if (level < ENEMIES.length) {
                level++;
                startEnemyBattle(primaryStage, root);
            } else {
                // Le joueur a vaincu tous les ennemis, afficher un message de victoire
                showVictory(primaryStage, root);
            }
        };

        // Ajouter l'événement de clic au bouton "Next Enemy"
        Button nextEnemyButton = (Button) alert.getDialogPane().lookupButton(nextButton);
        nextEnemyButton.addEventFilter(ActionEvent.ACTION, nextEventHandler);

        alert.showAndWait();
    }

    private void startEnemyBattle(Stage primaryStage, BorderPane root) {
        // Créer la fenêtre de combat
        Stage enemyBattleStage = new Stage();
        enemyBattleStage.setTitle("Enemy Battle");

        BorderPane battleRoot = new BorderPane();

        // Afficher l'image de l'ennemi
        String enemyName = ENEMIES[level - 1];
        String enemyImagePath = "file:src/assets/" + enemyName + ".png";
        Image enemyImage = new Image(enemyImagePath);
        ImageView enemyImageView = new ImageView(enemyImage);
        battleRoot.setCenter(enemyImageView);

        // Créer le menu déroulant pour choisir un sort
        ComboBox<String> spellComboBox = new ComboBox<>();
        spellComboBox.getItems().addAll(SPELLS);
        battleRoot.setBottom(spellComboBox);

        // Créer le bouton pour attaquer
        Button attackButton = new Button("Attack");

        // Gérer l'attaque lorsque le bouton est cliqué
        attackButton.setOnAction(event -> {
            // Récupérer le sort sélectionné
            String selectedSpell = spellComboBox.getValue();

            // Effectuer l'attaque et vérifier si le joueur a gagné
            boolean victory = performAttack(selectedSpell);

            if (victory) {
                // Afficher la victoire et passer au combat suivant
                handleVictory(primaryStage, root);
                enemyBattleStage.close();
            }
        });

        battleRoot.setRight(attackButton);

        // Créer la scène de combat
        Scene battleScene = new Scene(battleRoot, 800, 600);
        enemyBattleStage.setScene(battleScene);

        // Afficher la fenêtre de combat
        enemyBattleStage.show();
    }

    private void showVictory(Stage primaryStage, BorderPane root) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Victory");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations! You have defeated all enemies and won the game!");

        // Ajouter un bouton "OK"
        alert.getButtonTypes().setAll(ButtonType.OK);

        // Gérer le clic sur le bouton "OK"
        alert.setOnCloseRequest(event -> Platform.exit());

        alert.showAndWait();
    }

    private void gameOver() {
        // Afficher une boîte de dialogue de défaite
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("You have been defeated! Game over.");
        alert.showAndWait();

        // Fermer l'application
        Platform.exit();
    }
        private void showInformation(Stage primaryStage, BorderPane root) {
            Alert alert = new Alert(INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);

            alert.setContentText("Welcome to Harry Potter at Home, the wizarding RPG. Do you have what it takes to be the greatest " +
                    "wizard? Can you meet all the challenges? Let's get started! \n\nYour goal is to complete your education at Hogwarts. " +
                    "Each year will be full of challenges. \n\nAt the beginning of the game, you create your wizard. You then have to equip him with a" +
                    " wand and let the Sorting Hat assign him his own enemies. The mechanics for defeating them will change depending on the enemy. " +
                    "\n\nTo fight your enemies, you will be able to use spells that you will learn as the years go by. You also have potions which can" +
                    " help you regain life. The spells you cast have a percentage chance of success, so you can fail your spells. \n\nEach house has its" +
                    " own specificity: \n- Potions are more effective for Hufflepuff members. \n- Spells do more damage for Slytherin members. \n- Gryffindor" +
                    " wizards are more resistant to damage. \n- Ravenclaw wizards are more accurate. \n\nTo complete the game, you must survive all 7 levels." +
                    " If you do, you will graduate from the most prestigious wizarding school on the planet!");

            // Ajouter un bouton "Ok"
            alert.getButtonTypes().setAll(ButtonType.OK);

            // Changer la couleur de fond en marron et l'écriture en gras
            alert.getDialogPane().setStyle("-fx-background-color: #784e2b; -fx-font-weight: bold; -fx-font-color: #000000;");

            alert.showAndWait();

    }

    private String getRandomAnimal() {
        Random random = new Random();
        int index = random.nextInt(ANIMALS.length);
        return ANIMALS[index];
    }

    private String getRandomHouse() {
        Random random = new Random();
        int index = random.nextInt(HOUSES.length);
        return HOUSES[index];
    }

    private int getRandomWandLength() {
        Random random = new Random();
        return random.nextInt(MAX_WAND_LENGTH - MIN_WAND_LENGTH + 1) + MIN_WAND_LENGTH;
    }
}
