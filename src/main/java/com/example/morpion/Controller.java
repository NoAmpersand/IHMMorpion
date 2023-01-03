package com.example.morpion;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private GridPane map; //Gridpane dans l'interface

    @FXML
    private Label turn; //Le label dans l'interface

    static int player = 0; //Pour changer les tours

    public static Node getNodeByRowColumIndex(final int row, final int column, GridPane gridPane){
        //Cette methode retourne l'objet à la position donnée dans le gridpane
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();
        for(Node node : children){
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column){
                result = node;
                break;
            }
        }
        return result;
    }

    public String getURL(String name){
        File f = new File(name);
        //On get le chemin d'accès dit absolu du fichier f
        String absolute = f.getAbsolutePath();
        absolute = absolute.substring(0, absolute.length()-name.length());
        return "file:\\"+absolute+"src\\main\\java\\files\\"+name;
    }

    void update(char[][] grille){ //On met à jour la map à l'aide de notre grille
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                switch(grille[i][j]){
                    case 'X': {
                        ImageView imv = (ImageView) getNodeByRowColumIndex(i, j, map);
                        imv.setImage(new Image(getURL("x_blue.png")));
                    }
                    break;
                    case 'O': {
                        ImageView imv = (ImageView) getNodeByRowColumIndex(i, j, map);
                        imv.setImage(new Image(getURL("o_red.png")));
                    }
                    break;
                    case '.': {
                        ImageView imv = (ImageView) getNodeByRowColumIndex(i, j, map);
                        imv.setImage(new Image(getURL("empty.png")));
                    }
                    break;
                    case '1': {
                        ImageView imv = (ImageView) getNodeByRowColumIndex(i, j, map);
                        imv.setImage(new Image(getURL("x_yellow.png")));
                    }
                    break;
                    case '2': {
                        ImageView imv = (ImageView) getNodeByRowColumIndex(i, j, map);
                        imv.setImage(new Image(getURL("o_yellow.png")));
                    }
                    break;
                }
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Joueur p1 = new Joueur('X'); // On associe X à joueur1;
        Joueur p2 = new Joueur('O'); // On associe O à joueur 2
        Plateau p = new Plateau(); //On crée le plateau
        Joueur.plateau = p; // Les deux joueurs jouent sur le même plateau
        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3; j++){
                ImageView imageView = new ImageView(getURL("empty.png"));
                final int col = i;
                final int row = j;

                imageView.setOnMouseClicked(
                        evt -> {
                            if(player == 0){
                                if(Joueur.plateau.isEmpty(row, col)){
                                    p1.play(row, col);
                                    player = 1;
                                    if(Joueur.plateau.winDetector() != null){
                                        turn.setText("Joueur X a gagné");
                                        String positions[] = Joueur.plateau.winDetector();
                                        for(String pos : positions){
                                            int x = Integer.parseInt(pos.split(",")[0]);
                                            int y = Integer.parseInt(pos.split(",")[1]);
                                            p1.setMarque('1');
                                            p1.play(x, y);
                                        }
                                        player = -1; //Fin du jeu
                                    }else{
                                        if(Joueur.plateau.isFull()){
                                            turn.setText("Fin de partie");
                                            player = -1;
                                        }else{
                                            turn.setText("Tour de Joueur O");
                                        }
                                    }
                                }
                            }else{
                                if(player != -1){
                                    if(Joueur.plateau.isEmpty(row, col)){
                                        p2.play(row, col);
                                        player = 0;
                                        if(Joueur.plateau.winDetector() != null){
                                            turn.setText("Joueur O a gagné");
                                            String positions[] = Joueur.plateau.winDetector();
                                            for(String pos : positions){
                                                int x = Integer.parseInt(pos.split(",")[0]);
                                                int y = Integer.parseInt(pos.split(",")[1]);
                                                p1.setMarque('2');
                                                p1.play(x, y);
                                            }
                                            player = -1; //Fin du jeu
                                        }else{
                                            if(Joueur.plateau.isFull()){
                                                turn.setText("Fin de partie");
                                                player = -1;
                                            }else{
                                                turn.setText("Tour de Joueur X");
                                            }
                                        }
                                    }
                                }
                            }
                            update(Joueur.plateau.grille);
                        }
                );

                map.setHgap(10); //Espace entre les lignes
                map.setVgap(10); //Espace entre les colonnes
                map.add(imageView, i, j); //On ajoute l'image
            }

        }
        turn.setText("Tour de Joueur X");
    }
}