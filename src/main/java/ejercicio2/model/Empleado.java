package ejercicio2.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Empleado {
    private String nombre;
    private String apellido;
    private LocalDate fechaDeNacimiento;
    private String email;

    public Empleado(String nombre, String apellido, LocalDate fechaNacimiento, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaNacimiento;
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder ts = new StringBuilder();
        String fechaFormateada = fechaDeNacimiento.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        ts.append(nombre).append(",").append(apellido).append(", ").append(fechaFormateada).append(",").append(email).append("\n");
        return ts.toString();
    }

    public boolean isCumpleaños() {
        LocalDate hoy = LocalDate.now();
        return hoy.getDayOfMonth() == fechaDeNacimiento.getDayOfMonth()
                && hoy.getMonthValue() == fechaDeNacimiento.getMonthValue();
    }

    public void saludo(EnviarMail enviarMail) {
        enviarMail.enviar(this.email, "Cumpleaños","Hola " + this.nombre+ ", desde la empresa te deseamos un feliz cumpleaños!!\n\nSaludos cordiales.");
    }

    public String getEmail() {
        return this.email;
    }
}