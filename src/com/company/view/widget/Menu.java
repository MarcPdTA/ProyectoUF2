package com.company.view.widget;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;

import java.util.ArrayList;
import java.util.List;

public class Menu {

   int longitud;
   int inicio;

    public Menu(int longitud, int inicio){
        this.longitud=longitud;
        this.inicio=inicio;
    }

    public void show(String titulo, String ...lista){
        WindowTitle windowTitle = new WindowTitle();

        windowTitle.show(titulo);
        for (int i = 0; i <lista.length ; i++) {

            if(lista[i]!=null)
            System.out.println(i+1+(inicio-1) + ") " +lista[i]);
        }
        System.out.println("");
    }

    public int option(){

        return (new EditText("Opción: ").pedirInt(1,longitud+(inicio-1)));
    }

    public void showMenuUsuario(String titulo, ManagerUsuario managerUsuario, ManagerONG managerONG, String ...lista){
        WindowTitle windowTitle = new WindowTitle();
        windowTitle.show(titulo + " ::: " + managerUsuario.devolverStringValue("usuario",managerUsuario.usuarioConectado) + " ::: " + "Dinero actual: "+managerUsuario.devolverIntValue("dinero",managerUsuario.usuarioConectado)+"€");

        for (int i = 0; i <lista.length ; i++) {

            if(lista[i]!=null)
                System.out.println(i+1+ ") " +lista[i]);
        }
        System.out.println("");
    }

    public void showList(String titulo, List<String> lista){
        WindowTitle windowTitle = new WindowTitle();
        windowTitle.show(titulo);

        for (int i = 0; i <lista.size() ; i++) {

            if(lista.get(i)!=null)
                System.out.println(i+1+(inicio-1) + ") " +lista.get(i));
        }
        System.out.println("");
    }

    public void showMenuUsuarioLista(String titulo, ManagerUsuario managerUsuario, ManagerONG managerONG, List<String> lista){
        WindowTitle windowTitle = new WindowTitle();
        windowTitle.show(titulo + " ::: " + managerUsuario.devolverStringValue("usuario",managerUsuario.usuarioConectado) + " ::: " + "Dinero actual: "+managerUsuario.devolverIntValue("dinero",managerUsuario.usuarioConectado)+"€");

        for (int i = 0; i <lista.size() ; i++) {

            if(lista.get(i)!=null)
                System.out.println(i+1+ ") " +lista.get(i));
        }
        System.out.println("");
    }
}



