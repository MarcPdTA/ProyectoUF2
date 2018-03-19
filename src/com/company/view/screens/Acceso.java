package com.company.view.screens;

import com.company.view.widget.Menu;

public class Acceso {
    Menu menu = new Menu(2);
    int opcion;

    public void start(){
    menu.show("Que quieres hacer?","Acceder","Registrarse");
    opcion=menu.option();

        switch (opcion){
            case '1':
                break;

            case '2':
                Registro.start();
                break;

            default:
                break;
        }


    }
}
