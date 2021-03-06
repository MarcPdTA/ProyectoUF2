package com.company.view.widget;

import com.company.manager.ManagerUsuario;

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
                new TextoColor().colorError("No ha introducido texto");
            }
        }while (mensaje.equals(""));
        return mensaje;
    }

    public String[] pedirArrayStrings(int longitud){
        String[] mensaje = new String[longitud];

        for (int i = 0; i <longitud ; i++) {
            mensaje[i]=pedirString();
        }

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
                    new TextoColor().colorError("Número inválido");
                }
            }catch (Exception exception){
                new TextoColor().colorError("Número inválido");
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


    public String checkEmail(){
        do {
            System.out.println(text);
        String email = scanner.nextLine();

    for (int i = 0; i < email.length(); i++) {
        if (email.charAt(i) == '@' && email.charAt(0) != '@') {
            for (int j = i+1; j < email.length(); j++) {
                //falta comprobar gmail o hotmail
                if (email.charAt(j) == '.') {
                    if (email.charAt(j + 1) == 'c' && email.charAt(j + 2) == 'o' && email.charAt(j + 3) == 'm'
                            || email.charAt(j + 1) == 'n' && email.charAt(j + 2) == 'e' && email.charAt(j + 3) == 't') {
                        return email;
                    }
                }
            }
        }

    }
            new TextoColor().colorError("ERROR!");

}while (true);
    }

}
