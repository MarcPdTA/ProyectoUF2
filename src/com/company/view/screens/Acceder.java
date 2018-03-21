package com.company.view.screens;

import com.company.manager.ManagerUsuario;
import com.company.model.Usuario;
import com.company.view.widget.EditText;

public class Acceder {
    String usuario;
    String contraseña;
    public void start(){
        boolean noValido;
        ManagerUsuario managerUsuario = new ManagerUsuario();
        MenuUsuario menuUsuario = new MenuUsuario();
        do {

            usuario = new EditText("Usuario: ").pedirString();
            contraseña = new EditText("Contraseña: ").pedirString();


            if(!managerUsuario.verificarUsuario(usuario,contraseña)){
                System.out.println("Usuario/Contraseña incorrectos.");
            }
        } while(!managerUsuario.verificarUsuario(usuario,contraseña));
        menuUsuario.start();
    }
}
