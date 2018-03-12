package com.company;

import com.company.view.widget.EditText;

public class Usuario {

    int id;
    public String nombre, apellido;

    public Usuario(){

    }

    public Usuario(String nombre, String apellido) {

    }

    void pedirDatos() {
        nombre = new EditText("Nombre").pedir();
        apellido = new EditText("Introduzca se apellido").pedir();

    }
}
