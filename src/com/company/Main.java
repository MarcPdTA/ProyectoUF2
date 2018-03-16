package com.company;

import com.company.view.screens.Acceso;
import com.company.view.screens.MenuUsuario;
import com.company.view.widget.Menu;

public class Main {

    public static void main(String[] args) {

        //new Acceso().start();

        Menu menu = new Menu();

        int opcion = menu.show("ads","afg","lkj","lkklj", "kldslksdg");

        menu.show();
    }
}
