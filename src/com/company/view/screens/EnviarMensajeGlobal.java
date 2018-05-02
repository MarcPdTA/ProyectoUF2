package com.company.view.screens;

import com.company.manager.ManagerMensajes;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.TextoColor;
import com.company.view.widget.WindowTitle;

public class EnviarMensajeGlobal {

    public void start(ManagerUsuario managerUsuario, ManagerMensajes managerMensajes, ManagerONG managerONG){
        new WindowTitle().show("Enviar mensaje global");
        int emisorID= managerUsuario.usuarioConectado;
        String mensaje = new EditText("Escriba su mensaje").pedirString();

        if(managerMensajes.enviarMensajeGlobal(emisorID,mensaje)){
            new TextoColor().colorCheck("Mensaje enviado con éxito");
            new EditText("Pulse INTRO para continuar").esperar();
            new MenuUsuario().startMenuUsuario(managerUsuario,managerONG,managerMensajes);
        }
        else {
            new TextoColor().colorError("El mensaje no pudo ser enviado");
            new EditText("Pulse INTRO para continuar").esperar();
            new EnviarMensajeGlobal().start(managerUsuario,managerMensajes,managerONG);
        }
    }
}