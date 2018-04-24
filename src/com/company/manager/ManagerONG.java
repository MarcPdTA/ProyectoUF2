package com.company.manager;

import com.company.Database;
import com.company.model.ONG;
import com.company.model.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagerONG {

    public void crearONG(String nombre, String pais) {
        if (!Database.get().existeONG(nombre)) {

            Database.get().insertONG(nombre, pais);
        }
    }


    public boolean cambiarNombre(String nombreViejo, String nombreNuevo) {
        return Database.get().cambiarNombreONG(nombreViejo, nombreNuevo);
    }


    public void borrarONG(ONG ong) {

        for (int i = 0; i < ONGs.length; i++) {
            if (ONGs[i] != null && ONGs[i].nombre.equals(ong.nombre)) {
                ONGs[i] = null;
            }
        }
    }

    public List<String> listarONGs() {
        List<ONG> lista = Database.get().selectTodasONGS();

        List<String> listaNombres = new ArrayList<>();


        for(ONG ong: lista){
            listaNombres.add(ong.nombre);
        }

        return listaNombres;

    }
}