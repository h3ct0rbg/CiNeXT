package DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class UsuarioDAO {
    // Método para registrar un nuevo usuario
    public int registrarUsuario(String nombre, String email, String password) {
        try (Connection connection = ConexionDB.obtenerConexion()) {
            // Verificar si el usuario ya existe
            if (existeUsuario(email)) {
                return 2;
            }

            // Insertar el nuevo usuario en la base de datos
            String query = "INSERT INTO Usuarios (Nombre, Email, Contraseña, TipoUsuario) VALUES (?, ?, ?, 'Cliente')";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();

                connection.close();
                return 1;
            }
        } catch (SQLException e) {
            return 0;
        }
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
                        // Crear un objeto Usuario con la información obtenida de la base de datos
                        Usuario usuario = new Usuario();
                        usuario.setId(resultSet.getInt("ID"));
                        usuario.setNombre(resultSet.getString("Nombre"));
                        usuario.setEmail(resultSet.getString("Email"));
                        usuario.setTipoUsuario(resultSet.getString("TipoUsuario"));

                        connection.close();
                        return usuario;
                    }
                }
            }
        } catch (SQLException e) {}

        return null; // Devolver null si las credenciales son incorrectas o hay un error
    }

    // Método para verificar si un usuario ya existe
    private boolean existeUsuario(String email) {
        // Implementar lógica para verificar si el usuario ya existe en la base de datos
        // Devolver true si el usuario ya existe, false en caso contrario
        boolean sonIguales = false;
        try{
            Connection connection = ConexionDB.obtenerConexion();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select Email from Usuarios");
            
            while (rs.next()){
                String emailRegistrados = rs.getString(1);
                if (emailRegistrados.equals(email)){
                    sonIguales = true;
                }
            }
        }catch (SQLException e){}
        
        return sonIguales;
    }
}