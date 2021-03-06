package com.company;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.screens.Acceso;
import com.company.view.screens.MenuUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;

public class Main {

    public static void main(String[] args) {

        ManagerUsuario managerUsuario = new ManagerUsuario();
        ManagerONG managerONG = new ManagerONG();

        managerUsuario.crearUsuario("usuario","usuario", "u", "u",
                "usuario", "usuario", "usuario",100, 123123);

        managerUsuario.crearUsuario("admin","admin", "a", "a",
                "admin", "admin", "admin",100, 123123);

        managerUsuario.concederPermisoAdministrador(managerUsuario.encontrarUsuario("a"));

        managerONG.crearONG("STC" ,"España","Francia");
        managerONG.crearONG("HQT" ,"Italia","Francia");
        managerONG.crearONG("ITC" ,"Rumanía","Italia");



        new Acceso().start(managerUsuario, managerONG);

    }

}
