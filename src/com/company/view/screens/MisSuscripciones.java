package com.company.view.screens;

import com.company.manager.ManagerMensajes;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.Tabla;
import com.company.view.widget.WindowTitle;

public class MisSuscripciones {

    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG,ManagerMensajes managerMensajes) {
            new WindowTitle().show("Mis suscripciones");
            new Tabla().tablaSuscripciones(managerUsuario,managerONG);
            new EditText("Pulse INTRO para continuar").esperar();


        new MenuUsuario().startMenuUsuario(managerUsuario, managerONG,managerMensajes);
    }
}
