package com.company.view.screens;

import com.company.manager.ManagerMensajes;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.*;

public class HistorialDonativos {

    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG,ManagerMensajes managerMensajes) {
        if(managerUsuario.donacionesUsuarioConectado().size()!=0) {
            new WindowTitle().show("Historial de donativos");
            new Tabla().tablaDonaciones(managerUsuario, managerONG);
            new EditText("Pulse intro para continuar").esperar();
        }
        else {
            new TextoColor().colorWarning("No hay ninguna donación en el historial");
            new EditText("Pulse INTRO para continuar").esperar();
        }
        new MenuUsuario().startMenuUsuario(managerUsuario, managerONG, managerMensajes);
    }
}
