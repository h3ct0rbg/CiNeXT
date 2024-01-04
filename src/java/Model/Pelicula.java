package Model;

import java.util.Base64;

public class Pelicula {
    private int id;
    private String titulo;
    private String sinopsis;
    private int anio;
    private String genero;
    private int duracion;
    private String director;
    private String clasificacionEdad;
    private byte[] imagen;

    // Constructor
    public Pelicula(String titulo, String sinopsis, int anio, String genero, int duracion, String director, String clasificacionEdad, byte[] imagen) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.anio = anio;
        this.genero = genero;
        this.duracion = duracion;
        this.director = director;
        this.clasificacionEdad = clasificacionEdad;
        this.imagen = imagen;
    }

    public Pelicula(int id, String titulo, String sinopsis, int anio, String genero, int duracion, String director, String clasificacionEdad, byte[] imagen) {
        this.id = id;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.anio = anio;
        this.genero = genero;
        this.duracion = duracion;
        this.director = director;
        this.clasificacionEdad = clasificacionEdad;
        this.imagen = imagen;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public int getAnio() {
        return anio;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getDirector() {
        return director;
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }
    
    public byte[] getImagen() {
   	return imagen;
    }

    public String getImagenBase64() {
        if (imagen != null) {
            // Codificar la imagen en formato base64
            return Base64.getEncoder().encodeToString(imagen);
        } else {
            // En caso de que la imagen sea nula, puedes devolver una cadena vacía o una imagen de marcador de posición
            return "";
        }
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
