package Model;

import java.util.Base64;
import java.util.Date;

public class Informe {
    private int idPelicula;
    private String tituloPelicula;
    private String sinopsisPelicula;
    private Date anioPelicula;
    private int numAsistentes;
    private String generoPelicula;
    private float puntuacionPelicula;
    private String comentarios;
    private byte[] imagen;
    private String nombreUsuario;
    
    public Informe(){
        
    }

    public Informe(int idPelicula,String tituloPelicula, String sinopsisPelicula, int numAsistentes, String generoPelicula, float puntuacionPelicula, String comentarios) {
        this.idPelicula = idPelicula;
        this.tituloPelicula = tituloPelicula;
        this.sinopsisPelicula = sinopsisPelicula;
        this.numAsistentes = numAsistentes;
        this.generoPelicula = generoPelicula;
        this.puntuacionPelicula = puntuacionPelicula;
        this.comentarios = comentarios;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }
    
    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    public String getSinopsisPelicula() {
        return sinopsisPelicula;
    }

    public void setSinopsisPelicula(String sinopsisPelicula) {
        this.sinopsisPelicula = sinopsisPelicula;
    }

    public Date getAnioPelicula() {
        return anioPelicula;
    }

    public void setAnioPelicula(Date anioPelicula) {
        this.anioPelicula = anioPelicula;
    }

    public int getNumAsistentes() {
        return numAsistentes;
    }

    public void setNumAsistentes(int numAsistentes) {
        this.numAsistentes = numAsistentes;
    }
   
    public String getGeneroPelicula() {
        return generoPelicula;
    }

    public void setGeneroPelicula(String generoPelicula) {
        this.generoPelicula = generoPelicula;
    }

    public float getPuntuacionPelicula() {
        return puntuacionPelicula;
    }

    public void setPuntuacionPelicula(String puntuacionPelicula) {
        float puntuacionPeliculaFt;
        if (puntuacionPelicula == null){
            puntuacionPeliculaFt = (float) 0;
        }else{
            puntuacionPeliculaFt = Float.valueOf(puntuacionPelicula);
        }
        this.puntuacionPelicula = puntuacionPeliculaFt;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
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

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}