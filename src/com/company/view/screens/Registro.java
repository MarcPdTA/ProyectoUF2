package com.company.view.screens;

import com.company.Main;
import com.company.manager.ManagerUsuario;
import com.company.model.Usuario;
import com.company.view.widget.EditText;

public class Registro {


    public static void start(ManagerUsuario managerUsuario) {




        String nombre = new EditText("Nombre: ").pedirString();
        String apellido = new EditText("Apellidos: ").pedirString();
        String contraseña = new EditText("Conraseña: ").pedirString();
        String usuario = new EditText("Nombre de usuario: ").pedirString();
        String telefono = new EditText("Telefono: ").pedirString();
        String DNI = new EditText("DNI: ").pedirString();
        String correo = new EditText("Correo electronico: ").pedirString();
        long cuenta = new EditText("Cuenta bancaria: ").pedirLong();

        managerUsuario.crearUsuario(nombre,apellido,usuario,contraseña,telefono,DNI,correo,cuenta);

    }


}
