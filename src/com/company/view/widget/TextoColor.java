package com.company.view.widget;


public class TextoColor {


    public void colorError(String text){

        String textoF = ("\033[31;1m" + "\uD83D\uDEC7 " + text + "\033[0m");
        System.out.println(textoF);
    }
}
