package com.company.manager;

import com.company.model.ONG;
import com.company.model.Suscripcion;
import com.company.model.Usuario;
import com.company.view.widget.EditText;

public class ManagerUsuario {

    public Usuario[] usuarios = new Usuario[100];
    public Usuario usuarioConectado;

    public void crearUsuario(String nombre,String apellido,String username,String contraseña,String telefono,
                             String DNI, String correo,int dinero, long cuenta){
        if(!usuarioExiste(nombre)) {
            Usuario usuario = new Usuario();

            usuario.nombre = nombre;
            usuario.apellido = apellido;
            usuario.usuario = username;
            usuario.contraseña = contraseña;
            usuario.telefono = telefono;
            usuario.DNI = DNI;
            usuario.correo = correo;
            usuario.cuenta = cuenta;
            usuario.dinero = dinero;

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

    public void borrarUsuario(Usuario usuario){

        for (int i = 0; i <usuarios.length ; i++) {
            if(usuarios[i]!=null && usuarios[i].usuario.equals(usuario.usuario)){
                usuarios[i]=null;
            }
        }

    }

    public boolean hacerDonacion(ManagerUsuario managerUsuario, int dinero, ONG ong) {
        if(managerUsuario.usuarioConectado.dinero>= dinero){
            for (int i = 0; i <managerUsuario.usuarioConectado.donaciones.length ; i++) {
                if(managerUsuario.usuarioConectado.donaciones[i] == null) {
                    managerUsuario.usuarioConectado.donaciones[i] = "ID# " + (ong.id+1) + " Nombre: " + ong.nombre +" Cantidad: " + dinero;
                    break;
                }
            }

            managerUsuario.usuarioConectado.dinero-=dinero;
            return true;
        }

        return false;
    }

    public boolean suscribirse(ManagerUsuario managerUsuario, int dinero,  ONG ong){
        if(managerUsuario.usuarioConectado.dinero>= dinero){
            for (int i = 0; i <managerUsuario.usuarioConectado.suscripciones.length ; i++) {
                if(managerUsuario.usuarioConectado.suscripciones[i] == null) {
                    managerUsuario.usuarioConectado.suscripciones[i] = "ID# " + (ong.id+1) + " Nombre: " + ong.nombre +" Cantidad mensual: " + dinero;
                    break;
                }
            }

            managerUsuario.usuarioConectado.dinero-=dinero;
            return true;
        }
        return false;
    }

    public boolean suscribirse2(ManagerUsuario managerUsuario, int dinero,  ONG ong){
        if(managerUsuario.usuarioConectado.dinero>= dinero){
            for (int i = 0; i <managerUsuario.usuarioConectado.suscripcions.length ; i++) {
                if(managerUsuario.usuarioConectado.suscripcions[i] == null) {
                    Suscripcion suscripcion = new Suscripcion();
                    suscripcion.id = ong.id+1;
                    suscripcion.nombre = ong.nombre;
                    suscripcion.cantidad = dinero;

                    managerUsuario.usuarioConectado.suscripcions[i] = suscripcion;
                    break;
                }
            }

            managerUsuario.usuarioConectado.dinero-=dinero;
            return true;
        }
        return false;
    }

    public void ingresar(Usuario usuario, int dinero){
        usuario.dinero += dinero;

    }

    public Usuario encontrarUsuario(String username){

        for (int i = 0; i <usuarios.length ; i++) {
            if (usuarios[i]!=null && usuarios[i].usuario.equals(username)){
                return usuarios[i];
            }
        }
        return null;
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
