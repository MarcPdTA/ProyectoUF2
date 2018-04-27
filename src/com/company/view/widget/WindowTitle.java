package com.company.view.widget;

public class WindowTitle {
    void show(String title){
        System.out.println("\033[104;97m" + String.format("%-80s", title) + "\033[0m");
        System.out.println();
    }
}
