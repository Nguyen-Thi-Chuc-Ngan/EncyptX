package controller;


import model.AffineCipher;
import model.HillCipher;
import model.SubstitutionCipher;
import model.VigenereCipher;
import view.frame.GUI;

public class MainController {
    private GUI gui;
    private AffineCipher affineCipher;
    private HillCipher hillCipher;
    private SubstitutionCipher substitutionCipher;
    private VigenereCipher vigenereCipher;

    public MainController() {
        gui = new GUI(this);
    }

    public static void main(String[] args) {
        new MainController();
    }

}
