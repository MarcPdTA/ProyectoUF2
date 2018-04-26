package com.company.manager;

import com.company.Database;
import com.company.model.ONG;
import com.company.model.Suscripcion;
import com.company.model.Usuario;
import com.company.view.widget.EditText;

public class ManagerUsuario {

    int usuarioConectado;

    public void crearUsuario(String nombre,String apellido,String username,String contraseña,String telefono,
                             String DNI, String correo,int dinero, long cuenta){
        if(!Database.get().existeUsuario(nombre)) {

            Database.get().insertUsuario(nombre,apellido,username,contraseña,telefono,
                    DNI,correo,dinero,cuenta);
            }
        }

    public boolean usuarioExiste(String username){

        return Database.get().existeUsuario(username);

        }

    public boolean verificarUsuario(String username, String contraseña){

        return Database.get().verificarUsuario(username,contraseña);

    }

    public boolean borrarUsuario(String usuario){

      return Database.get().borrarUsuario(usuario);

    }

    public boolean hacerDonacion(ManagerUsuario managerUsuario, int dinero, int usuarioID, int ongID) {

        return Database.get().insertDonacion(usuarioID, ongID, dinero);
    }

    public boolean suscribirse(ManagerUsuario managerUsuario, int dinero, int usuarioID, int ongID){

        return Database.get().insertSuscripcion(usuarioID,ongID,dinero);
    }

    public void ingresar(int usuarioID, int dinero){
        Database.get().ingresarDinero(usuarioID,dinero);

    }


    public void concederPermisoAdministrador(Usuario usuario){
        usuario.admin=true;
    }

    public void cambiarNombre(Usuario usuario,String nuevoNombre){
        usuario.nombre=nuevoNombre;
    }

    public void cambiarApellido(Usuario usuario,String nuevoApellido){
        usuario.apellido=nuevoApellido;
    }

    public void cambiarNombreUsuario(Usuario usuario,String nuevoNombreUsuario){
        usuario.usuario=nuevoNombreUsuario;
    }

    public void cambiarContraseña(Usuario usuario,String nuevaContraseña){
        usuario.contraseña=nuevaContraseña;
    }

    public void cambiarDNI(Usuario usuario, String nuevoDNI) { usuario.DNI = nuevoDNI; }

    public void cambiarCorreo(Usuario usuario, String nuevoCorreo) { usuario.correo = nuevoCorreo; }

    public void cambiarTelefono(Usuario usuario, String nuevoTelefono) { usuario.telefono = nuevoTelefono; }

    public void crearUsuarioAdmin(){
        String nombre = new EditText("Nombre: ").pedirString();
        String apellido = new EditText("Apellidos: ").pedirString();
        String contraseña = new EditText("Contraseña: ").pedirString();
        String usuario = new EditText("Nombre de usuario: ").pedirString();
        String telefono = new EditText("Telefono: ").pedirString();
        String DNI = new EditText("DNI: ").pedirString();
        String correo = new EditText("Correo electronico: ").checkEmail();
        int dinero = new EditText("Ingreso inicial: ").pedirInt(0, 1000000000);
        long cuenta = new EditText("Cuenta bancaria: ").pedirLong();

        crearUsuario(nombre,apellido,usuario,contraseña,telefono,DNI,correo,dinero,cuenta);
    }

    public int cantidadUsuarios() {
        for (int i = 0; i < usuarios.length; i++) {

            if (usuarios[i] == null) {
                return i;
            }
        }
        return 0;
    }

    public String[] listaUsuarios() {
        String[] lista = new String[cantidadUsuarios()];

        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] != null) {
                lista[i] = "ID:"+(Integer.toString(usuarios[i].id)+" ");
                lista[i] +="Username:"+usuarios[i].usuario+" ";
                lista[i] +="Nombre:"+usuarios[i].nombre+" ";
                lista[i] +="Apellido:"+usuarios[i].apellido+" ";
            }
        }
        return lista;
    }

}
