package com.company.view.screens;

import com.company.Usuario;
import com.company.view.widget.EditText;

public class Registro {

    String username;

    void start() {

        Usuario usuario = new Usuario();

        usuario.nombre = new EditText("Nombre: ").pedir();
    }
}
