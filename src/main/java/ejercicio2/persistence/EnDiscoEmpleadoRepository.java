package ejercicio2.persistence;

import ejercicio2.model.Empleado;
import ejercicio2.model.EmpleadoRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EnDiscoEmpleadoRepository implements EmpleadoRepository {

    private final String rutaArchivo;

    public EnDiscoEmpleadoRepository(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void registrarEmpleado(String registro) {
        try {
            Path path = Paths.get(rutaArchivo);
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
            Files.write(path, (registro + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Error al registrar el empleado: " + e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<Empleado> obtenerTodos() {
        Path path = Paths.get(rutaArchivo);
        ArrayList<Empleado> empleados = new ArrayList<>();

        if (!Files.exists(path)) return empleados;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try (var lines = Files.lines(path)) {
            lines.filter(line -> !line.isBlank())
                    .map(line -> line.split(","))
                    .filter(campos -> campos.length >= 4)
                    .map(campos -> new Empleado(
                            campos[0],
                            campos[1],
                            LocalDate.parse(campos[2].trim(), formatter),
                            campos[3]
                    ))
                    .forEach(empleados::add);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + e.getMessage(), e);
        }
        return empleados;
    }
}
