package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.Tabla;

public class HistorialDonativos {
    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG) {

        new Tabla().tablaDonaciones(managerUsuario, managerONG);
        new EditText("Pulse intro para continuar").esperar();
        new MenuUsuario().startMenuUsuario(managerUsuario, managerONG);
    }




}
