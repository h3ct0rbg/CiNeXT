package Model;

import java.util.Date;

public class Entrada {

    private int id;
    private int filas;
    private int columnas;
    private int sesionId;
    private String email;
    private String nombreUsuario;
    private Date fecha;
    private String sala;
    private String tituloPelicula;

    //Constructor
    public Entrada(int sesionId, int filas, int columnas, String email) {
        this.sesionId = sesionId;
        this.filas = filas;
        this.columnas = columnas;
        this.email = email;
    }
    
    public Entrada(int id, int sesionId, int filas, int columnas, String email) {
        this.id = id;
        this.sesionId = sesionId;
        this.filas = filas;
        this.columnas = columnas;
        this.email = email;
    }

    public Entrada(int id, int sesionId, int filas, int columnas, String email, String nombreUsuario, Date fecha, String sala, String tituloPelicula) {
        this.id = id;
        this.filas = filas;
        this.columnas = columnas;
        this.sesionId = sesionId;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.fecha = fecha;
        this.sala = sala;
        this.tituloPelicula = tituloPelicula;
    }

    //Getters
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getId() {
        return id;
    }

    public int getSesionId() {
        return sesionId;
    }

    public String getEmail() {
        return email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getSala() {
        return sala;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }    

    //Setters
    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void setSesionId(int sesionId) {
        this.sesionId = sesionId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }
}