package com.company.view.widget;

import com.company.manager.ManagerMensajes;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.screens.MenuUsuario;

public class MessageViewer {

    public String foramatMessage(String texto){
        StringBuilder mensaje = new StringBuilder();
        for (int i = 0; i <texto.length() ; i++) {
            mensaje.append(texto.charAt(i));
            if((i+1)%50==0){
                mensaje.append("\n");
            }
        }
        mensaje.append("\n");
        return mensaje.toString();
    }

    public void todosLosMensajes(ManagerUsuario managerUsuario, ManagerMensajes managerMensajes, ManagerONG managerONG){
        for (int i = 0; i <managerMensajes.selectMensajesUsuario(managerUsuario.usuarioConectado).size() ; i++) {
            System.out.println("---------------------------------------------------");
            System.out.println("Emisor: "+managerUsuario.devolverStringValue("usuario",managerMensajes.selectMensajesUsuario(managerUsuario.usuarioConectado).get(i).emisorID));
            System.out.println("Fecha: "+managerMensajes.selectMensajesUsuario(managerUsuario.usuarioConectado).get(i).fecha);
            System.out.println("");
            System.out.println(new MessageViewer().foramatMessage(managerMensajes.selectMensajesUsuario(managerUsuario.usuarioConectado).get(i).mensaje));
            System.out.println("---------------------------------------------------");
            System.out.println("");

        }
        managerMensajes.marcarMensajesLeidos(managerUsuario.usuarioConectado);
        new EditText("Pulse INTRO para continuar").esperar();
        new MenuUsuario().startMenuUsuario(managerUsuario,managerONG,managerMensajes);
    }

    public void mensajesNoLeidos(ManagerUsuario managerUsuario, ManagerMensajes managerMensajes, ManagerONG managerONG){
        for (int i = 0; i <managerMensajes.selectMensajesNoLeidosUsuario(managerUsuario.usuarioConectado).size() ; i++) {
            System.out.println("---------------------------------------------------");
            System.out.println("Emisor: "+managerUsuario.devolverStringValue("usuario",managerMensajes.selectMensajesNoLeidosUsuario(managerUsuario.usuarioConectado).get(i).emisorID));
            System.out.println("Fecha: "+managerMensajes.selectMensajesNoLeidosUsuario(managerUsuario.usuarioConectado).get(i).fecha);
            System.out.println("");
            System.out.println(new MessageViewer().foramatMessage(managerMensajes.selectMensajesNoLeidosUsuario(managerUsuario.usuarioConectado).get(i).mensaje));
            System.out.println("---------------------------------------------------");
            System.out.println("");

        }
        managerMensajes.marcarMensajesLeidos(managerUsuario.usuarioConectado);
        new EditText("Pulse INTRO para continuar").esperar();
        new MenuUsuario().startMenuUsuario(managerUsuario,managerONG,managerMensajes);
    }
}
