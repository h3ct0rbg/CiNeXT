package DAO;

import Model.Informe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InformeDAO {
    Connection connection;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public List mostarPeliculas() {
        ArrayList<Informe> peliculas = new ArrayList<>();
        String sql = "SELECT P.ID, P.TITULO, P.SINOPSIS, COUNT(X.PELICULAID), P.ANIO, P.GENERO, PUNTUACIONMEDIA, P.IMAGEN "
                + "FROM PELICULAS P "
                + "LEFT JOIN (SELECT PELICULAID FROM ENTRADAS E "
                + "INNER JOIN SESIONES H ON E.SESIONID = H.ID) X ON P.ID = X.PELICULAID "
                + "LEFT JOIN (SELECT PELICULAID, AVG(PUNTUACION)::NUMERIC(10,2) AS PUNTUACIONMEDIA FROM comentarios "
                + "GROUP BY PELICULAID) C ON P.ID = C.PELICULAID "
                + "GROUP BY P.ID, P.TITULO, P.SINOPSIS, P.ANIO, P.GENERO, P.IMAGEN, PUNTUACIONMEDIA";

        try {
            connection = ConexionDB.obtenerConexion();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Informe i = new Informe();
                i.setIdPelicula(Integer.parseInt(rs.getString("id")));
                i.setTituloPelicula(rs.getString("Titulo"));
                i.setSinopsisPelicula(rs.getString("Sinopsis"));
                i.setNumAsistentes(Integer.parseInt(rs.getString("count")));
                i.setGeneroPelicula(rs.getString("Genero"));
                i.setPuntuacionPelicula(rs.getString("PuntuacionMedia"));
                i.setImagen(rs.getBytes("imagen"));
                peliculas.add(i);
            }
            connection.close();
        } catch (NumberFormatException | SQLException e) {}
        return peliculas;
    }
    
    //Metodo para listar una pelicula
    public List ordenarPeliculasGenero(){
        ArrayList<Informe> peliculas = new ArrayList<>();
        String sql = "SELECT P.ID, P.TITULO, P.SINOPSIS, COUNT(X.PELICULAID), P.ANIO, P.GENERO, PUNTUACIONMEDIA, P.IMAGEN "
                + "FROM PELICULAS P "
                + "LEFT JOIN (SELECT PELICULAID FROM ENTRADAS E "
                + "INNER JOIN SESIONES H ON E.SESIONID = H.ID) X ON P.ID = X.PELICULAID "
                + "LEFT JOIN (SELECT PELICULAID, AVG(PUNTUACION)::NUMERIC(10,2) AS PUNTUACIONMEDIA FROM comentarios "
                + "GROUP BY PELICULAID) C ON P.ID = C.PELICULAID "
                + "GROUP BY P.ID, P.TITULO, P.SINOPSIS, P.ANIO, P.GENERO, P.IMAGEN, PUNTUACIONMEDIA "
                + "ORDER BY GENERO";
        
        try{
            connection = ConexionDB.obtenerConexion();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Informe i= new Informe();
                i.setIdPelicula(Integer.parseInt(rs.getString("Id")));
                i.setTituloPelicula(rs.getString("Titulo"));
                i.setSinopsisPelicula(rs.getString("Sinopsis"));
                i.setNumAsistentes(Integer.parseInt(rs.getString("count")));
                i.setGeneroPelicula(rs.getString("Genero"));
                i.setPuntuacionPelicula(rs.getString("PuntuacionMedia"));
                i.setImagen(rs.getBytes("imagen"));
                peliculas.add(i);
            }
            connection.close();
        }catch (NumberFormatException | SQLException e){}
        return peliculas;
    }
    
        //Metodo para listar una pelicula
    public List ordenarPeliculasPuntuacion(){
        ArrayList<Informe> peliculas = new ArrayList<>();
        String sql = "SELECT P.ID, P.TITULO, P.SINOPSIS, COUNT(X.PELICULAID), P.ANIO, P.GENERO, PUNTUACIONMEDIA, P.IMAGEN "
                + "FROM PELICULAS P "
                + "LEFT JOIN (SELECT PELICULAID FROM ENTRADAS E "
                + "INNER JOIN SESIONES H ON E.SESIONID = H.ID) X ON P.ID = X.PELICULAID "
                + "LEFT JOIN (SELECT PELICULAID, AVG(PUNTUACION)::NUMERIC(10,2) AS PUNTUACIONMEDIA FROM comentarios "
                + "GROUP BY PELICULAID) C ON P.ID = C.PELICULAID "
                + "GROUP BY P.ID, P.TITULO, P.SINOPSIS, P.ANIO, P.GENERO, P.IMAGEN, PUNTUACIONMEDIA "
                + "HAVING PUNTUACIONMEDIA IS NOT NULL "
                + "ORDER BY PUNTUACIONMEDIA DESC";
        
        try{
            connection = ConexionDB.obtenerConexion();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Informe i= new Informe();
                i.setIdPelicula(Integer.parseInt(rs.getString("id")));
                i.setTituloPelicula(rs.getString("Titulo"));
                i.setSinopsisPelicula(rs.getString("Sinopsis"));
                i.setNumAsistentes(Integer.parseInt(rs.getString("count")));
                i.setGeneroPelicula(rs.getString("Genero"));
                i.setPuntuacionPelicula(rs.getString("PuntuacionMedia"));
                i.setImagen(rs.getBytes("imagen"));
                peliculas.add(i);
            }
            connection.close();
        }catch (NumberFormatException | SQLException e){}
        return peliculas;
    }
    
    //funcion que muestra los comentarios registrados de las peliculas
    public List mostrarComentarios(String tituloPelicula) {
        ArrayList<Informe> peliculas = new ArrayList<>();
        String sql = "select u.nombre, pu.comentario, pu.puntuacion from comentarios pu inner join usuarios u on pu.usuarioemail=u.email "
                + "inner join peliculas p on pu.peliculaid = p.id "
                + "where p.titulo='"+tituloPelicula+"' and pu.comentario is not NULL";

        try {
            connection = ConexionDB.obtenerConexion();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Informe i= new Informe();
                i.setNombreUsuario(rs.getString("nombre"));
                i.setComentarios(rs.getString("comentario"));
                i.setPuntuacionPelicula(rs.getString("puntuacion"));
                peliculas.add(i);
            }
            connection.close();
        } catch (SQLException e) {}
        return peliculas;
    }
}