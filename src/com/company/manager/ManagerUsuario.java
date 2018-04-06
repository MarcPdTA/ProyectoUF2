package com.company.manager;

import com.company.model.Usuario;

public class ManagerUsuario {

    Usuario[] usuarios = new Usuario[100];
    public Usuario usuarioConectado;

    public void crearUsuario(String nombre,String apellido,String username,String contraseña,String telefono,
                             String DNI, String correo, long cuenta){
        if(!usuarioExiste(nombre)) {
            Usuario usuario = new Usuario();

            usuario.nombre = nombre;
            usuario.apellido = apellido;
            usuario.usuario = username;
            usuario.contraseña=contraseña;
            usuario.telefono = telefono;
            usuario.DNI = DNI;
            usuario.correo = correo;
            usuario.cuenta = cuenta;

            for (int i = 0; i < usuarios.length; i++) {
                if (usuarios[i] == null) {
                    usuario.id = i;
                    usuarios[i] = usuario;
                    break;
                }

            }
        }
    }

    public boolean usuarioExiste(String nombre){

        for (int i = 0; i <usuarios.length ; i++) {
            if(usuarios[i]!=null && usuarios[i].nombre.equals(nombre)){return true;
            }
        }

        return false;
    }

    public boolean verificarUsuario(String username, String contraseña){

        for (int i = 0; i <usuarios.length ; i++) {
            if(usuarios[i]!=null && usuarios[i].usuario.equals(username) && usuarios[i].contraseña.equals(contraseña)){
                usuarioConectado=usuarios[i];
                return true;
            }
        }

        return false;

    }

    public void borrarUsuario(String username){

        for (int i = 0; i <usuarios.length ; i++) {
            if(usuarios[i]!=null && usuarios[i].usuario.equals(username)){
                usuarios[i]=null;
            }
        }

    }

}
