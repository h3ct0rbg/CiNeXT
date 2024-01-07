package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/cinext";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "root";

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

    // Método para cerrar la conexión
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error al cerrar la conexión", e);
            }
        }
    }
}