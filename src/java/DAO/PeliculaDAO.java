package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Pelicula;

public class PeliculaDAO {

    private Connection conexion;

    public PeliculaDAO() {
        // Conexión a la base de datos
        this.conexion = ConexionDB.obtenerConexion();
    }

    public List<Pelicula> getAllPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();

        try {
            // Consulta SQL para obtener todas las películas
            String consulta = "SELECT id, titulo, sinopsis, anio, genero, duracion, director, clasificacionedad, imagen FROM peliculas";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Crear objeto Pelicula con los datos obtenidos de la base de datos
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");
                String sinopsis = resultSet.getString("sinopsis");
                int anio = resultSet.getInt("anio");
                String genero = resultSet.getString("genero");
                int duracion = resultSet.getInt("duracion");
                String director = resultSet.getString("director");
                String clasificacionEdad = resultSet.getString("clasificacionedad");
                byte[] imagen = resultSet.getBytes("imagen");

                Pelicula pelicula = new Pelicula(id, titulo, sinopsis, anio, genero, duracion, director, clasificacionEdad, imagen);
                peliculas.add(pelicula);
            }

        } catch (SQLException e) {
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }

        return peliculas;
    }

    // Método para obtener una película por su titulo
    public Pelicula getPeliculaByTitulo(String title) {
        Pelicula pelicula = null;

        try {
            // Consulta SQL para obtener una película por su ID
            String consulta = "SELECT id, titulo, sinopsis, anio, genero, duracion, director, clasificacionedad, imagen FROM peliculas WHERE titulo=?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, title);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Crear objeto Pelicula con los datos obtenidos de la base de datos
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");
                String sinopsis = resultSet.getString("sinopsis");
                int anio = resultSet.getInt("anio");
                String genero = resultSet.getString("genero");
                int duracion = resultSet.getInt("duracion");
                String director = resultSet.getString("director");
                String clasificacionEdad = resultSet.getString("clasificacionedad");
                byte[] imagen = resultSet.getBytes("imagen");

                pelicula = new Pelicula(id, titulo, sinopsis, anio, genero, duracion, director, clasificacionEdad, imagen);
            }

        } catch (SQLException e) {
            // Manejar la excepción
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }

        return pelicula;
    }

    // Método para insertar una nueva película en la base de datos
    public int insertarPelicula(Pelicula pelicula) {
        try {
            // Verificar si la película ya existe en la base de datos
            if (peliculaExiste(pelicula.getTitulo())) {
                // La película ya existe, retorna un código de error
                return 2;
            }

            // Consulta SQL para insertar una nueva película
            String consulta = "INSERT INTO peliculas (titulo, sinopsis, anio, genero, duracion, director, clasificacionedad, imagen) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Establecer los valores de los parámetros de la consulta
            statement.setString(1, pelicula.getTitulo());
            statement.setString(2, pelicula.getSinopsis());
            statement.setInt(3, pelicula.getAnio());
            statement.setString(4, pelicula.getGenero());
            statement.setInt(5, pelicula.getDuracion());
            statement.setString(6, pelicula.getDirector());
            statement.setString(7, pelicula.getClasificacionEdad());
            statement.setBytes(8, pelicula.getImagen());

            // Ejecutar la consulta
            int filasAfectadas = statement.executeUpdate();

            // Verificar si se insertó correctamente
            return (filasAfectadas > 0) ? 1 : 0;

        } catch (SQLException e) {
            return 0;

        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
    }

    // Método para actualizar una película
    public int modificarPelicula(Pelicula pelicula) {
        try {
            // Consulta SQL para actualizar una película
            String consulta;
            PreparedStatement statement;

            if (pelicula.getImagen() != null) {
                // Si la nueva imagen no es nula, actualizar todos los campos, incluida la imagen
                consulta = "UPDATE peliculas SET titulo=?, sinopsis=?, anio=?, genero=?, duracion=?, director=?, clasificacionedad=?, imagen=? WHERE id=?";
                statement = conexion.prepareStatement(consulta);
                statement.setBytes(8, pelicula.getImagen());
                statement.setInt(9, pelicula.getId());
            } else {
                // Si la nueva imagen es nula, actualizar todos los campos excepto la imagen
                consulta = "UPDATE peliculas SET titulo=?, sinopsis=?, anio=?, genero=?, duracion=?, director=?, clasificacionedad=? WHERE id=?";
                statement = conexion.prepareStatement(consulta);
                statement.setInt(8, pelicula.getId());
            }

            // Establecer los valores de los parámetros de la consulta
            statement.setString(1, pelicula.getTitulo());
            statement.setString(2, pelicula.getSinopsis());
            statement.setInt(3, pelicula.getAnio());
            statement.setString(4, pelicula.getGenero());
            statement.setInt(5, pelicula.getDuracion());
            statement.setString(6, pelicula.getDirector());
            statement.setString(7, pelicula.getClasificacionEdad());

            // Ejecutar la consulta
            int filasAfectadas = statement.executeUpdate();

            // Verificar si se actualizó correctamente
            return (filasAfectadas > 0) ? 1 : 0;

        } catch (SQLException e) {
            // Manejar la excepción
            return 0;

        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
    }

    public int eliminarPelicula(String titulo) {
        try {
            // Consulta SQL para eliminar una película por su título
            String consulta = "DELETE FROM peliculas WHERE titulo=?";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Establecer el valor del parámetro de la consulta
            statement.setString(1, titulo);

            // Ejecutar la consulta
            int filasAfectadas = statement.executeUpdate();

            // Verificar si se eliminó correctamente
            return (filasAfectadas > 0) ? 1 : 0;

        } catch (SQLException e) {
            // Manejar la excepción
            return 0;

        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
    }

    // Método para obtener una película por su titulo
    public List<Pelicula> buscarPelicula(String title) {
        List<Pelicula> peliculas = new ArrayList<>();

        try {
            // Consulta SQL para obtener películas por su título
            String consulta = "SELECT id, titulo, sinopsis, anio, genero, duracion, director, clasificacionedad, imagen FROM peliculas WHERE LOWER(titulo) LIKE LOWER(?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, "%" + title + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Crear objeto Pelicula con los datos obtenidos de la base de datos
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");
                String sinopsis = resultSet.getString("sinopsis");
                int anio = resultSet.getInt("anio");
                String genero = resultSet.getString("genero");
                int duracion = resultSet.getInt("duracion");
                String director = resultSet.getString("director");
                String clasificacionEdad = resultSet.getString("clasificacionedad");
                byte[] imagen = resultSet.getBytes("imagen");

                Pelicula pelicula = new Pelicula(id, titulo, sinopsis, anio, genero, duracion, director, clasificacionEdad, imagen);
                peliculas.add(pelicula);
            }

        } catch (SQLException e) {
            // Manejar la excepción
        } finally {
            ConexionDB.cerrarConexion(conexion);
        }
        return peliculas;
    }

    // Método para verificar si una película ya existe en la base de datos (excepto para la misma película)
    private boolean peliculaExiste(String titulo) throws SQLException {
        String consulta = "SELECT COUNT(*) FROM peliculas WHERE titulo = ?";
        try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
            statement.setString(1, titulo);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0;
            }
        }
    }
}
