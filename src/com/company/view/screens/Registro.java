package com.company.view.screens;

import com.company.model.Usuario;
import com.company.view.widget.EditText;

public class Registro {

    String username;

    public static void start() {

        Usuario usuario = new Usuario();

        usuario.nombre = new EditText("Nombre: ").pedir();
    }

    void pedirDatos() {
//        nombre = new EditText("Nombre").pedir();
//        apellido = new EditText("Introduzca se apellido").pedir();

    }
}
