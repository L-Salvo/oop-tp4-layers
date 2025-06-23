package ejercicio1.ui;


import ejercicio1.model.Inscripcion;
import ejercicio1.model.Participante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AgregarParticipante extends JFrame {

    private JTextField nombre;
    private JTextField telefono;
    private JTextField region;
    private Inscripcion inscripcion;

    public AgregarParticipante(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
        setupUIComponents();
    }

    private void setupUIComponents() {
        setTitle("Add Participant");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.nombre = new JTextField(10);
        this.telefono = new JTextField(10);
        this.region = new JTextField(10);
        this.nombre.setText("");
        this.telefono.setText("0000-000000");
        this.region.setText("China");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FlowLayout());
        contentPane.add(new JLabel("Nombre: "));
        contentPane.add(nombre);
        contentPane.add(new JLabel("Telefono: "));
        contentPane.add(telefono);
        contentPane.add(new JLabel("Region: "));
        contentPane.add(region);
        JButton botonCargar = new JButton("Cargar");
        botonCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBotonCargar();
            }
        });
        contentPane.add(botonCargar);
        // Add the new button to open ListaParticipantes
        JButton botonVerLista = new JButton("Ver Lista de Participantes");
        botonVerLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ListaDeParticipantes(inscripcion); // Open the ListaParticipantes window
            }
        });
        contentPane.add(botonVerLista);

        setContentPane(contentPane);
        contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pack();
        setVisible(true);
    }

    private void onBotonCargar() {

        try {
            inscripcion.nuevoParticipante(nombre.getText(), telefono.getText(), region.getText());
            JOptionPane.showMessageDialog(this, "Participante agregado correctamente");
            //dispose();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}