package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.Menu;

public class Donar {

    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG) {
        System.out.println("Donar ::: " + managerUsuario.usuarioConectado.usuario);
        Menu menu = new Menu(managerONG.cantidadONG());
        menu.show("ONGs disponibles", managerONG.ONGNombres());
    }
}
