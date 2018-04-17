package com.company.view.widget;


public class TextoColor {


    public void colorError(String text){

        String textoF = ("\033[31;1m" + "\uD83D\uDEC7 " + text + " \uD83D\uDEC7 " + "\033[0m"+"\n");
        System.out.println(textoF);
    }

    public void colorCheck(String text){

        String textoF = ("\033[32;1m" + "✓ " + text + " ✓ " + "\033[0m"+"\n");
        System.out.println(textoF);
    }
}
