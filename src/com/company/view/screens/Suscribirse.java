package com.company.view.screens;

import com.company.Database;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.Tabla;
import com.company.view.widget.TextoColor;

import javax.swing.text.TabableView;

public class Suscribirse {
    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG) {
        new Tabla().tablaONGs(managerONG);

        int idONG = new EditText("Elija una ONG (número)").pedirInt(1,managerONG.contarONGs());

        int cantidadDinero = new EditText("Que cantidad quiere donar mensualmente a " + managerONG.idToStringONG(idONG) + "?  Dinero actual: "+managerUsuario.devolverIntValue("dinero", managerUsuario.usuarioConectado)+"€").pedirInt(1,1000000000);

        if(managerUsuario.suscribirse(managerUsuario,cantidadDinero,managerUsuario.usuarioConectado,idONG)){
            new TextoColor().colorCheck("¡Se ha suscrito a " + managerONG.idToStringONG(idONG) + "!");
            new MisSuscripciones().start(managerUsuario, managerONG);
        }else{
            new TextoColor().colorError("No tienes suficiente dinero.");
        }

    }

}
