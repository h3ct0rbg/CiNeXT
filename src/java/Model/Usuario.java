package Model;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String pass;
    private String tipoUsuario;

    // Constructor
    public Usuario() {}

    public Usuario(int id, String nombre, String email, String pass, String tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.pass = pass;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters
    public int getId() {
        return id;
    }

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
    public void setId(int id) {
        this.id = id;
    }

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