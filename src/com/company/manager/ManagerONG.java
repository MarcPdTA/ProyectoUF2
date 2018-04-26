package com.company.manager;

import com.company.Database;
import com.company.model.ONG;
import com.company.model.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagerONG {

    public boolean crearONG(String nombre, String pais) {
        if (!Database.get().existeONG(nombre)) {
            return Database.get().insertONG(nombre, pais);
        }
        return false;
    }


    public boolean cambiarNombre(String nombreViejo, String nombreNuevo) {
        return Database.get().cambiarNombreONG(nombreViejo, nombreNuevo);
    }


    public boolean borrarONG(String nombreONG) {
        return Database.get().borrarONG(nombreONG);
    }

    public List<ONG> listarONGs() {
        return Database.get().selectTodasONGS();

    }

    public int contarONGs(){
        return Database.get().contarONGs();
    }

    public String idToStringONG(int id){
        return Database.get().id_nombreONG(id);
    }
}
