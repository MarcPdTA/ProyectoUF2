package com.company.view.screens;

import com.company.manager.ManagerMensajes;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.*;

public class MisSuscripciones {

    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG,ManagerMensajes managerMensajes) {
        if(managerUsuario.suscripcionesUsuarioConectado().size()!=0) {
            new WindowTitle().show("Mis suscripciones");
            new Tabla().tablaSuscripciones(managerUsuario, managerONG);
            new EditText("Pulse INTRO para continuar").esperar();

        }else {
            new TextoColor().colorWarning("No hay ninguna suscripción activa");
            new EditText("Pulse INTRO para continuar").esperar();
        }
        new MenuUsuario().startMenuUsuario(managerUsuario, managerONG,managerMensajes);
    }
}
