package DAO;

import Model.Pelicula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO {
    Connection connection;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;
    
    //Metodo para introducir una pelicula
    public int introducirPelicula(String titulo, String sinopsis, String paginaOficial, String anio, String genero, String duracion, String distribuidora, String director, String clasificacion, String otrosDatos) {
        String sql = "insert into peliculas (titulo, sinopsis, paginaOficial, anio, genero, duracion, distribuidora, director, clasificacionEdad, otrosDatos) values "
                + "('" +titulo+ "','" +sinopsis+ "','" +paginaOficial+ "','" +anio+ "','" +genero+ "','" +duracion+ "','" +distribuidora
                + "','" +director+ "','" +clasificacion+ "','" +otrosDatos+ "')";

        try {
            connection = ConexionDB.obtenerConexion();

            if (existePelicula(titulo, director)) {
                return 2;
            } else {
                try {
                    ps = connection.prepareStatement(sql);
                    ps.executeUpdate();
                    connection.close();
                    return 1;
                }catch(SQLException e){
                    return 0;
                }
            }
        } catch (Exception e) {
            return 0;
        }
    }
        
    //Metodo para listar una pelicula
    public List mostarPeliculas(){
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        String sql = "select * from peliculas order by id";
        
        try{
            connection = ConexionDB.obtenerConexion();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Pelicula p = new Pelicula();
                p.setId(rs.getInt("Id"));
                p.setTitulo(rs.getString("Titulo"));
                p.setSinopsis(rs.getString("Sinopsis"));
                p.setPaginaOficial(rs.getString("PaginaOficial"));
                p.setAnio(rs.getDate("Anio"));
                p.setGenero(rs.getString("Genero"));
                p.setDuracion(rs.getString("Duracion"));
                p.setDistribuidora(rs.getString("Distribuidora"));
                p.setDirector(rs.getString("Director"));
                p.setClasificacionEdad(rs.getString("ClasificacionEdad"));
                p.setOtrosDatos(rs.getString("OtrosDatos"));
                peliculas.add(p);
            }
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return peliculas;
    }
    
    //Metodo para actualizar una pelicula
    public int modificarPelicula(int id, String titulo, String sinopsis, String paginaOficial, String anio, String genero, String duracion, String distribuidora, String director, String clasificacion, String otrosDatos){
        String sql = "update peliculas set titulo='"+titulo+"', sinopsis='"+sinopsis+"', paginaoficial='"+paginaOficial+"', anio='"+anio+"', genero='"+genero+"', duracion='"+duracion+
                "', distribuidora='"+distribuidora+"', director='"+director+"', clasificacionedad='"+clasificacion+"', otrosdatos='"+otrosDatos+"' where id="+id;
        
        try{
            connection = ConexionDB.obtenerConexion();
            try{
                ps = connection.prepareStatement(sql);
                ps.executeUpdate();
                connection.close();
                return 1;
            }catch (SQLException e){
                e.printStackTrace();
                return 2;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    
    //Metodo para borrar una pelicula
    public int eliminarPelicula(int id) {
        String sql = "delete from peliculas where id='" + id + "'";
        try {
            connection = ConexionDB.obtenerConexion();
            try {
                ps = connection.prepareStatement(sql);
                ps.executeUpdate();
                connection.close();
                return 1;
            } catch (SQLException e) {
                return 2;
            }
        } catch (Exception e) {
            return 0;
        }
    }
    
    //metodo para comprobar que una pelicula no este repetida
    //utilizo el titulo y el director, ya que es muy probable que una pelicula con el mismo titulo y director sean la misma
    public boolean existePelicula(String titulo, String director) {
        boolean sonIguales = false;
        try {
            Connection connection = ConexionDB.obtenerConexion();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select Titulo, Director from Peliculas");

            while (rs.next()) {
                String pelTitulo = rs.getString(1);
                String pelDirector = rs.getString(2);
                if (pelTitulo.equals(titulo) && pelDirector.equals(director)) {
                    sonIguales = true;
                }
            }
        } catch (SQLException e) {
        }
        return sonIguales;
    }
    
    //metodo para mostar una pelicula obteniendo previamente el id
    public List listarPeliculaId(int id){
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        String sql = "select * from peliculas where id='"+id+"'";

        try {
            connection = ConexionDB.obtenerConexion();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setId(rs.getInt("Id"));
                p.setTitulo(rs.getString("Titulo"));
                p.setSinopsis(rs.getString("Sinopsis"));
                p.setPaginaOficial(rs.getString("PaginaOficial"));
                p.setAnio(rs.getDate("Anio"));
                p.setGenero(rs.getString("Genero"));
                p.setDuracion(rs.getString("Duracion"));
                p.setDistribuidora(rs.getString("Distribuidora"));
                p.setDirector(rs.getString("Director"));
                p.setClasificacionEdad(rs.getString("ClasificacionEdad"));
                p.setOtrosDatos(rs.getString("OtrosDatos"));
                peliculas.add(p);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peliculas;
    }
    
    public List buscarTitulo(String titulo){
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "select * from peliculas where titulo like '%"+titulo+"%'";
        System.out.println(sql);

        try {
            connection = ConexionDB.obtenerConexion();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setId(rs.getInt("Id"));
                p.setTitulo(rs.getString("Titulo"));
                p.setSinopsis(rs.getString("Sinopsis"));
                p.setPaginaOficial(rs.getString("PaginaOficial"));
                p.setAnio(rs.getDate("Anio"));
                p.setGenero(rs.getString("Genero"));
                p.setDuracion(rs.getString("Duracion"));
                p.setDistribuidora(rs.getString("Distribuidora"));
                p.setDirector(rs.getString("Director"));
                p.setClasificacionEdad(rs.getString("ClasificacionEdad"));
                p.setOtrosDatos(rs.getString("OtrosDatos"));
                peliculas.add(p);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peliculas;
    }
}

