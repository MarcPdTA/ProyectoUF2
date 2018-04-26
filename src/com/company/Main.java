package com.company;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.model.ONG;
import com.company.view.screens.Acceso;
import com.company.view.screens.MenuUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Database.get().insertONG("Save The Childern", "España");
        Database.get().insertONG("Intermon Oxfam", "Francia");

        ManagerUsuario managerUsuario = new ManagerUsuario();
        ManagerONG managerONG = new ManagerONG();

        managerUsuario.crearUsuario("usuario","usuario", "u", "u",
                "usuario", "usuario", "usuario@gmail.com",100, 123123);

        managerUsuario.crearUsuario("admin","admin", "a", "a",
                "admin", "admin", "admin@gmail.com",100, 123123);

        managerUsuario.concederPermisoAdministrador("a");


        new Acceso().start(managerUsuario, managerONG);

    }

}
