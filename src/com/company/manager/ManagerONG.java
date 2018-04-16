package com.company.manager;

import com.company.model.ONG;
import com.company.model.Usuario;

public class ManagerONG {
    public ONG[] ONGs = new ONG[100];
    public void crearONG(String nombre, String ... nombresPais){
        if(!ONGExiste(nombre)) {
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
        for (int i = 0; i <ONGs.length ; i++) {
            if(ONGs[i]!=null && ONGs[i].nombre.equals(nombre)){
                return true;
            }
        }
        return false;
    }

    public int cantidadONG(){
        for (int i = 0; i <ONGs.length ; i++) {

            if(ONGs[i]== null){
                return i;
            }
        }
        return 0;
    }

    public String[] ONGNombres() {
        String[] nombres = new String[cantidadONG()];

        for (int i = 0; i <ONGs.length ; i++) {
            if(ONGs[i]!=null ){
                nombres[i]=ONGs[i].nombre;
            }
        }
        return nombres;
    }


    public ONG encontrarONG(String encontrarONG){

        for (int i = 0; i <ONGs.length ; i++) {
            if (ONGs[i]!=null && ONGs[i].nombre.equals(encontrarONG)){
                return ONGs[i];
            }
        }
        return null;
    }

    public void cambiarNombreONG(ONG ong, String nuevoNombreONG){
        ong.nombre=nuevoNombreONG;
    }


    public void cambiarNombrePaisONG(ONG ong, String nuevoNombrePaisONG){
        ong.nombre=nuevoNombrePaisONG;
    }



}
