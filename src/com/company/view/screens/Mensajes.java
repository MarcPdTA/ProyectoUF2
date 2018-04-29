package com.company.view.screens;

import com.company.manager.ManagerMensajes;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.Menu;
import com.company.view.widget.MessageViewer;
import com.company.view.widget.WindowTitle;

public class Mensajes {

    public void startMensajes(ManagerUsuario managerUsuario, ManagerMensajes managerMensajes, ManagerONG managerONG){
        Menu menu = new Menu(3,1);

        menu.show("Mensajes","Enviar Mensaje","Leer mensajes no leidos","Leer todos los mensajes");

        switch (menu.option()){

            case 1:
                new EnviarMensaje().start(managerUsuario,managerMensajes,managerONG);
                break;
            case 2:
                new MessageViewer().mensajesNoLeidos(managerUsuario, managerMensajes,managerONG);
                break;
            case 3:
                new MessageViewer().todosLosMensajes(managerUsuario, managerMensajes,managerONG);
                break;
            default:
                break;
        }
    }
}
