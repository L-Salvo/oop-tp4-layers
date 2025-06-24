package ejercicio2.model;

import java.util.List;

public class GestionEmpleados {

    private EmpleadoRepository empleadoRepository;
    private EnviarMail envio;

    public GestionEmpleados(EmpleadoRepository empleadoRepository, EnviarMail envio) {
        this.empleadoRepository = empleadoRepository;
        this.envio = envio;
    }

    public void registrar(Empleado empleado) {
        if (empleadoRepository.obtenerTodos().stream().anyMatch(e -> e.getEmail().equals(empleado.getEmail()))) {
            throw new RuntimeException("El empleado ya se encuentra registrado");
        }
        empleadoRepository.registrarEmpleado(empleado.toString());
    }

    public void saludarEmpleado() {
        List<Empleado> Empleados = empleadoRepository.obtenerTodos();
        for (Empleado empleado : Empleados) {
            if (empleado.isCumpleaños()) {
                empleado.saludo(envio);
            }
        }
    }

    public List<Empleado> obtenerEmpleados() {
        return empleadoRepository.obtenerTodos();
    }
}
