package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.Menu;
import com.company.view.widget.TextoColor;

public class GestionarONGs {

    Menu menu = new Menu(3,1);


    public void start(ManagerUsuario managerUsuario,ManagerONG managerONG){

        menu.showMenuUsuario("Panel de control de ONGs",managerUsuario,managerONG,"Cambiar nombre", "Cambiar Pais","Volver al menú");

        switch (menu.option()) {
            case 1:
                try {
                    managerONG.cambiarNombreONG(managerONG.encontrarONG(new EditText("Indique el nombre de la ONG").pedirString()),new EditText("Indique el nuevo nombre").pedirString());
                    new TextoColor().colorCheck("Username cambiado con éxito");
                }catch (Exception e){
                    new TextoColor().colorError("No existe una ONG con ese nombre");
                }
                new GestionarUsuarios().start(managerUsuario,managerONG);

                break;

            case 2:
                try {
                    managerONG.cambiarNombrePaisONG(managerONG.encontrarONG(new EditText("Indique el pais de la ONG a modificar").pedirString()),new EditText("Indique el nuevo pais").pedirString());
                    new TextoColor().colorCheck("Username cambiado con éxito");
                }catch (Exception e){
                    new TextoColor().colorError("No existe esa ONG");
                }
                new GestionarUsuarios().start(managerUsuario, managerONG);

                break;



            case 3:
                new MenuUsuario().startMenuUsuario(managerUsuario, managerONG);

            default:
                break;
        }
    }

}
