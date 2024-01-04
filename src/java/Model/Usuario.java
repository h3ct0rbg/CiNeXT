package Model;

public class Usuario {
    private String nombre;
    private String email;
    private String pass;
    private String tipoUsuario;

    // Constructor
    public Usuario() {}

    public Usuario(String nombre, String email, String pass, String tipoUsuario) {
        this.nombre = nombre;
        this.email = email;
        this.pass = pass;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }
    
    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}