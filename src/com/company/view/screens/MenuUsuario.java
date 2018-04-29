package com.company.view.screens;

import com.company.manager.ManagerMensajes;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.TextoColor;

public class MenuUsuario {
    Menu menu = new Menu(8,1);
    Menu menuA = new Menu(2,9);
    Donar donar = new Donar();
    Suscribirse suscribirse = new Suscribirse();
    MisSuscripciones misSuscripciones = new MisSuscripciones();
    HistorialDonativos historialDonativos = new HistorialDonativos();
    Ingresar ingresar = new Ingresar();
    GestionarUsuarios gestionarUsuarios = new GestionarUsuarios();
    GestionarONGs gestionarOng = new GestionarONGs();
    int opcion;

    public void startMenuUsuario(ManagerUsuario managerUsuario, ManagerONG managerONG, ManagerMensajes managerMensajes) {

        if (managerUsuario.devolverIntValue("admin",managerUsuario.usuarioConectado)==0) {
            menu.showMenuUsuario("Menu principal", managerUsuario, managerONG,managerMensajes, "Realizar Donativo", "Suscribirme", "Mis Suscripciones", "Historial de Donativos", "Ingresar dinero","Mensajes","Noticias","Salir");
            opcion = menu.option();
            switch (opcion) {
                case 1:
                    donar.start(managerUsuario, managerONG,managerMensajes);
                    break;

                case 2:
                    suscribirse.start(managerUsuario, managerONG,managerMensajes);
                    break;

                case 3:
                    misSuscripciones.start(managerUsuario, managerONG,managerMensajes);
                    break;

                case 4:
                    historialDonativos.start(managerUsuario, managerONG,managerMensajes);
                    break;

                case 5:
                    ingresar.start(managerUsuario, managerONG,managerMensajes);
                    break;
                case 6:
                    new Mensajes().startMensajes(managerUsuario,managerMensajes, managerONG);
                    break;
                case 7:
                    new Noticias().show(managerUsuario,managerONG,managerMensajes);
                    break;
                case 8:
                    new Acceso().start(managerUsuario,managerONG,managerMensajes);
                    break;
                default:
                    new TextoColor().colorError("Opción Inválida");
                    new EditText("Pulse intro para continuar").esperar();
                    new MenuUsuario().startMenuUsuario(managerUsuario, managerONG,managerMensajes);
                    break;
            }
        }

        else {
            menuA.showMenuUsuario("Menu principal", managerUsuario, managerONG,managerMensajes, "Realizar Donativo", "Suscribirme", "Mis Suscripciones", "Historial de Donativos", "Ingresar dinero","Mensajes","Noticias","Salir");
            menuA.show("Panel de administración",  "Gestionar Usuarios", "Gestionar ONGs");

            opcion = menuA.option();

            switch (opcion) {
                case 1:
                    donar.start(managerUsuario, managerONG,managerMensajes);
                    break;

                case 2:
                    suscribirse.start(managerUsuario, managerONG,managerMensajes);
                    break;

                case 3:
                    misSuscripciones.start(managerUsuario, managerONG,managerMensajes);
                    break;

                case 4:
                    historialDonativos.start(managerUsuario, managerONG,managerMensajes);
                    break;

                case 5:
                    ingresar.start(managerUsuario, managerONG,managerMensajes);
                    break;

                case 6:
                    new Mensajes().startMensajes(managerUsuario,managerMensajes, managerONG);
                    break;

                case 7:
                    new Noticias().show(managerUsuario,managerONG,managerMensajes);
                    break;

                case 8:
                    new Acceso().start(managerUsuario,managerONG,managerMensajes);
                    break;

                case 9:
                    gestionarUsuarios.start(managerUsuario,managerONG,managerMensajes);
                    break;

                case 10:
                    gestionarOng.start(managerUsuario, managerONG,managerMensajes);
                    break;

                default:
                    new TextoColor().colorError("Opción Inválida");
                    new EditText("Pulse intro para continuar").esperar();
                    new MenuUsuario().startMenuUsuario(managerUsuario, managerONG,managerMensajes);
                    break;
            }
        }


    }
}
