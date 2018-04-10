package com.company.view.widget;

import java.util.Scanner;

public class EditText {

    Scanner scanner = new Scanner(System.in);
    String text;

    public EditText(String text){ this.text = text; }

    public String pedirString(){
        String mensaje;
        do {
            System.out.println(text);
            mensaje=scanner.nextLine();
            if(mensaje.equals("")){
                System.out.println("No ha introducido texto"+"\n");
            }
        }while (mensaje.equals(""));
        return mensaje;
    }

    public int pedirInt(int min, int max){
        int numero=0;
        boolean isNum=false;

        do{
            System.out.println(text);
            try{
                numero=Integer.parseInt(scanner.nextLine());
                if(numero>=min && numero<=max){
                isNum=true;}
                else {
                    System.out.println("Número inválido"+"\n");
                }
            }catch (Exception exception){
                System.out.println("Número inválido"+"\n");
            }

        }while (!isNum);

            return numero;
    }

    public long pedirLong(){

        long numero=0;

        return numero;


    }

    public void esperar(){
        System.out.println(text);
        scanner.nextLine();
    }
}
