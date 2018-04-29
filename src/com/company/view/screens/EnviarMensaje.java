package com.company.view.screens;

import com.company.manager.ManagerMensajes;
import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;
import com.company.view.widget.EditText;
import com.company.view.widget.TextoColor;
import com.company.view.widget.WindowTitle;

public class EnviarMensaje {

    public void start(ManagerUsuario managerUsuario, ManagerMensajes managerMensajes, ManagerONG managerONG){
        new WindowTitle().show("Enviar mensaje");
        int emisorID= managerUsuario.usuarioConectado;
        String receptor = new EditText("Elija el usuario al que mandar el mensaje").pedirString();
        String mensaje = new EditText("Escriba su mensaje").pedirString();

        if(managerMensajes.enviarMensaje(emisorID,receptor,mensaje)){
            new TextoColor().colorCheck("Mensaje enviado con éxito");
            new EditText("Pulse INTRO para continuar").esperar();
            new MenuUsuario().startMenuUsuario(managerUsuario,managerONG,managerMensajes);
        }
        else {
            new TextoColor().colorError("El mensaje no pudo ser enviado");
            new EditText("Pulse INTRO para continuar").esperar();
            new EnviarMensaje().start(managerUsuario,managerMensajes,managerONG);
        }
    }
}
