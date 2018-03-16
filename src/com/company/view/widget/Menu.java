package com.company.view.widget;

import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    String title;

    public int show(String ...lista){
        System.out.println("MENU");
        for (int i = 0; i <lista.length ; i++) {
            System.out.println(i+1 + ") " +lista[i]);

        }

        System.out.println("OPcion: ");
        String opString = scanner.nextLine();

        return Integer.parseInt(opString);
    }
}
