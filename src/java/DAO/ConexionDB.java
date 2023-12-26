package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:derby://localhost:1527/cine";
    private static final String USUARIO = "app";
    private static final String CONTRASENA = "app";

    // Método para obtener una conexión a la base de datos
    public static Connection obtenerConexion() {
        try {
            // Cargar el driver de la base de datos
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            // Establecer la conexión
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }
}