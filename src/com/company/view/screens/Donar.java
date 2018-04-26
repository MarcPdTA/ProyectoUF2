package com.company.view.screens;

import com.company.Database;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.Tabla;
import com.company.view.widget.TextoColor;

public class Donar {


    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG) {
        Menu menu = new Menu(1,managerONG.contarONGs());
        new Tabla().tablaONGs(managerONG);
        int idONG = new EditText("Elija una ONG (ID)").pedirInt(1,managerONG.contarONGs());

        int cantidadDinero = new EditText("Que cantidad quiere donar a " + managerONG.idToStringONG(idONG) + "?  Dinero actual: "+managerUsuario.devolverIntValue("dinero",managerUsuario.usuarioConectado)+"€").pedirInt(1,1000000000);

        if(managerUsuario.hacerDonacion(managerUsuario,cantidadDinero,managerUsuario.usuarioConectado,idONG)){
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
