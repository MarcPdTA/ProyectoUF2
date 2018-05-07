package com.company.view.screens;

import com.company.Main;
import com.company.manager.ManagerMensajes;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.model.Usuario;
import com.company.view.widget.EditText;
import com.company.view.widget.WindowTitle;

public class Registro {


    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG,ManagerMensajes managerMensajes) {
        new WindowTitle().show("Registro");

        String nombre = new EditText("Nombre: ").pedirString();
        String apellido = new EditText("Apellidos: ").pedirString();
        String contraseña = managerUsuario.cifrarPassword(new EditText("Contraseña: ").pedirString());
        String usuario = new EditText("Nombre de usuario: ").pedirString();
        String telefono = new EditText("Telefono: ").pedirString();
        String DNI = new EditText("DNI: ").checkDNI();
        String correo = new EditText("Correo electronico: ").checkEmail();
        int dinero = new EditText("Ingreso inicial: ").pedirInt(0, 1000000000);
        long cuenta = new EditText("Cuenta bancaria: ").pedirLong();

        managerUsuario.crearUsuario(nombre,apellido,usuario,contraseña,telefono,DNI,correo,dinero,cuenta);
        if(managerUsuario.usuarioConectado==0) {
            new Acceso().start(managerUsuario, managerONG,managerMensajes);
        }
        else {new MenuUsuario().startMenuUsuario(managerUsuario,managerONG,managerMensajes);}
    }


}
