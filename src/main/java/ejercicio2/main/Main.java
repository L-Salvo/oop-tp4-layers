package ejercicio2.main;

import ejercicio2.model.Empleado;
import ejercicio2.model.EmpleadoRepository;
import ejercicio2.model.EnviarMail;
import ejercicio2.model.GestionEmpleados;
import ejercicio2.persistence.EnDiscoEmpleadoRepository;
import ejercicio2.persistence.EnviarSaludoMail;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EnviarMail enviarMail = new EnviarSaludoMail();
        EmpleadoRepository empleadoRepository = new EnDiscoEmpleadoRepository("src/main/resources/empleados.txt");
        Empleado empleado = new Empleado("Jorge", " Martinez", LocalDate.now(), "jmartinez@gmail.com");
        GestionEmpleados administrarEmpleados = new GestionEmpleados(empleadoRepository, enviarMail);
        administrarEmpleados.registrar(empleado);
        administrarEmpleados.saludarEmpleado();
    }
}
