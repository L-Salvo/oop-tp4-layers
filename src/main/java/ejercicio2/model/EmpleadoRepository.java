package ejercicio2.model;

import java.util.ArrayList;

public interface EmpleadoRepository {
    void registrarEmpleado(String registro);

    ArrayList<Empleado> obtenerTodos();
}