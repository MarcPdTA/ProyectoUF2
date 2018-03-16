package com.company.view.screens;

import java.util.Scanner;

public class Acceso {
    char opcion;

    public void start(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que quieres hacer?");
        System.out.println("a. Acceder.\nb. Registrarte.");
        opcion = scanner.nextLine().charAt(1);
        switch (opcion){
            case 'a':

                break;
            case 'b':
                Registro.start();
                break;
        }

    }
}
