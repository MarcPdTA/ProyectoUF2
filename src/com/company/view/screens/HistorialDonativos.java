package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;

public class HistorialDonativos {
    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG) {
        if(managerUsuario.usuarioConectado.donaciones[0]!=null) {
            new Menu(managerUsuario.usuarioConectado.donaciones.length).show(1,"\nHistorial Donativos", managerUsuario.usuarioConectado.donaciones);
            new EditText("Pulse INTRO para continuar").esperar();
        }
        else {
            System.out.println("No has realizado ninguna donación\n");
            new EditText("Pulse INTRO para continuar").esperar();
        }
        new MenuUsuario().startMenuUsuario(managerUsuario, managerONG);
    }




}
