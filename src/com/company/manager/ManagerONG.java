package com.company.manager;

import com.company.Database;
import com.company.model.ONG;
import com.company.model.Usuario;

import java.util.Arrays;

public class ManagerONG {

    public void crearONG(String nombre, String pais) {
        if (!Database.get().existeONG(nombre)) {

            Database.get().insertONG(nombre, pais);
        }
    }


    public boolean cambiarNombre(String viejoNombre, String nuevoNombre) {

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
