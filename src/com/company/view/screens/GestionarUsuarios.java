package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.TextoColor;

public class GestionarUsuarios {

    Menu menu = new Menu(11,1);


    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG){

        menu.showMenuUsuario("Panel de control de usuarios", managerUsuario, managerONG, "Cambiar nombre", "Cambiar apellido", "Cambiar username", "Cambiar contraseña", "Cambiar DNI","Cambiar correo","Cambiar teléfono","Borrar Usuario","Crear Usuario","Conceder permisos de Administrador","Listar usuarios","Volver al menú");

        switch (menu.option()) {
            case 1:
                try {
                    managerUsuario.cambiarNombre(managerUsuario.encontrarUsuario(new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique el nuevo nombre").pedirString());
                    new TextoColor().colorCheck("Nombre cambiado con éxito");
                    new EditText("Pulse intro para continuar").esperar();
                }catch (Exception e){
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;

            case 2:
                try {
                    managerUsuario.cambiarApellido(managerUsuario.encontrarUsuario(new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique el nuevo apellido").pedirString());
                    new TextoColor().colorCheck("Apellido cambiado con éxito");
                    new EditText("Pulse intro para continuar").esperar();
                }catch (Exception e){
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;

            case 3:
                try {
                    managerUsuario.cambiarNombreUsuario(managerUsuario.encontrarUsuario(new EditText("Indique el username del usuario a modificar").pedirString()), new EditText("Indique el nuevo nombre de usuario").pedirString());
                    new TextoColor().colorCheck("Username cambiado con éxito");
                    new EditText("Pulse intro para continuar").esperar();
                }catch (Exception e){
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new GestionarUsuarios().start(managerUsuario,managerONG);
                break;

            case 4:
                try {
                    managerUsuario.cambiarContraseña(managerUsuario.encontrarUsuario(new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique la nueva contraseña").pedirString());
                    new TextoColor().colorCheck("Contraseña cambiada con éxito");
                    new EditText("Pulse intro para continuar").esperar();
                }catch (Exception e){
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new GestionarUsuarios().start(managerUsuario,managerONG);
                break;

            case 5:
                try {
                    managerUsuario.cambiarDNI(managerUsuario.encontrarUsuario(new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique el nuevo DNI").pedirString());
                    new TextoColor().colorCheck("DNI cambiado con éxito");
                    new EditText("Pulse intro para continuar").esperar();
                }catch (Exception e){
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new GestionarUsuarios().start(managerUsuario,managerONG);
                break;
            case 6:
                try {
                    managerUsuario.cambiarCorreo(managerUsuario.encontrarUsuario(new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique el nuevo correo").pedirString());
                    new TextoColor().colorCheck("Correo cambiado con éxito");
                    new EditText("Pulse intro para continuar").esperar();
                }catch (Exception e){
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new GestionarUsuarios().start(managerUsuario,managerONG);
                break;
            case 7:
                try {
                    managerUsuario.cambiarTelefono(managerUsuario.encontrarUsuario(new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique el nuevo telefono").pedirString());
                    new TextoColor().colorCheck("Telefono cambiado con éxito");
                    new EditText("Pulse intro para continuar").esperar();
                }catch (Exception e){
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new GestionarUsuarios().start(managerUsuario,managerONG);
                break;
            case 8:
                try {
                    managerUsuario.borrarUsuario(managerUsuario.encontrarUsuario(new EditText("Indique el username del usuario a borrar").pedirString()));
                    new TextoColor().colorCheck("Usuario borrado con éxito");
                    new EditText("Pulse intro para continuar").esperar();
                }catch (Exception e){
                    new TextoColor().colorError("No existe un usuario con ese username");
                }                break;
            case 9:
                managerUsuario.crearUsuarioAdmin();
                break;
            case 10:
                try {
                    managerUsuario.concederPermisoAdministrador(managerUsuario.encontrarUsuario(new EditText("Indique el username del usuario al que quiere conceder permisos de administrador").pedirString()));
                    new TextoColor().colorCheck("Permisos cambiados con éxito");
                    new EditText("Pulse intro para continuar").esperar();
                }catch (Exception e){
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new GestionarUsuarios().start(managerUsuario,managerONG);
                break;

            case 11:
                new Menu(managerUsuario.usuarios.length,1).show("\nLista de usuarios", managerUsuario.listaUsuarios());
                new EditText("Pulse intro para continuar").esperar();
                new GestionarUsuarios().start(managerUsuario,managerONG);
                break;

            case 12:
                new MenuUsuario().startMenuUsuario(managerUsuario, managerONG);
                break;

            default:
                new TextoColor().colorError("Opción Inválida");
                new GestionarUsuarios().start(managerUsuario, managerONG);
                new EditText("Pulse intro para continuar").esperar();
                break;
        }
    }

}
