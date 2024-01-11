package Model;

import java.sql.Date;
import java.sql.Time;

public class Sesion {
    private int id;
    private Date fecha;
    private Time hora;
    private int salaId;
    private String nombreSala;
    private int peliculaId;
    private String nombrePelicula;
    private byte[] asientosReservados;

    //Constructor
    public Sesion(){
        //Constructor vacío
    }
    
    public Sesion(Date fecha, Time hora, int salaId, int peliculaId) {
        this.fecha = fecha;
        this.hora = hora;
        this.salaId = salaId;
        this.peliculaId = peliculaId;
    }

    public Sesion(int id, Date fecha, Time hora, int salaId, int peliculaId) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.salaId = salaId;
        this.peliculaId = peliculaId;
    }

    public Sesion(int id, Date fecha, Time hora, int salaId, int peliculaId, String nombreSala, String tituloPelicula) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.salaId = salaId;
        this.peliculaId = peliculaId;
        this.nombreSala = nombreSala;
        this.nombrePelicula = tituloPelicula;
    }
    
    public Sesion(int id, Date fecha, Time hora, int salaId, int peliculaId, String nombreSala, String tituloPelicula, byte[] asientosReservados) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.salaId = salaId;
        this.peliculaId = peliculaId;
        this.nombreSala = nombreSala;
        this.nombrePelicula = tituloPelicula;
        this.asientosReservados = asientosReservados;
    }

    //Getters
    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public int getSalaId() {
        return salaId;
    }

    public int getPeliculaId() {
        return peliculaId;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public byte[] getAsientosReservados() {
        return asientosReservados;
    }
    
    //Setters
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setSalaId(int salaId) {
        this.salaId = salaId;
    }

    public void setPeliculaId(int peliculaId) {
        this.peliculaId = peliculaId;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public void setAsientosReservados(byte[] asientosReservados) {
        this.asientosReservados = asientosReservados;
    }
}