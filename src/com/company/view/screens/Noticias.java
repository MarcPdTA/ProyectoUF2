package com.company.view.screens;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;

import java.awt.*;
import java.io.File;

public class Noticias {

    public void show(ManagerUsuario managerUsuario, ManagerONG managerONG){
        try {

        File htmlFile = new File("xml/noticias.xml");
        Desktop.getDesktop().browse(htmlFile.toURI());
        new MenuUsuario().startMenuUsuario(managerUsuario,managerONG);
        }catch (Exception e){
            new MenuUsuario().startMenuUsuario(managerUsuario,managerONG);
        }
    }
}
