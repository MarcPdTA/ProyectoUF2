package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;

public class Ingresar {
    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG){
        int ingreso = new EditText("Cuanto dinero quiere ingresar?").pedirInt(1,10000000);
        new ManagerUsuario().ingresar(managerUsuario.usuarioConectado, ingreso);
        System.out.println("Has ingresado " + ingreso + "€");
        new MenuUsuario().start(managerUsuario, managerONG);

    }
}
