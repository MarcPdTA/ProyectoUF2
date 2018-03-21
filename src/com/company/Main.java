package com.company;

import com.company.manager.ManagerUsuario;
import com.company.view.screens.Acceso;
import com.company.view.screens.MenuUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;

public class Main {

    public static void main(String[] args) {

        ManagerUsuario managerUsuario = new ManagerUsuario();
        new Acceso().start(managerUsuario);

    }

}
