package com.company.view.screens;


import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.Tabla;
import com.company.view.widget.TextoColor;

public class GestionarONGs {

    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG) {

        Menu menu = new Menu(5, 1);


        menu.showMenuUsuario("Panel de control de ONGs", managerUsuario, managerONG, "Cambiar nombre", "Crear nueva ONG", "Eliminar ONG","Listar ONGs", "Volver al menú");
        switch (menu.option()) {
            case 1:
                if(managerONG.cambiarNombre((new EditText("Indique el nombre a cambiar").pedirString()), new EditText("Indique el nuevo nombre").pedirString())) {
                    new TextoColor().colorCheck("Nombre cambiado con éxito");
                    }else{
                        new TextoColor().colorError("No existe una ONG con ese nombre");
                    }
                    new EditText("Pulse intro para continuar").esperar();
                new GestionarONGs().start(managerUsuario,managerONG);

                break;
            case 2:
                if(managerONG.crearONG(new EditText("Indique el nombre de la ONG a crear").pedirString(),new EditText("Indique el pais de la ONG a crear").pedirString())) {
                    new TextoColor().colorCheck("ONG creada con éxito");
                    new EditText("Pulse intro para continuar").esperar();
                }else {
                    new TextoColor().colorError("No se pudo crear la ONG");
                }
                new EditText("Pulse intro para continuar").esperar();
                new GestionarONGs().start(managerUsuario,managerONG);

            case 3:
                if(managerONG.borrarONG(new EditText("Indique el nombre de la ONG a borrar").pedirString())) {
                    new TextoColor().colorCheck("ONG borrada con éxito");
                    new EditText("Pulse intro para continuar").esperar();
                }else {
                    new TextoColor().colorError("No se pudo borrar la ONG");
                }
                new EditText("Pulse intro para continuar").esperar();
                new GestionarONGs().start(managerUsuario,managerONG);
                break;
            case 4:
                new Tabla().tablaONGs(managerONG);
                new EditText("Pulse intro para continuar").esperar();
                new GestionarONGs().start(managerUsuario,managerONG);
                break;
            case 5:
                new MenuUsuario().startMenuUsuario(managerUsuario, managerONG);
                break;
            default:
                new TextoColor().colorError("Opción Inválida");
                new EditText("Pulse intro para continuar").esperar();
                new GestionarONGs().start(managerUsuario, managerONG);
                break;
        }
    }
}