package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.TextoColor;

public class MenuUsuario {
    Menu menu = new Menu(5,1);
    Menu menuA = new Menu(5,7);
    Donar donar = new Donar();
    Suscribirse suscribirse = new Suscribirse();
    MisSuscripciones misSuscripciones = new MisSuscripciones();
    HistorialDonativos historialDonativos = new HistorialDonativos();
    Ingresar ingresar = new Ingresar();
    GestionarUsuarios gestionarUsuarios = new GestionarUsuarios();
    GestionarONGs gestionarOng = new GestionarONGs();
    int opcion;

    public void startMenuUsuario(ManagerUsuario managerUsuario, ManagerONG managerONG) {

        if (managerUsuario.devolverIntValue("admin",managerUsuario.usuarioConectado)==0) {
            menu.showMenuUsuario("Menu principal", managerUsuario, managerONG, "Realizar Donativo", "Suscribirme", "Mis Suscripciones", "Historial de Donativos", "Ingresar dinero","Salir");
            opcion = menu.option();
            switch (opcion) {
                case 1:
                    donar.start(managerUsuario, managerONG);
                    break;

                case 2:
                    suscribirse.start(managerUsuario, managerONG);
                    break;

                case 3:
                    misSuscripciones.start(managerUsuario, managerONG);
                    break;

                case 4:
                    historialDonativos.start(managerUsuario, managerONG);
                    break;

                case 5:
                    ingresar.start(managerUsuario, managerONG);
                    break;
                case 6:
                    break;
                default:
                    new TextoColor().colorError("Opción Inválida");
                    new EditText("Pulse intro para continuar").esperar();
                    new MenuUsuario().startMenuUsuario(managerUsuario, managerONG);
                    break;
            }
        }

        else {
            menuA.showMenuUsuario("Menu principal", managerUsuario, managerONG, "Realizar Donativo", "Suscribirme", "Mis Suscripciones", "Historial de Donativos", "Ingresar dinero","Salir");
            menuA.show("Panel de administración",  "Gestionar Usuarios", "Gestionar ONGs");

            opcion = menuA.option();

            switch (opcion) {
                case 1:
                    donar.start(managerUsuario, managerONG);
                    break;

                case 2:
                    suscribirse.start(managerUsuario, managerONG);
                    break;

                case 3:
                    misSuscripciones.start(managerUsuario, managerONG);
                    break;

                case 4:
                    historialDonativos.start(managerUsuario, managerONG);
                    break;

                case 5:
                    ingresar.start(managerUsuario, managerONG);
                    break;

                case 6:
                    break;

                case 7:
                    gestionarUsuarios.start(managerUsuario,managerONG);
                    break;

                case 8:
                    gestionarOng.start(managerUsuario, managerONG);
                    break;

                default:
                    new TextoColor().colorError("Opción Inválida");
                    new EditText("Pulse intro para continuar").esperar();
                    new MenuUsuario().startMenuUsuario(managerUsuario, managerONG);
                    break;
            }
        }


    }
}
