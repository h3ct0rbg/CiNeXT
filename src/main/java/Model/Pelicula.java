package Model;

import java.util.Date;

public class Pelicula {
    int id;
    String titulo;
    String sinopsis;
    String paginaOficial;
    Date anio;
    String genero;
    String duracion;
    String distribuidora;
    String director;
    String clasificacionEdad;
    String otrosDatos;
    
    //Constructor 
    public Pelicula(){
        
    }
    
    public Pelicula(String titulo, String sinopsis, String paginaOficial, Date anio, String genero, String duracion, String distribuidora, String director, String clasificacion, String otrosDatos) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.paginaOficial = paginaOficial;
        this.anio = anio;
        this.genero = genero;
        this.duracion = duracion;
        this.distribuidora = distribuidora;
        this.director = director;
        this.clasificacionEdad = clasificacion;
        this.otrosDatos = otrosDatos;
    }
    
    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getPaginaOficial() {
        return paginaOficial;
    }

    public void setPaginaOficial(String paginaOficial) {
        this.paginaOficial = paginaOficial;
    }

    public Date getAnio() {
        return anio;
    }

    public void setAnio(Date anio) {
        this.anio = anio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(String distribuidora) {
        this.distribuidora = distribuidora;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }

    public String getOtrosDatos() {
        return otrosDatos;
    }

    public void setOtrosDatos(String otrosDatos) {
        this.otrosDatos = otrosDatos;
    }
    
    
}
