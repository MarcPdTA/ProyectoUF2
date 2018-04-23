package com.company;


import com.company.model.ONG;

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
            stmt.execute("CREATE TABLE IF NOT EXISTS usuarios (nombre text);");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertONG(String nombre, String pais) {
        String sql = "INSERT INTO ongs(nombre, pais) VALUES(?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, pais);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectEstudiantesConNotaSuperiorA(double nota){
        String sql = "SELECT nombre, nota FROM estudiantes WHERE nota > ?";

        try (PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setDouble(1, nota);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("nombre") + "\t" +
                        rs.getDouble("nota"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //return new Estudiante[1];
    }

    public List<ONG> selectTodasONGS(){
        String sql = "SELECT * FROM ongs";

        List<ONG> resultado = new ArrayList<>();

        try (Statement stmt  = conn.createStatement()){

            ResultSet rs  = stmt.executeQuery(sql);

            while (rs.next()) {
                ONG ong = new ONG();
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
        String sql = "SELECT nombre FROM ongs";
        try (PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setString(1, nombre);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {

                if(rs.getString("nombre").equals(nombre)){
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public String encontrarONG(String nombre){

            String sql = "SELECT nombre FROM ongs";
            try (PreparedStatement pstmt  = conn.prepareStatement(sql)){

                pstmt.setString(1, nombre);
                ResultSet rs  = pstmt.executeQuery();

                while (rs.next()) {
                    String nombreOng=rs.getString("nombre");

                    if(nombreOng.equals(nombre)){
                        return nombreOng;
                    }
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return null;
    }
}

