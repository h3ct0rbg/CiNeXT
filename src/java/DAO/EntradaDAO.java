package DAO;

import Model.Entrada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntradaDAO {

    private Connection conexion;

    public EntradaDAO() {
        // Conexión a la base de datos
        this.conexion = ConexionDB.obtenerConexion();
    }
    
    // Método para mostrar todas las entradas
    public List<Entrada> getAllEntradas() {
        List<Entrada> entradas = new ArrayList<>();

        try {
            // Consulta SQL para obtener todas las entradas del usuario
            String consulta = "SELECT E.ID, H.FECHA, FILA, COLUMNA, U.NOMBRE, E.USUARIOEMAIL, SESIONID, S.NOMBRE, P.TITULO "
                    + "FROM ENTRADAS E "
                    + "INNER JOIN USUARIOS U ON E.USUARIOEMAIL = U.EMAIL "
                    + "INNER JOIN SESIONES H ON E.SESIONID = H.ID "
                    + "INNER JOIN SALAS S ON H.SALAID = S.ID "
                    + "INNER JOIN PELICULAS P ON H.PELICULAID = P.ID "
                    + "ORDER BY H.FECHA;";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Crear objeto Entrada con los datos obtenidos de la base de datos
                int id = resultSet.getInt(1);
                Date fecha = resultSet.getDate(2);
                int fila = resultSet.getInt(3);
                int columna = resultSet.getInt(4);
                String nombreUsuario = resultSet.getString(5);
                String usuarioEmail = resultSet.getString(6);
                int sesionId = resultSet.getInt(7);
                String sala = resultSet.getString(8);
                String tituloPelicula = resultSet.getString(9);

                Entrada entrada = new Entrada(id, sesionId, fila, columna, usuarioEmail, nombreUsuario, fecha, sala, tituloPelicula);
                entradas.add(entrada);
            }

        } catch (SQLException e) {
            // Manejar la excepción
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
        return entradas;
    }

    // Método para obtener una entrada por su ID
    public Entrada getEntradaById(int idEntrada, String email) {
        Entrada entrada = null;

        try {
            // Consulta SQL para obtener una entrada por su ID
            String consulta = "SELECT id, sesionId, fila, columna, usuarioEmail FROM entradas WHERE id=? AND usuarioEmail=?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, idEntrada);
            statement.setString(2, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Crear objeto Entrada con los datos obtenidos de la base de datos
                int id = resultSet.getInt("id");
                int sesionId = resultSet.getInt("sesionId");
                int fila = resultSet.getInt("fila");
                int columna = resultSet.getInt("columna");
                String usuarioEmail = resultSet.getString("usuarioEmail");

                entrada = new Entrada(id, sesionId, fila, columna, usuarioEmail);
            }

        } catch (SQLException e) {
            // Manejar la excepción
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
        return entrada;
    }

    // Método para mostrar todas las entradas de un usuario
    public List<Entrada> mostrarEntradas(String email) {
        List<Entrada> entradas = new ArrayList<>();

        try {
            // Consulta SQL para obtener todas las entradas del usuario
            String consulta = "SELECT id, sesionId, fila, columna, usuarioEmail FROM entradas WHERE usuarioEmail=?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Crear objeto Entrada con los datos obtenidos de la base de datos
                int id = resultSet.getInt("id");
                int sesionId = resultSet.getInt("sesionId");
                int fila = resultSet.getInt("fila");
                int columna = resultSet.getInt("columna");
                String usuarioEmail = resultSet.getString("usuarioEmail");

                Entrada entrada = new Entrada(id, sesionId, fila, columna, usuarioEmail);
                entradas.add(entrada);
            }

        } catch (SQLException e) {
            // Manejar la excepción
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
        return entradas;
    }

    // Método para insertar una nueva entrada en la base de datos
    public int insertarEntrada(Entrada entrada) {
        try {
            // Consulta SQL para insertar una nueva entrada
            String consulta = "INSERT INTO entradas (sesionid, fila, columna, usuarioemail) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            
            // Establecer los valores de los parámetros de la consulta
            statement.setInt(1, entrada.getSesionId());
            statement.setInt(2, entrada.getFilas());
            statement.setInt(3, entrada.getColumnas());
            statement.setString(4, entrada.getEmail());
            System.out.println("Holaaa!");
            // Ejecutar la consulta
            int filasAfectadas = statement.executeUpdate();
            System.out.println("Holaaa!");
            // Verificar si se insertó correctamente
            return (filasAfectadas > 0) ? 1 : 0;

        } catch (SQLException e) {
            return 0;
        } finally {
            //ConexionDB.cerrarConexion(conexion);
        }
    }
}