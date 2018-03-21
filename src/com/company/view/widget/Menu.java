package com.company.view.widget;

public class Menu {

   int longitud;

    public Menu(int longitud){
        this.longitud=longitud;
    }

    public void show( String titulo,String ...lista){
        System.out.println(titulo);
        for (int i = 0; i <lista.length ; i++) {
            System.out.println(i+1 + ") " +lista[i]);
        }
        System.out.println("");
    }

    public int option(){

        return (new EditText("Opción: ").pedirInt(1,longitud))-1;
    }
}
