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

        Database.get().insertONG("STH", "ES");
        Database.get().insertONG("ITC", "EN");

        List<ONG> resultado = Database.get().selectTodasONGS();

        for (int i = 0; i < resultado.size(); i++) {
            System.out.println(resultado.get(i).nombre);
        }

        ManagerUsuario managerUsuario = new ManagerUsuario();
        ManagerONG managerONG = new ManagerONG();

        managerUsuario.crearUsuario("usuario","usuario", "u", "u",
                "usuario", "usuario", "usuario",100, 123123);

        managerUsuario.crearUsuario("admin","admin", "a", "a",
                "admin", "admin", "admin",100, 123123);

        managerUsuario.concederPermisoAdministrador(managerUsuario.encontrarUsuario("a"));


        new Acceso().start(managerUsuario, managerONG);

    }

}
