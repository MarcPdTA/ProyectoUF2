package com.company.manager;

import com.company.Database;
import com.company.model.Donacion;
import com.company.model.ONG;
import com.company.model.Suscripcion;
import com.company.model.Usuario;
import com.company.view.widget.EditText;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class ManagerUsuario {

    public int usuarioConectado;

    public void crearUsuario(String nombre, String apellido, String username, String contraseña, String telefono,
                             String DNI, String correo, int dinero, long cuenta) {
        if (!Database.get().existeUsuario(nombre)) {

            Database.get().insertUsuario(nombre, apellido, username, contraseña, telefono,
                    DNI, correo, dinero, cuenta);
        }
    }

    public String cifrarPassword(String password){

        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }


    public boolean verificarUsuario(String username, String contraseña) {
        contraseña=cifrarPassword(contraseña);
        if (Database.get().verificarUsuario(username, contraseña)){
            usuarioConectado=Database.get().usernameToIdUsuario(username);
            return true;
        }

        return false;

    }

    public boolean borrarUsuario(String username) {

        return Database.get().borrarUsuario(username);

    }

    public boolean hacerDonacion(ManagerUsuario managerUsuario, int dinero, int usuarioID, int ongID) {

        return Database.get().insertDonacion(usuarioID, ongID, dinero);
    }

    public boolean suscribirse(ManagerUsuario managerUsuario, int dinero, int usuarioID, int ongID) {

        return Database.get().insertSuscripcion(usuarioID, ongID, dinero);
    }

    public void ingresar(int usuarioID, int dinero) {
        Database.get().ingresarDinero(usuarioID, dinero);

    }


    public boolean concederPermisoAdministrador(String username) {
        if(Database.get().existeUsuario(username)){
            return Database.get().concederPermisoAdministrador(username);
        }
        return false;
    }

    public boolean cambiarNombre(String username, String nombreNuevo) {
        if(Database.get().existeUsuario(username)){
            return Database.get().cambiarNombreUsuario(username, nombreNuevo);
        }
        return false;
    }

    public boolean cambiarApellido(String username, String nuevoApellido) {
        if(Database.get().existeUsuario(username)){
            return Database.get().cambiarApellidoUsuario(username, nuevoApellido);
        }
        return false;
    }

    public boolean cambiarNombreUsuario(String username, String nuevoNombreUsuario) {
        if(Database.get().existeUsuario(username)){
            return Database.get().cambiarUsernameUsuario(username, nuevoNombreUsuario);
        }
        return false;
    }

    public boolean cambiarContraseña(String username, String nuevaContraseña) {
        if(Database.get().existeUsuario(username)){
            return Database.get().cambiarContraseñaUsuario(username, nuevaContraseña);
        }
        return false;
    }

    public boolean cambiarDNI(String username, String nuevoDNI) {
        if(Database.get().existeUsuario(username)){
            return Database.get().cambiarDNIUsuario(username, nuevoDNI);
        }
        return false;
    }


    public boolean cambiarCorreo(String username, String nuevoCorreo) {
        if(Database.get().existeUsuario(username)){
            return Database.get().cambiarCorreoUsuario(username, nuevoCorreo);
        }
        return false;

    }

    public boolean cambiarTelefono(String username, String nuevoTelefono) {
        if(Database.get().existeUsuario(username)){
            return Database.get().cambiarTelefonoUsuario(username, nuevoTelefono);
        }
        return false;

    }

    public int cantidadUsuarios() {
        return Database.get().contarUsuarios();
    }

    public List<Usuario> listaUsuarios() {

        return Database.get().selectTodosUsuarios();
    }


    public int devolverIntValue(String parametro,int id){

        return Database.get().devolverIntValueUsuario(parametro,id);

    }

    public String devolverStringValue(String parametro,int id){

        return Database.get().devolverStringValueUsuario(parametro,id);

    }

    public List<Donacion> donacionesUsuarioConectado(){
        return Database.get().donacionesUsuarioConectado(usuarioConectado);
    }

    public List<Donacion> suscripcionesUsuarioConectado(){
        return Database.get().suscripcionesUsuarioConectado(usuarioConectado);
    }

}
