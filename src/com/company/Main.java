package com.company;

import com.company.manager.ManagerMensajes;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.model.ONG;
import com.company.view.screens.Acceso;
import com.company.view.screens.MenuUsuario;
import com.company.view.screens.Noticias;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        ManagerUsuario managerUsuario = new ManagerUsuario();
        ManagerONG managerONG = new ManagerONG();
        ManagerMensajes managerMensajes = new ManagerMensajes();
        new Acceso().start(managerUsuario, managerONG,managerMensajes);

    }

}
//Rama Ivan