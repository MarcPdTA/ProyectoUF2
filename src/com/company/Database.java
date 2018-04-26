package com.company;


import com.company.model.ONG;

import javax.xml.soap.Text;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
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
            stmt.execute("CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre text, apellido text, usuario text, contraseña text, telefono text, DNI text, correo text, dinero INTEGER, admin INTEGER DEFAULT 0);");
            stmt.execute("CREATE TABLE IF NOT EXISTS suscripciones (usuarioID INTEGER NOT NULL,ongID INTEGER NOT NULL, dinero INTEGER);");
            stmt.execute("CREATE TABLE IF NOT EXISTS donaciones (usuarioID INTEGER NOT NULL,ongID INTEGER NOT NULL, dinero INTEGER);");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//
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
        String sql = "SELECT nombre FROM usuarios WHERE nombre = ?";
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

    public boolean insertUsuario(String nombre,String apellido,String username,String contraseña,String telefono,
                                 String DNI, String correo,int dinero, long cuenta) {
        String sql = "INSERT INTO usuarios(nombre, apellidos, username, contraseña, telefono, DNI, correo, dinero, cuenta) VALUES(?,?,?,?,?,?,?,?,?)";

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

                    if(rs.getString("usuario").equals(username)){
                        return true;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
            return true;
        }

        return false;
    }

    public boolean borrarUsuario(String username){
        if(existeONG(username)){
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
        String sql = "INSERT INTO donaciones(usuarioID, ongID, dinero) VALUES(?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioID);
            pstmt.setInt(2, ongID);
            pstmt.setInt(3, dinero);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean insertSuscripcion(int usuarioID, int ongID,int dinero) {
        String sql = "INSERT INTO suscripciones(usuarioID, ongID, dinero) VALUES(?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioID);
            pstmt.setInt(2, ongID);
            pstmt.setInt(3, dinero);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean ingresarDinero(int usuarioID,int dinero) {
        String sql = "UPDATE usuarios SET dinero = ? WHERE usuarioID = ?";

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
}


