package Model;

public class Comentario {
    private int id;
    private int peliculaId;
    private String email;
    private String comentario;
    private float puntuacion;

    public Comentario() {
    }
    
    public Comentario(int peliculaId, String email, String comentario, float puntuacion) {
        this.peliculaId = peliculaId;
        this.email = email;
        this.comentario = comentario;
        this.puntuacion = puntuacion;
    }
    
    public Comentario(int id, int peliculaId, String email, String comentario, float puntuacion) {
        this.id = id;
        this.peliculaId = peliculaId;
        this.email = email;
        this.comentario = comentario;
        this.puntuacion = puntuacion;
    }
    
    //Getters
    public int getId() {
        return id;
    }

    public int getPeliculaId() {
        return peliculaId;
    }

    public String getEmail() {
        return email;
    }

    public String getComentario() {
        return comentario;
    }

    public float getPuntuacion() {
        return puntuacion;
    }
    
    //Setters
    public void setPeliculaId(int peliculaId) {
        this.peliculaId = peliculaId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }
    
}
