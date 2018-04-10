package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;

public class Donar {


    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG) {
        Menu menu = new Menu(managerONG.cantidadONG());
        menu.showMenuUsuario("Donar",managerUsuario,managerONG,managerONG.ONGNombres());
        int idONG = new EditText("Elija una ONG (número)").pedirInt(1,managerONG.cantidadONG())-1;

        int cantidadDinero = new EditText("Que cantidad quiere donar a " + managerONG.ONGs[idONG].nombre + "?  Dinero actual: "+managerUsuario.usuarioConectado.dinero+"€").pedirInt(1,1000000000);

        if(managerUsuario.hacerDonacion(managerUsuario,cantidadDinero,managerONG.ONGs[idONG])){
            System.out.println("\n¡Donación realizada!\n");
            new HistorialDonativos().start(managerUsuario, managerONG);
        }else{
            System.out.println("\nNo tienes suficiente dinero.\n");
        }

        new EditText("Pulse INTRO para continuar").esperar();

        new MenuUsuario().start(managerUsuario, managerONG);
    }
}

//#ID  ONG         PAIS
// 1    oxfam       españa
// 2    ubnicef     brsil

