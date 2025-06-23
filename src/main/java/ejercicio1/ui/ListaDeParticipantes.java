package ejercicio1.ui;

import ejercicio1.model.Inscripcion;

import ejercicio1.model.Participante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaDeParticipantes extends JFrame {

    private Inscripcion inscripcion;
    private JTable table;

    public ListaDeParticipantes(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
        setupUIComponents();
    }

    private void setupUIComponents() {
        setTitle("Lista de Participantes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear el modelo de la tabla
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Nombre", "Teléfono", "Región"}, 0);
        table = new JTable(tableModel);

        // Cargar los datos de los participantes
        cargarParticipantes(tableModel);

        // Configurar el panel principal
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void cargarParticipantes(DefaultTableModel tableModel) {
        try {
            List<Participante> participantes = inscripcion.participantes();
            for (Participante participante : participantes) {
                tableModel.addRow(new Object[]{
                        participante.getNombre(),
                        participante.getTelefono(),
                        participante.getRegion()
                });
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los participantes: " + e.getMessage());
        }
    }
}