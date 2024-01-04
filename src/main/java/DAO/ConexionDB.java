package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/Cine";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "1234";

    // Método para obtener una conexión a la base de datos
    public static Connection obtenerConexion() {
        try {
            // Cargar el driver de la base de datos
            Class.forName("org.postgresql.Driver");

            // Establecer la conexión
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }
}