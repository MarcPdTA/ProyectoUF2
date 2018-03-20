package com.company.view.screens;

import com.company.manager.ManagerUsuario;
import com.company.model.Usuario;
import com.company.view.widget.EditText;

public class Acceder {

        public void start(){
            boolean noValido;
            do {

                ManagerUsuario managerUsuario = new ManagerUsuario();
                String usuario = new EditText("Usuario: ").pedirString();
                String contraseña = new EditText("Contraseña: ").pedirString();

                noValido = managerUsuario.verificarUsuario(usuario, contraseña);
                if(noValido){
                    System.out.println("Usuario/Contraseña incorrectos.");
                }
            }while(noValido);

        }
}
