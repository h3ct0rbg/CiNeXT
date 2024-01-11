package Model;

public class Asiento {

    private int filas;
    private int columnas;

    //Constructor
    public Asiento(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }

    //Getters
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    //Setters
    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
}