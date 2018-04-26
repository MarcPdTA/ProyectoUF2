package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.model.Usuario;
import com.company.view.widget.EditText;
import com.company.view.widget.TextoColor;

public class Acceder {
    String usuario;
    String contraseña;
    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG){

        MenuUsuario menuUsuario = new MenuUsuario();
        do {

            usuario = new EditText("Usuario: ").pedirString();
            contraseña = new EditText("Contraseña: ").pedirString();


            if(!managerUsuario.verificarUsuario(usuario,contraseña)){
                new TextoColor().colorError("Usuario/Contraseña incorrectos.");
            }
        } while(!managerUsuario.verificarUsuario(usuario,contraseña));

            menuUsuario.startMenuUsuario(managerUsuario, managerONG);




    }
}
