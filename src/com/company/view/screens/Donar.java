package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.TextoColor;

public class Donar {


    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG) {
        Menu menu = new Menu(1,managerONG.contarONGs());
        menu.showMenuUsuarioLista("Donar",managerUsuario,managerONG,managerONG.listarONGs());
        int idONG = new EditText("Elija una ONG (número)").pedirInt(1,managerONG.contarONGs())-1;

        int cantidadDinero = new EditText("Que cantidad quiere donar a " + managerONG.ONGs[idONG].nombre + "?  Dinero actual: "+managerUsuario.usuarioConectado.dinero+"€").pedirInt(1,1000000000);

        if(managerUsuario.hacerDonacion(managerUsuario,cantidadDinero,managerONG.ONGs[idONG])){
            new TextoColor().colorCheck("¡Donación realizada!");
            new HistorialDonativos().start(managerUsuario, managerONG);
        }else{
            new TextoColor().colorError("No tienes suficiente dinero.");
        }
        System.out.println(" ");
        new EditText("Pulse INTRO para continuar").esperar();

        new MenuUsuario().startMenuUsuario(managerUsuario, managerONG);
    }
}

//#ID  ONG         PAIS
// 1    oxfam       españa
// 2    ubnicef     brsil

