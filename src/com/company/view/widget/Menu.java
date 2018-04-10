package com.company.view.widget;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;

public class Menu {

   int longitud;

    public Menu(int longitud){
        this.longitud=longitud;
    }

    public void show( String titulo,String ...lista){
        System.out.println(titulo);
        for (int i = 0; i <lista.length ; i++) {

            if(lista[i]!=null)
            System.out.println(i+1 + ") " +lista[i]);
        }
        System.out.println("");
    }

    public int option(){

        return (new EditText("Opción: ").pedirInt(1,longitud));
    }

    public void showMenuUsuario(String titulo,ManagerUsuario managerUsuario, ManagerONG managerONG, String ...lista){
        System.out.println(titulo + " ::: " + managerUsuario.usuarioConectado.usuario + " ::: " + "Dinero actual: "+managerUsuario.usuarioConectado.dinero+"€\n");

        for (int i = 0; i <lista.length ; i++) {

            if(lista[i]!=null)
                System.out.println(i+1 + ") " +lista[i]);
        }
        System.out.println("");
    }
}


