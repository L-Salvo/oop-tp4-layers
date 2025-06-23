package ejercicio1.model;

public class Participante {

    private String nombre;
    private String telefono;
    private String region;

    public Participante(String nombre, String telefono, String region) {

        checkNombreIsEmpty(nombre);
        checkTelefonoIsEmpty(telefono);
        checkFormatoTelefono(telefono);
        checkRegionConocida(region);
        this.nombre = nombre;
        this.telefono = telefono;
        this.region = region;
    }

    private static void checkRegionConocida(String region) {
        if (esUnaRegionDesconocida(region)) {
            throw new RuntimeException("Region desconocida. Las conocidas son: China, US, Europa");
        }
    }

    private static void checkTelefonoIsEmpty(String telefono) {
        if (telefono.isEmpty()) {
            throw new RuntimeException("Debe cargar un telefono");
        }
    }

    private static void checkNombreIsEmpty(String nombre) {
        if (nombre.isEmpty()) {
            throw new RuntimeException("Debe cargar un nombre");
        }
    }

    private static boolean esUnaRegionDesconocida(String region) {
        return !region.equals("China") && !region.equals("US") && !
                region.equals("Europa");
    }

    private void checkFormatoTelefono(String telefono) {
        if (!validarTelefono(telefono)) {
            throw new RuntimeException("El telefono debe ingresarse de la siguiente forma: NNNN-NNNNNN");
        }
    }

    private boolean validarTelefono(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRegion() {
        return region;
    }


}