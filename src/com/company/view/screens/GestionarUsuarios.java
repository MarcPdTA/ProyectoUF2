package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.Tabla;
import com.company.view.widget.TextoColor;

public class GestionarUsuarios {

    Menu menu = new Menu(12,1);


    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG){

        menu.showMenuUsuario("Panel de control de usuarios", managerUsuario, managerONG, "Cambiar nombre", "Cambiar apellido", "Cambiar username", "Cambiar contraseña", "Cambiar DNI","Cambiar correo","Cambiar teléfono","Borrar Usuario","Crear Usuario","Conceder permisos de Administrador","Listar usuarios","Volver al menú");

        switch (menu.option()) {
            case 1:
                if(managerUsuario.cambiarNombre((new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique el nuevo nombre").pedirString())){
                    new TextoColor().colorCheck("Nombre cambiado con éxito");
                }
                else{
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new EditText("Pulse intro para continuar").esperar();
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;

            case 2:
                if(managerUsuario.cambiarApellido((new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique el nuevo apellido").pedirString())){
                    new TextoColor().colorCheck("Apellido cambiado con éxito");
                }
                else{
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new EditText("Pulse intro para continuar").esperar();
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;

            case 3:
                if(managerUsuario.cambiarNombreUsuario((new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique el nuevo username").pedirString())){
                    new TextoColor().colorCheck("Username cambiado con éxito");
                }
                else{
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new EditText("Pulse intro para continuar").esperar();
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;

            case 4:
                if(managerUsuario.cambiarContraseña((new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique la nueva contraseña").pedirString())){
                    new TextoColor().colorCheck("Contraseña cambiada con éxito");
                }
                else{
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new EditText("Pulse intro para continuar").esperar();
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;

            case 5:
                if(managerUsuario.cambiarDNI((new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique el nuevo DNI").pedirString())){
                    new TextoColor().colorCheck("DNI cambiado con éxito");
                }
                else{
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new EditText("Pulse intro para continuar").esperar();
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;
            case 6:
                if(managerUsuario.cambiarCorreo((new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique el nuevo correo").pedirString())){
                    new TextoColor().colorCheck("Corrreo cambiado con éxito");
                }
                else{
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new EditText("Pulse intro para continuar").esperar();
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;
            case 7:
                if(managerUsuario.cambiarTelefono((new EditText("Indique el username del usuario a modificar").pedirString()),new EditText("Indique el nuevo telefono").pedirString())){
                    new TextoColor().colorCheck("Telefono cambiado con éxito");
                }
                else{
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new EditText("Pulse intro para continuar").esperar();
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;
            case 8:
                if(managerUsuario.borrarUsuario((new EditText("Indique el username del usuario a modificar").pedirString()))){
                    new TextoColor().colorCheck("Usuario eliminado con éxito");
                }
                else{
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new EditText("Pulse intro para continuar").esperar();
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;

            case 9:
                new Registro().start(managerUsuario,managerONG);
                new TextoColor().colorCheck("Usuario creado con éxito");

                new EditText("Pulse intro para continuar").esperar();
                new GestionarUsuarios().start(managerUsuario,managerONG);
                break;


            case 10:
                if(managerUsuario.concederPermisoAdministrador((new EditText("Indique el username del usuario a modificar").pedirString()))){
                    new TextoColor().colorCheck("Permiso concedido con éxito");
                }
                else{
                    new TextoColor().colorError("No existe un usuario con ese username");
                }
                new EditText("Pulse intro para continuar").esperar();
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;

            case 11:
                new Tabla().tablaUsuarios(managerUsuario);
                new EditText("Pulse intro para continuar").esperar();
                new GestionarUsuarios().start(managerUsuario,managerONG);
                break;

            case 12:
                new MenuUsuario().startMenuUsuario(managerUsuario, managerONG);
                break;

            default:
                new TextoColor().colorError("Opción Inválida");
                new EditText("Pulse intro para continuar").esperar();
                new GestionarUsuarios().start(managerUsuario, managerONG);

                break;
        }
    }

}
