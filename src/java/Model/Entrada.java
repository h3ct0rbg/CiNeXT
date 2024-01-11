package Model;

public class Entrada {

    private int id;
    private int filas;
    private int columnas;
    private int sesionId;
    private String email;

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
}