package com.example.morpion;

public class Joueur {
    char marque; //Indique la marque du joueur

    static Plateau plateau; //Représente le plateau


    public Joueur(char mark){ //Constructeur
        this.marque = mark;
    }

    public void play(int x, int y){ //Met la marque sur le tableau
        plateau.set(x, y, marque);
    }

    public char getMarque(){ //Retourne la marque
        return marque;
    }

    public void setMarque(char marque){ // Définit la marque
        this.marque = marque;
    }
}
