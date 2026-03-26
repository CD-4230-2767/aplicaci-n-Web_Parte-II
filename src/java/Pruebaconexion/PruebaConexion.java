package Pruebaconexion;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author user
 */

public class PruebaConexion {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:8090/murach?useSSL=false&serverTimezone=UTC";
        String user = "murach";
        String password = "campusjalpa";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa a MySQL");
            conn.close();
        } catch (Exception e) {
            System.out.println("Error de conexión:");
            e.printStackTrace();
        }
    }
}
