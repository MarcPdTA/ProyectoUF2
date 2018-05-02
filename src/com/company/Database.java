package com.company;


import com.company.model.Donacion;
import com.company.model.Mensaje;
import com.company.model.ONG;
import com.company.model.Usuario;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Database {
    static final String url = "jdbc:sqlite:database.db";
    static final int DATABASE_VERSION = 4;

    static Database instance;
    static Connection conn;

    public static Database get(){
        if(instance == null){
            instance = new Database();

            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            if(instance.getVersion() != DATABASE_VERSION){
                System.out.println("VERSION = " + instance.getVersion());
                instance.upgradeDatabase();
                instance.setVersion();
            }
        }
        return instance;
    }

    public int getVersion(){
        try (Statement stmt  = conn.createStatement()){
            ResultSet rs  = stmt.executeQuery("PRAGMA user_version");
            while (rs.next()) {
                return rs.getInt("user_version");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public void setVersion(){
        try (Statement stmt  = conn.createStatement()){
            stmt.execute("PRAGMA user_version = " + DATABASE_VERSION);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void upgradeDatabase(){
        deleteTables();
        createTables();
    }

    void deleteTables(){
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS ongs;");
            stmt.execute("DROP TABLE IF EXISTS grupos;");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void createTables(){
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS ongs (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,nombre text, pais text);");
            stmt.execute("CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre text, apellido text, usuario text, contraseña text, telefono text, DNI text, correo text, dinero INTEGER, cuenta INTEGER, admin INTEGER DEFAULT 0);");
            stmt.execute("CREATE TABLE IF NOT EXISTS suscripciones (usuarioID INTEGER NOT NULL,ongID INTEGER NOT NULL, dinero INTEGER);");
            stmt.execute("CREATE TABLE IF NOT EXISTS donaciones (usuarioID INTEGER NOT NULL,ongID INTEGER NOT NULL, dinero INTEGER, fecha text);");
            stmt.execute("CREATE TABLE IF NOT EXISTS mensajes (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, emisorID INTEGER, receptorID INTEGER, mensaje text, leido INTEGER DEFAULT 0,fecha text);");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//

    public boolean suficienteDinero(int usuarioID, int dinero) {

        String sql = "SELECT dinero FROM usuarios WHERE ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioID);

            ResultSet rs  = pstmt.executeQuery();
            while (rs.next()) {
                int dineroActual=rs.getInt("dinero");
                if(dineroActual>=dinero){
                    return true;
                }
                else{
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;

    }
        public boolean insertONG(String nombre, String pais) {
        String sql = "INSERT INTO ongs(nombre, pais) VALUES(?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, pais);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public List<ONG> selectTodasONGS(){
        String sql = "SELECT * FROM ongs";

        List<ONG> resultado = new ArrayList<>();

        try (Statement stmt  = conn.createStatement()){

            ResultSet rs  = stmt.executeQuery(sql);

            while (rs.next()) {
                ONG ong = new ONG();
                ong.id = rs.getInt("id");
                ong.nombre = rs.getString("nombre");
                ong.pais = rs.getString("pais");

                resultado.add(ong);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }

    public boolean existeONG(String nombre){
        String sql = "SELECT nombre FROM ongs WHERE nombre = ?";
        try (PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1, nombre);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {

                if(rs.getString("nombre").equals(nombre)){
                    return true;
                }
            }
            return false;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean cambiarNombreONG(String nombreViejo, String nombreNuevo){
        if(existeONG(nombreViejo)){
            String sql = "UPDATE ongs SET nombre = ? WHERE nombre = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombreNuevo);
                pstmt.setString(2, nombreViejo);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }
    public boolean borrarONG(String nombreONG){
        if(existeONG(nombreONG)){
            String sql = "DELETE FROM ongs WHERE nombre = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombreONG);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }

        return false;
    }

    public int contarONGs(){
        String sql = "SELECT COUNT(*) AS cuenta FROM ongs";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery(); /////////////////////////DUDA A GERARD SOBRE ESTO.
            while (rs.next()){
                return rs.getInt("cuenta");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return 0;
    }

    public String id_nombreONG(int id){
        String sql = "SELECT nombre FROM ongs WHERE id= ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                return rs.getString("nombre");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }


    public boolean existeUsuario(String usuario){
        String sql = "SELECT usuario FROM usuarios WHERE usuario = ?";
        try (PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1, usuario);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {

                if(rs.getString("usuario").equals(usuario)){
                    return true;
                }
            }
            return false;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean existeUsuario(int id){
        String sql = "SELECT id FROM usuarios WHERE id = ?";
        try (PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setInt(1, id);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {

                if(rs.getInt("id")==(id)){
                    return true;
                }
            }
            return false;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean insertUsuario(String nombre,String apellido,String username,String contraseña,String telefono,
                                 String DNI, String correo,int dinero, long cuenta) {
        String sql = "INSERT INTO usuarios(nombre, apellido, usuario, contraseña, telefono, DNI, correo, dinero, cuenta) VALUES(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, username);
            pstmt.setString(4, contraseña);
            pstmt.setString(5, telefono);
            pstmt.setString(6, DNI);
            pstmt.setString(7, correo);
            pstmt.setInt(8, dinero);
            pstmt.setLong(9, cuenta);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean verificarUsuario(String username, String contraseña){
        if(existeUsuario(username)){
            String sql = "SELECT usuario FROM usuarios WHERE usuario = ? AND contraseña = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, contraseña);

                ResultSet rs  = pstmt.executeQuery();

                while (rs.next()) {
                    return true;

                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        return false;
    }

    public boolean borrarUsuario(String username){
        if(existeUsuario(username)){
            String sql = "DELETE FROM usuarios WHERE usuario = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }

        return false;
    }

    public boolean insertDonacion(int usuarioID, int ongID,int dinero) {
        String sql = "INSERT INTO donaciones(usuarioID, ongID, dinero, fecha) VALUES(?,?,?,?)";
        if(suficienteDinero(usuarioID,dinero)){

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date today = Calendar.getInstance().getTime();
            String fecha = df.format(today);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, usuarioID);
                pstmt.setInt(2, ongID);
                pstmt.setInt(3, dinero);
                pstmt.setString(4,fecha);

                pstmt.executeUpdate();
                retirarDinero(usuarioID,dinero);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
                }
        }
        return false;
    }

    public boolean insertSuscripcion(int usuarioID, int ongID,int dinero) {
        String sql = "INSERT INTO suscripciones(usuarioID, ongID, dinero) VALUES(?,?,?)";
        if(suficienteDinero(usuarioID,dinero)) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, usuarioID);
                pstmt.setInt(2, ongID);
                pstmt.setInt(3, dinero);

                pstmt.executeUpdate();
                retirarDinero(usuarioID,dinero);
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean ingresarDinero(int usuarioID,int dinero) {
        String sql = "UPDATE usuarios SET dinero = dinero + ? WHERE ID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dinero);
            pstmt.setInt(2, usuarioID);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean retirarDinero(int usuarioID,int dinero) {
        String sql = "UPDATE usuarios SET dinero = dinero - ? WHERE ID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dinero);
            pstmt.setInt(2, usuarioID);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean concederPermisoAdministrador(String username) {
        String sql = "UPDATE usuarios SET admin = 1 WHERE usuario = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;

        }
       return true;
    }

    public boolean cambiarNombreUsuario(String username, String nombreNuevo){
        if(existeUsuario(username)){
            String sql = "UPDATE usuarios SET nombre = ? WHERE usuario = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombreNuevo);
                pstmt.setString(2, username);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean cambiarApellidoUsuario(String username, String nombreNuevo){
        if(existeUsuario(username)){
            String sql = "UPDATE usuarios SET apellido = ? WHERE usuario = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombreNuevo);
                pstmt.setString(2, username);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean cambiarUsernameUsuario(String username, String usernameNuevo){
        if(existeUsuario(username)){
            String sql = "UPDATE usuarios SET usuario = ? WHERE usuario = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, usernameNuevo);
                pstmt.setString(2, username);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean cambiarContraseñaUsuario(String username, String contraseñaNueva){
        if(existeUsuario(username)){
            String sql = "UPDATE usuarios SET contraseña = ? WHERE usuario = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, contraseñaNueva);
                pstmt.setString(2, username);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean cambiarTelefonoUsuario(String username, String telefonoNuevo){
        if(existeUsuario(username)){
            String sql = "UPDATE usuarios SET telefono = ? WHERE usuario = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, telefonoNuevo);
                pstmt.setString(2, username);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean cambiarDNIUsuario(String username, String DNInuevo){
        if(existeUsuario(username)){
            String sql = "UPDATE usuarios SET DNI = ? WHERE usuario = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, DNInuevo);
                pstmt.setString(2, username);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean cambiarCorreoUsuario(String username, String correoNuevo){
        if(existeUsuario(username)){
            String sql = "UPDATE usuarios SET correo = ? WHERE usuario = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, correoNuevo);
                pstmt.setString(2, username);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }

    public int contarUsuarios(){
        String sql = "SELECT COUNT(*) AS cuenta FROM usuarios";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                return rs.getInt("cuenta");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return 0;
    }

    public List<Usuario> selectTodosUsuarios(){
        String sql = "SELECT * FROM usuarios";

        List<Usuario> resultado = new ArrayList<>();

        try (Statement stmt  = conn.createStatement()){

            ResultSet rs  = stmt.executeQuery(sql);

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.id = rs.getInt("id");
                usuario.nombre = rs.getString("nombre");
                usuario.apellido = rs.getString("apellido");
                usuario.usuario = rs.getString("usuario");
                usuario.contraseña = rs.getString("contraseña");
                usuario.telefono = rs.getString("telefono");
                usuario.DNI = rs.getString("DNI");
                usuario.correo = rs.getString("correo");
                usuario.dinero = rs.getInt("dinero");
                usuario.cuenta=rs.getLong("cuenta");
                usuario.admin = rs.getInt("admin");

                resultado.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }


    public int usernameToIdUsuario(String username){
        String sql = "SELECT id FROM usuarios WHERE usuario = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return 0;
    }

    public int devolverIntValueUsuario(String parametro, int id){
        String sql = "SELECT "+parametro+" AS respuesta FROM usuarios WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                return rs.getInt("respuesta");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return 0;
    }

    public String devolverStringValueUsuario(String parametro, int id){
        String sql = "SELECT "+parametro+" AS respuesta FROM usuarios WHERE id= ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                return rs.getString("respuesta");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    public List<Donacion> donacionesUsuarioConectado(int id){

        String sql = "SELECT * FROM donaciones WHERE usuarioID = ?";

        List<Donacion> resultado = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);

            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                Donacion donacion = new Donacion();
                donacion.usuarioID=rs.getInt("usuarioID");
                donacion.ongID=rs.getInt("ongID");
                donacion.dinero=rs.getInt("dinero");
                donacion.fecha=rs.getString("fecha");

                resultado.add(donacion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public List<Donacion> suscripcionesUsuarioConectado(int id){
        String sql = "SELECT * FROM suscripciones WHERE usuarioID = ?";

        List<Donacion> resultado = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);

            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                Donacion donacion = new Donacion();
                donacion.usuarioID=rs.getInt("usuarioID");
                donacion.ongID=rs.getInt("ongID");
                donacion.dinero=rs.getInt("dinero");

                resultado.add(donacion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public boolean enviarMensaje(int emisorID, String receptor, String mensaje){

        if(existeUsuario(receptor)){
            String sql = "INSERT INTO mensajes(emisorID, receptorID, mensaje, fecha) VALUES(?,?,?,?)";

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date today = Calendar.getInstance().getTime();
            String fecha = df.format(today);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, emisorID);
                pstmt.setInt(2, usernameToIdUsuario(receptor));
                pstmt.setString(3, mensaje);
                pstmt.setString(4, fecha);
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }

        }

        return false;
    }

    public List<Mensaje> selectMensajesUsuario(int receptorID){
        String sql = "SELECT * FROM mensajes WHERE receptorID ="+receptorID;

        List<Mensaje> resultado = new ArrayList<>();

        try (Statement stmt  = conn.createStatement()){

            ResultSet rs  = stmt.executeQuery(sql);

            while (rs.next()) {
                Mensaje mensaje = new Mensaje();
                mensaje.id = rs.getInt("id");
                mensaje.receptorID = rs.getInt("receptorID");
                mensaje.emisorID = rs.getInt("emisorID");
                mensaje.mensaje = rs.getString("mensaje");
                mensaje.leido = rs.getInt("leido");
                mensaje.fecha= rs.getString("fecha");

                resultado.add(mensaje);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }

    public List<Mensaje> selectMensajesNoLeidosUsuario(int receptorID){
        String sql = "SELECT * FROM mensajes WHERE receptorID ="+receptorID+" AND leido=0";

        List<Mensaje> resultado = new ArrayList<>();

        try (Statement stmt  = conn.createStatement()){

            ResultSet rs  = stmt.executeQuery(sql);

            while (rs.next()) {
                Mensaje mensaje = new Mensaje();
                mensaje.id = rs.getInt("id");
                mensaje.receptorID = rs.getInt("receptorID");
                mensaje.emisorID = rs.getInt("emisorID");
                mensaje.mensaje = rs.getString("mensaje");
                mensaje.leido = rs.getInt("leido");
                mensaje.fecha= rs.getString("fecha");


                resultado.add(mensaje);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }

    public List<Mensaje> marcarMensajesLeidos(int receptorID){
        String sql = "UPDATE mensajes SET leido = 1 WHERE receptorID ="+receptorID;

        List<Mensaje> resultado = new ArrayList<>();

        try (Statement stmt  = conn.createStatement()){
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }
    public int contarMensajesNoLeidos(int receptorID){
        String sql = "SELECT COUNT(*) AS cuenta FROM mensajes WHERE receptorID="+receptorID+" AND leido=0";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                return rs.getInt("cuenta");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return 0;
    }

    public boolean enviarMensajeGlobal(int emisorID, String mensaje){
            String sql = "INSERT INTO mensajes(emisorID, receptorID, mensaje, fecha) VALUES(?,?,?,?)";

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date today = Calendar.getInstance().getTime();
            String fecha = df.format(today);

        for (int i = 1; i <=contarUsuarios(); i++) {
            if (existeUsuario(i)) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, emisorID);
                    pstmt.setInt(2, i);
                    pstmt.setString(3, mensaje);
                    pstmt.setString(4, fecha);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }
}





