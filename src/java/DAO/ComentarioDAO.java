package DAO;

import Model.Comentario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO {
    private Connection conexion;

    public ComentarioDAO() {
        this.conexion = ConexionDB.obtenerConexion();
    }

    public int insertarComentario(Comentario comentario) {
        try {
            // Verificar si el comentario ya existe
            if (existeComentario(comentario)) {
                return 2; // El comentario ya está registrado
            }

            String query = "INSERT INTO Comentarios (PeliculaID, UsuarioEmail, Comentario, Puntuacion) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setInt(1, comentario.getPeliculaId());
                statement.setString(2, comentario.getEmail());
                statement.setString(3, comentario.getComentario());
                statement.setFloat(4, comentario.getPuntuacion());
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    return 1; // Inserción exitosa
                }
            }
        } catch (SQLException e) {
            return 0;
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
        return 0;
    }

    public int modificarComentario(Comentario comentario) {
        try {
            String query = "UPDATE Comentarios SET PeliculaID=?, UsuarioEmail=?, Comentario=?, Puntuacion=? WHERE ID=?";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setInt(1, comentario.getPeliculaId());
                statement.setString(2, comentario.getEmail());
                statement.setString(3, comentario.getComentario());
                statement.setFloat(4, comentario.getPuntuacion());
                statement.setInt(5, comentario.getId());
                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            return 0;
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
    }

    public int eliminarComentario(int id) {
        try {
            String query = "DELETE FROM Comentarios WHERE ID=?";
            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.setInt(1, id);
                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            return 0;
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
    }

    public List<Comentario> getComentariosByPelicula(int idPelicula, String email_) {
        List<Comentario> comentarios = new ArrayList<>();
        try {
            String query = "SELECT id, peliculaid, usuarioemail, comentario, puntuacion FROM comentarios WHERE peliculaid=? AND usuarioemail=?";
            
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, idPelicula);
            statement.setString(2, email_);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int peliculaId = resultSet.getInt("peliculaid");
                String email = resultSet.getString("usuarioemail");
                String comentarioTexto = resultSet.getString("comentario");
                float puntuacion = resultSet.getFloat("puntuacion");

                Comentario comentario = new Comentario(id, peliculaId, email, comentarioTexto, puntuacion);
                comentarios.add(comentario);
            }
        } catch (SQLException e) {
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
        return comentarios;
    }
    
    public List<Comentario> getComentariosByIdPelicula(int idPelicula) {
        List<Comentario> comentarios = new ArrayList<>();
        try {
            String query = "SELECT id, peliculaid, usuarioemail, comentario, puntuacion FROM comentarios WHERE peliculaid=?";
            
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, idPelicula);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int peliculaId = resultSet.getInt("peliculaid");
                String email = resultSet.getString("usuarioemail");
                String comentarioTexto = resultSet.getString("comentario");
                float puntuacion = resultSet.getFloat("puntuacion");

                Comentario comentario = new Comentario(id, peliculaId, email, comentarioTexto, puntuacion);
                comentarios.add(comentario);
            }
        } catch (SQLException e) {
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
        return comentarios;
    }
    
    public boolean existeComentario(Comentario comentario) {
        try {
            String query = "SELECT COUNT(*) AS count FROM Comentarios WHERE PeliculaID=? AND UsuarioEmail=?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, comentario.getPeliculaId());
            statement.setString(2, comentario.getEmail());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            return false; // Manejar el error según tus necesidades
        }
        return false;
    }
}