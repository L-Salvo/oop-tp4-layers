package ejercicio3.model;


public class Inscripcion {
    private String apellido;
    private String nombre;
    private String telefono;
    private String email;
    private int idConcurso;

    public Inscripcion(String apellido, String nombre, String telefono, String email, int idConcurso) {
        checkApellido(apellido);
        checkNombre(nombre);
        checkTelefono(telefono);
        checkEmail(email);
        checkIdConcurso(idConcurso);

        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.idConcurso = idConcurso;
    }

    private void checkIdConcurso(int idConcurso) {
        if (idConcurso <= 0)
            throw new IllegalArgumentException("El concurso seleccionado no es válido.");
    }

    private void checkEmail(String email) {
        if (email == null || !email.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+$"))
            throw new IllegalArgumentException("El email no es válido.");
    }

    private void checkTelefono(String telefono) {
        if (telefono == null || !telefono.matches("\\d{4}-\\d{6}"))
            throw new IllegalArgumentException("El teléfono debe tener el formato NNNN-NNNNNN.");
    }

    private void checkNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede ser vacío.");
    }

    private void checkApellido(String apellido) {
        if (apellido == null || apellido.isBlank())
            throw new IllegalArgumentException("El apellido no puede ser vacío.");
    }

    public String getApellido() { return apellido; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public int getIdConcurso() { return idConcurso; }
}
