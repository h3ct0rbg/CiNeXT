package DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class UsuarioDAO {
    // Método para registrar un nuevo usuario
    public int registrarUsuario(String nombre, String email, String password) {
        int result = 0;
        try (Connection connection = ConexionDB.obtenerConexion()) {
            // Verificar si el usuario ya existe
            if (existeUsuario(email)) {
                ConexionDB.cerrarConexion(connection);
                result = 2;
            }

            // Insertar el nuevo usuario en la base de datos
            String query = "INSERT INTO Usuarios (Nombre, Email, Contraseña, TipoUsuario) VALUES (?, ?, ?, 'Cliente')";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();
                ConexionDB.cerrarConexion(connection);
                result = 1;
            }
        } catch (SQLException e) {}
        
        return result; // Devolver 0 si no se consigue insertar el usuario en la BBDD
    }
    
        // Método para iniciar sesión
    public Usuario iniciarSesion(String email, String password) {
        try (Connection connection = ConexionDB.obtenerConexion()) {
            // Verificar las credenciales del usuario
            String query = "SELECT * FROM Usuarios WHERE Email = ? AND Contraseña = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Crear un objeto Usuario con la información obtenida de la BBDD
                        Usuario usuario = new Usuario();
                        usuario.setNombre(resultSet.getString("Nombre"));
                        usuario.setEmail(resultSet.getString("Email"));
                        usuario.setTipoUsuario(resultSet.getString("TipoUsuario"));
                        ConexionDB.cerrarConexion(connection);
                        return usuario;
                    }
                }
            }
        } catch (SQLException e) {}

        return null; // Devolver null si las credenciales son incorrectas o hay un error
    }

    // Método para verificar si un usuario ya existe
    private boolean existeUsuario(String email) {
        // Inicializar la variable que indicará si el usuario existe
        boolean usuarioExiste = false;
        
        // Obtener la conexión
        Connection connection = ConexionDB.obtenerConexion();
        
        try {
            // Consulta SQL para verificar si el usuario existe
            String consulta = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
            
            try (PreparedStatement pstmt = connection.prepareStatement(consulta)) {
                pstmt.setString(1, email);

                // Ejecutar la consulta
                try (ResultSet rs = pstmt.executeQuery()) {
                    // Obtener el resultado
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        // Si count es mayor que 0, el usuario existe
                        usuarioExiste = count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar si el usuario existe", e);

        } finally {
            // Cerrar la conexión
            ConexionDB.cerrarConexion(connection);
        }

        // Devolver si el usuario existe o no
        return usuarioExiste;
    }
}