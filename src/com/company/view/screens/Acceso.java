package com.company.view.screens;

import com.company.view.widget.Menu;

public class Acceso {
    Menu menu = new Menu(2);
    Registro registro = new Registro();
    Acceder acceder = new Acceder();
    int opcion;

    public void start(){
    menu.show("Que quieres hacer?","Acceder","Registrarse");
    opcion=menu.option();

        switch (opcion){
            case '1':
                acceder.start();
                break;

            case '2':
                registro.start();
                break;

            default:
                break;
        }


    }
}
