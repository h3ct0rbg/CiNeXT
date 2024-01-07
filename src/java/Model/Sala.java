package Model;

public class Sala {

    private int id;
    private int filas;
    private int columnas;
    private String tipo;
    private String nombre;

    //Constructor
    public Sala(int filas, int columnas, String tipo, String nombre) {
        this.filas = filas;
        this.columnas = columnas;
        this.tipo = tipo;
        this.nombre = nombre;
    }
    
    
    public Sala(int id, int filas, int columnas, String tipo, String nombre) {
        this.id = id;
        this.filas = filas;
        this.columnas = columnas;
        this.tipo = tipo;
        this.nombre = nombre;
    }

    //Getters
    public int getId() {
        return id;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }
    
    //Setters
    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}