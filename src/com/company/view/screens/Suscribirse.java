package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.TextoColor;

public class Suscribirse {
    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG) {
        Menu menu = new Menu(managerONG.cantidadONG(),1);
        menu.showMenuUsuario("Suscribirse",managerUsuario,managerONG,managerONG.ONGNombres());
        int idONG = new EditText("Elija una ONG (número)").pedirInt(1,managerONG.cantidadONG())-1;

        int cantidadDinero = new EditText("Que cantidad quiere donar mensualmente a " + managerONG.ONGs[idONG].nombre + "?  Dinero actual: "+managerUsuario.usuarioConectado.dinero+"€").pedirInt(1,1000000000);

        if(managerUsuario.suscribirse2(managerUsuario,cantidadDinero,managerONG.ONGs[idONG])){
            new TextoColor().colorCheck("¡Se ha suscrito a " + managerONG.ONGs[idONG].nombre + "!");
            new MisSuscripciones().start(managerUsuario, managerONG);
        }else{
            new TextoColor().colorError("No tienes suficiente dinero.");
        }

    }

}
