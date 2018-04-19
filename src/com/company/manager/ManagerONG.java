package com.company.manager;

import com.company.model.ONG;
import com.company.model.Usuario;

import java.util.Arrays;

public class ManagerONG {
    public ONG[] ONGs = new ONG[100];

    public void crearONG(String nombre, String... nombresPais) {
        if (!ONGExiste(nombre)) {
            ONG ong = new ONG();
            ong.nombre = nombre;
            ong.nombresPais = nombresPais;

            for (int i = 0; i < ONGs.length; i++) {
                if (ONGs[i] == null) {
                    ong.id = i;
                    ONGs[i] = ong;
                    break;
                }

            }
        }
    }

    public boolean ONGExiste(String nombre) {
        for (int i = 0; i < ONGs.length; i++) {
            if (ONGs[i] != null && ONGs[i].nombre.equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public int cantidadONG() {
        for (int i = 0; i < ONGs.length; i++) {

            if (ONGs[i] == null) {
                return i;
            }
        }
        return 0;
    }

    public String[] ONGNombres() {
        String[] nombres = new String[cantidadONG()];

        for (int i = 0; i < ONGs.length; i++) {
            if (ONGs[i] != null) {
                nombres[i] = ONGs[i].nombre;
            }
        }
        return nombres;
    }

    public ONG encontrarONG(String username) {

        for (int i = 0; i < ONGs.length; i++) {
            if (ONGs[i] != null && ONGs[i].nombre.equals(username)) {
                return ONGs[i];
            }
        }
        return null;
    }

    public void cambiarNombre(ONG ong, String nuevoNombre) {
        ong.nombre = nuevoNombre;
    }

    public void borrarONG(ONG ong) {

        for (int i = 0; i < ONGs.length; i++) {
            if (ONGs[i] != null && ONGs[i].nombre.equals(ong.nombre)) {
                ONGs[i] = null;
            }
        }
    }

    public String[] listarONGs(){
        String[] lista = new String[cantidadONG()];

        for (int i = 0; i < ONGs.length; i++) {
            if (ONGs[i] != null) {
                lista[i] = "ID:"+(Integer.toString(ONGs[i].id)+" ");
                lista[i] +="Nombre:"+ONGs[i].nombre+" ";
                lista[i] +="Paises de acción:"+ Arrays.toString(ONGs[i].nombresPais)+" ";

            }
        }
        return lista;
    }
}
