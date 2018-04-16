package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.TextoColor;

public class GestionarUsuarios {

    Menu menu = new Menu(10,1);


    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG){

        menu.showMenuUsuario("Panel de control de usuarios", managerUsuario, managerONG, "Cambiar nombre", "Cambiar apellido", "Cambiar nombre usuario", "Cambiar contraseña", "Cambiar DNI","Cambiar correo","Cambiar teléfono","Borrar Usuario","Crear Usuario","Volver al menú");

        switch (menu.option()) {
            case 1:
                try {
                    managerUsuario.cambiarNombre(managerUsuario.encontrarUsuario(new EditText("Indique el nombre de usuario del usuario a modificar").pedirString()),new EditText("Indique el nuevo nombre").pedirString());
                    new TextoColor().colorCheck("Username cambiado con éxito");
                }catch (Exception e){
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;

            case 2:
                try {
                    managerUsuario.cambiarApellido(managerUsuario.encontrarUsuario(new EditText("Indique el nombre de usuario del usuario a modificar").pedirString()),new EditText("Indique el nuevo apellido").pedirString());
                    new TextoColor().colorCheck("Username cambiado con éxito");
                }catch (Exception e){
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;

            case 3:
                try {
                    managerUsuario.cambiarNombreUsuario(managerUsuario.encontrarUsuario(new EditText("Indique el username del usuario a modificar").pedirString()), new EditText("Indique el nuevo nombre de usuario").pedirString());
                    new TextoColor().colorCheck("Username cambiado con éxito");
                }catch (Exception e){
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new GestionarUsuarios().start(managerUsuario,managerONG);
                break;

            case 4:
                try {
                    managerUsuario.cambiarContraseña(managerUsuario.encontrarUsuario(new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique la nueva contraseña").pedirString());
                    new TextoColor().colorCheck("Username cambiado con éxito");
                }catch (Exception e){
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;

            case 5:

                break;

            case 10:
                new MenuUsuario().startMenuUsuario(managerUsuario, managerONG);

            default:
                break;
        }
    }

}
