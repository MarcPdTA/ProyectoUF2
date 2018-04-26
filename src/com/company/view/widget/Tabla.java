package com.company.view.widget;

import com.company.manager.ManagerONG;
import com.company.manager.ManagerUsuario;


public class Tabla {

    public void tablaUsuarios(ManagerUsuario managerUsuario) {

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%10s %14s %14s %14s %14s %14s %14s %28s %14s %14s %14s", "ID", "NOMBRE", "APELLIDO", "USERNAME", "CONTRASEÑA","TELEFONO","DNI","CORREO","DINERO","CUENTA","ADMIN");
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < managerUsuario.cantidadUsuarios(); i++) {
            System.out.format("%10s %14s %14s %14s %14s %14s %14s %28s %14s %14s %14s",
                    managerUsuario.listaUsuarios().get(i).id, managerUsuario.listaUsuarios().get(i).nombre, managerUsuario.listaUsuarios().get(i).apellido, managerUsuario.listaUsuarios().get(i).usuario, managerUsuario.listaUsuarios().get(i).contraseña,
                    managerUsuario.listaUsuarios().get(i).telefono,managerUsuario.listaUsuarios().get(i).DNI,managerUsuario.listaUsuarios().get(i).correo,managerUsuario.listaUsuarios().get(i).dinero,managerUsuario.listaUsuarios().get(i).cuenta,managerUsuario.listaUsuarios().get(i).admin);
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("");
    }

    public void tablaONGs(ManagerONG managerONG) {

        System.out.println("----------------------------------------------------------");
        System.out.printf("%10s %28s %14s", "ID", "NOMBRE", "PAIS");
        System.out.println();
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < managerONG.listarONGs().size(); i++) {
            System.out.format("%10s %28s %14s ",
                   managerONG.listarONGs().get(i).id, managerONG.listarONGs().get(i).nombre, managerONG.listarONGs().get(i).pais);
            System.out.println();
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("");
    }

    public void tablaDonaciones(ManagerUsuario managerUsuario, ManagerONG managerONG) {

        System.out.println("-------------------------------------------------");
        System.out.printf("%10s %14s %14s", "ID", "ONG", "CANTIDAD");
        System.out.println();
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < managerUsuario.donacionesUsuarioConectado().size(); i++) {
            System.out.format("%10s %14s %14s",
                    i+1, managerONG.idToStringONG(managerUsuario.donacionesUsuarioConectado().get(i).ongID), managerUsuario.donacionesUsuarioConectado().get(i).dinero);
            System.out.println();
        }

        System.out.println("-------------------------------------------------");
        System.out.println("");

    }

    public void tablaSuscripciones(ManagerUsuario managerUsuario, ManagerONG managerONG) {

        System.out.println("-------------------------------------------------");
        System.out.printf("%10s %14s %14s", "ID", "ONG", "CANTIDAD");
        System.out.println();
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < managerUsuario.suscripcionesUsuarioConectado().size(); i++) {
            System.out.format("%10s %14s %14s",
                    i+1, managerONG.idToStringONG(managerUsuario.suscripcionesUsuarioConectado().get(i).ongID), managerUsuario.suscripcionesUsuarioConectado().get(i).dinero);
            System.out.println();
        }

        System.out.println("-------------------------------------------------");
        System.out.println("");
    }
}