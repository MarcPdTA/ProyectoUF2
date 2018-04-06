package com.company.view.screens;

import com.company.manager.ManagerUsuario;
import com.company.view.widget.Menu;

public class MenuUsuario {
    Menu menu = new Menu(4);
    Donar donar = new Donar();
    Suscribirse suscribirse = new Suscribirse();
    MisSuscripciones misSuscripciones = new MisSuscripciones();
    HistorialDonativos historialDonativos = new HistorialDonativos();
    int opcion;

    public void start(ManagerUsuario managerUsuario) {
        menu.show("Menu principal ::: " + managerUsuario.usuarioConectado.usuario, "Realizar Donativo", "Suscribirme", "Mis Suscripciones", "Historial de Donativos");
        opcion = menu.option();

        switch (opcion) {
            case 1:
                donar.start();
                break;

            case 2:
                suscribirse.start();
                break;

            case 3:
                misSuscripciones.start();
                break;

            case 4:
                historialDonativos.start();
                break;

            default:
                break;
        }
    }
}
