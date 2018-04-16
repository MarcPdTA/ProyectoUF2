package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;

public class MisSuscripciones {
    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG) {
        if(managerUsuario.usuarioConectado.suscripciones[0]!=null) {
            new Menu(managerUsuario.usuarioConectado.suscripciones.length,1).show("\nMis suscripciones", managerUsuario.usuarioConectado.suscripciones);
            System.out.println(" ");
            new EditText("Pulse INTRO para continuar").esperar();
        }
        else {
            System.out.println("No te has suscrito a ninguna ONG\n");
            System.out.println(" ");
            new EditText("Pulse INTRO para continuar").esperar();
        }

        new MenuUsuario().startMenuUsuario(managerUsuario, managerONG);
    }


}
