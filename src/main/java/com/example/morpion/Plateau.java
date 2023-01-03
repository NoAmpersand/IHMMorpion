package com.example.morpion;

public class Plateau {
    char[][] grille; // Là où les joueurs jouent leur tour

    int counter;  // Pour vérifier si la grille est remplie

    public Plateau(){ //Constructeur
        counter = 0;
        grille = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j<3; j++){
                grille[i][j] = '.';
            }
        }
    }

    void set(int x, int y, char marque){ //Place la marque
        this.grille[x][y] = marque;
        counter ++; //On incrémente le nombre de marque sur la grille
    }

    public boolean isFull(){ //Verifier si grille est remplie
        return counter == 9;
    }

    public String[] winDetector(){ //Detecte le gagnant
        String[] sequence;

        //On teste toutes les lignes
        for(int i = 0; i < 3; i++){
            if(grille[i][0] == grille[i][1] && grille[i][0] == grille[i][2]){
                if(grille[i][0] != '.'){
                    sequence = new String[3];
                    sequence[0] = i+","+0;
                    sequence[1] = i+","+1;
                    sequence[2] = i+","+2;
                    return sequence;
                }
            }
        }
        //On teste tous les colonnes
        for(int j = 0; j < 3; j++){
            if(grille[0][j] == grille[1][j] && grille[0][j] == grille[2][j]){
                if(grille[0][j] != '.'){
                    sequence = new String[3];
                    sequence[0] = 0+","+j;
                    sequence[1] = 1+","+j;
                    sequence[2] = 2+","+j;
                    return sequence;
                }
            }
        }
        //On teste la première diagonale
        if(grille[0][0] == grille[1][1] && grille[1][1] == grille[2][2]){
            if(grille[0][0] != '.'){
                sequence = new String[3];
                sequence[0] = 0+","+0;
                sequence[1] = 1+","+1;
                sequence[2] = 2+","+2;
                return sequence;
            }
        }
        //On teste la deuxième diagonale
        if(grille[0][2] == grille[1][1] && grille[1][1] == grille[2][0]){
            if(grille[1][1] != '.'){
                sequence = new String[3];
                sequence[0] = 0+","+2;
                sequence[1] = 1+","+1;
                sequence[2] = 2+","+0;
                return sequence;
            }
        }
        //Si la méthode ne rentre dans aucune condition alors il n'y a pas de gagnant
        return null;
    }



    public char[][] getGrille(){//Getter pour la grille
        return this.grille;
    }

    public boolean isEmpty(int x, int y){//Verifie si position est vide
        return grille[x][y] == '.';
    }

}
