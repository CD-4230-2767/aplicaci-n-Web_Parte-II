/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import murach.busoness.User;

/**
 * Permite realizar las operaciones CRUD en la entidad
 * user de la base de datos murach.
 * 
 * Esta clase contiene métodos para insertar usuarios
 * y obtener la lista completa de usuarios registrados.
 * 
 * @author alopezorozco
 */
public class UserDB {

    /**
     * Inserta un nuevo usuario en la base de datos.
     * 
     * Este método recibe un objeto de tipo User, obtiene una conexión
     * desde el pool de conexiones, prepara una consulta SQL INSERT
     * y almacena los datos del usuario en la tabla user.
     * 
     * @param user Objeto User que contiene los datos del usuario a insertar.
     * @return int Retorna el número de filas afectadas.
     *         Si el valor es mayor que 0, la inserción fue exitosa.
     *         Si retorna 0, ocurrió un error.
     */
    public static int insert(User user) {
        // Obtiene una instancia del ConnectionPool para gestionar las conexiones a la base de datos
        ConnectionPool pool = ConnectionPool.getInstance();

        // Obtiene una conexión del pool
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        // Consulta SQL para insertar un nuevo usuario en la tabla 'user'
        String query = "INSERT INTO user (Email, FirstName, LastName) "
                + "VALUES (?, ?, ?)";

        try {
            // Prepara la declaración SQL
            ps = connection.prepareStatement(query);

            // Establece los valores de la consulta preparada a partir del objeto User
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());

            // Ejecuta la inserción y retorna el número de filas afectadas
            return ps.executeUpdate();

        } catch (SQLException e) {
            // Si ocurre un error, lo muestra en consola y guarda la descripción
            System.out.println(e);
            Error.descripcion = e.getMessage();
            return 0;

        } finally {
            // Cierra el PreparedStatement y libera la conexión
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    /**
     * Obtiene la lista completa de usuarios registrados en la base de datos.
     * 
     * Este método realiza una consulta SELECT sobre la tabla user,
     * recorre el ResultSet y crea una colección de objetos User
     * con los datos obtenidos.
     * 
     * @return List<User> Retorna una lista de usuarios.
     *         Si ocurre un error, retorna null.
     */
    public static List<User> getAllUsers() {
        // Obtiene una instancia del ConnectionPool para gestionar las conexiones a la base de datos
        ConnectionPool pool = ConnectionPool.getInstance();

        // Obtiene una conexión del pool
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        // Consulta SQL para obtener todos los registros de la tabla user
        String query = "SELECT * FROM user";

        try {
            // Prepara la consulta SQL
            ps = connection.prepareStatement(query);

            // Ejecuta la consulta
            rs = ps.executeQuery();

            // Crea una lista para almacenar los usuarios obtenidos
            ArrayList<User> users = new ArrayList<>();

            // Variable auxiliar para guardar cada usuario leído del ResultSet
            User user = null;

            // Recorre cada fila del resultado
            while (rs.next()) {
                // Crea un nuevo objeto User
                user = new User();

                // Asigna los valores obtenidos desde la base de datos
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));

                // Agrega el usuario a la lista
                users.add(user);
            }

            // Retorna la lista completa de usuarios
            return users;

        } catch (SQLException e) {
            // Si ocurre un error, lo muestra en consola y guarda la descripción
            System.out.println(e);
            Error.descripcion = e.getMessage();
            return null;

        } finally {
            // Cierra el ResultSet, el PreparedStatement y libera la conexión
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}