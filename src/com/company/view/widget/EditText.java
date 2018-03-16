package com.company.view.widget;

import java.util.Scanner;

public class EditText {
    Scanner scanner = new Scanner(System.in);
    String text;

    public EditText(String text, int tipo /*tipo de dato*/){
        this.text = text;
    }

    public String pedir(){
        System.out.println(text);
        return scanner.nextLine();
    }
}
