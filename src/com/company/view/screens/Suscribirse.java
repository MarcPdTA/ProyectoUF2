package com.company.view.screens;

import com.company.Database;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.TextoColor;

public class Suscribirse {
    public void start(ManagerUsuario managerUsuario, ManagerONG managerONG) {
        Menu menu = new Menu(managerONG.contarONGs(),1);
        menu.showMenuUsuarioLista("Suscribirse",managerUsuario,managerONG,managerONG.listarONGs());
        int idONG = new EditText("Elija una ONG (número)").pedirInt(1,managerONG.contarONGs())-1;

        int cantidadDinero = new EditText("Que cantidad quiere donar mensualmente a " + Database.get().id_nombreONG(idONG) + "?  Dinero actual: "+managerUsuario.usuarioConectado.dinero+"€").pedirInt(1,1000000000);

        if(managerUsuario.suscribirse2(managerUsuario,cantidadDinero,idONG,Database.get().id_nombreONG(idONG))){
            new TextoColor().colorCheck("¡Se ha suscrito a " + Database.get().id_nombreONG(idONG) + "!");
            new MisSuscripciones().start(managerUsuario, managerONG);
        }else{
            new TextoColor().colorError("No tienes suficiente dinero.");
        }

    }

}
