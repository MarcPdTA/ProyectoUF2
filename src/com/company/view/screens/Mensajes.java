package com.company.view.screens;

import com.company.manager.ManagerMensajes;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.Menu;
import com.company.view.widget.MessageViewer;
import com.company.view.widget.TextoColor;

public class Mensajes {

    public void startMensajes(ManagerUsuario managerUsuario, ManagerMensajes managerMensajes, ManagerONG managerONG){
        Menu menu = new Menu(5,1);
        if(managerUsuario.devolverIntValue("admin",managerUsuario.usuarioConectado)==1) {
            menu.show("Mensajes", "Enviar Mensaje", "Leer mensajes no leidos", "Leer todos los mensajes", "Enviar mensaje global","Volver al menú");

            switch (menu.option()) {

                case 1:
                    new EnviarMensaje().start(managerUsuario, managerMensajes, managerONG);
                    break;
                case 2:
                    if (managerMensajes.selectMensajesNoLeidosUsuario(managerUsuario.usuarioConectado).size() != 0) {
                        new MessageViewer().mensajesNoLeidos(managerUsuario, managerMensajes, managerONG);
                    } else {
                        new TextoColor().colorWarning("No hay mensajes que mostar");
                    }
                    new Mensajes().startMensajes(managerUsuario, managerMensajes, managerONG);
                    break;
                case 3:
                    if (managerMensajes.selectMensajesUsuario(managerUsuario.usuarioConectado).size() != 0) {
                        new MessageViewer().todosLosMensajes(managerUsuario, managerMensajes, managerONG);
                    } else {
                        new TextoColor().colorWarning("No hay mensajes que mostar");
                    }
                    new Mensajes().startMensajes(managerUsuario, managerMensajes, managerONG);
                    break;
                case 4:
                    new EnviarMensajeGlobal().start(managerUsuario, managerMensajes, managerONG);
                    break;
                case 5:
                    new MenuUsuario().startMenuUsuario(managerUsuario, managerONG, managerMensajes);

                default:
                    break;
            }
        }
        else{
            menu.show("Mensajes", "Enviar Mensaje", "Leer mensajes no leidos", "Leer todos los mensajes","Volver al menú");

            switch (menu.option()) {

                case 1:
                    new EnviarMensaje().start(managerUsuario, managerMensajes, managerONG);
                    break;
                case 2:
                    if (managerMensajes.selectMensajesNoLeidosUsuario(managerUsuario.usuarioConectado).size() != 0) {
                        new MessageViewer().mensajesNoLeidos(managerUsuario, managerMensajes, managerONG);
                    } else {
                        new TextoColor().colorWarning("No hay mensajes que mostar");
                    }
                    new Mensajes().startMensajes(managerUsuario, managerMensajes, managerONG);
                    break;
                case 3:
                    if (managerMensajes.selectMensajesUsuario(managerUsuario.usuarioConectado).size() != 0) {
                        new MessageViewer().todosLosMensajes(managerUsuario, managerMensajes, managerONG);
                    } else {
                        new TextoColor().colorWarning("No hay mensajes que mostar");
                    }
                    new Mensajes().startMensajes(managerUsuario, managerMensajes, managerONG);
                    break;
                case 4:
                    new EnviarMensajeGlobal().start(managerUsuario, managerMensajes, managerONG);
                    break;

                default:
                    break;
        }
     }
    }
}
