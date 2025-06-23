package ejercicio3.ui;

import ejercicio3.model.Concurso;
import ejercicio3.model.Inscripcion;
import ejercicio3.persistence.ConcursoRepository;
import ejercicio3.persistence.InscripcionRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RadioCompetition {
    private JPanel contentPane;
    private JTextField txtName, txtLastName, txtId, txtPhone, txtEmail;
    private JComboBox<Concurso> comboBox;
    private JButton btnOk;

    private ConcursoRepository concursoRepo;
    private InscripcionRepository inscripcionRepo;

    public RadioCompetition(ConcursoRepository concursoRepo, InscripcionRepository inscripcionRepo) {
        this.concursoRepo = concursoRepo;
        this.inscripcionRepo = inscripcionRepo;

        JFrame frame = new JFrame("Inscripción a Concurso");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 450, 250);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);

        crearComponentes();
        layout();

        cargarConcursos();
        frame.setVisible(true);
    }

    private void crearComponentes() {
        txtName = new JTextField(10);
        txtLastName = new JTextField(10);
        txtId = new JTextField(10);
        txtPhone = new JTextField(10);
        txtEmail = new JTextField(10);
        comboBox = new JComboBox<>();

        btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnOk.setEnabled(false);
                guardarInscripcion();
                btnOk.setEnabled(true);
            }
        });
    }

    private void cargarConcursos() {
        comboBox.addItem(null); // opción vacía
        List<Concurso> concursos = concursoRepo.obtenerConcursosAbiertos();
        for (Concurso c : concursos) {
            comboBox.addItem(c);
        }
    }

    private void guardarInscripcion() {
        Concurso concursoSeleccionado = (Concurso) comboBox.getSelectedItem();
        try {
            Inscripcion insc = new Inscripcion(
                    txtLastName.getText(),
                    txtName.getText(),
                    txtPhone.getText(),
                    txtEmail.getText(),
                    concursoSeleccionado != null ? concursoSeleccionado.getId() : -1
            );
            inscripcionRepo.guardarInscripcion(insc);
            JOptionPane.showMessageDialog(contentPane, "Inscripción guardada correctamente.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(contentPane, ex.getMessage());
        }
    }


    private boolean checkEmail(String email) {
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+$";
        return email.matches(regex);
    }

    private boolean checkPhone(String telefono) {
        return telefono.matches("\\d{4}-\\d{6}");
    }

    private void layout() {
        JLabel lblName = new JLabel("Nombre:");
        JLabel lblLastName = new JLabel("Apellido:");
        JLabel lblId = new JLabel("DNI:");
        JLabel lblPhone = new JLabel("Teléfono:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblCompetition = new JLabel("Concurso:");

        GroupLayout gl = new GroupLayout(contentPane);
        gl.setHorizontalGroup(
                gl.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl.createSequentialGroup().addContainerGap()
                                .addGroup(gl.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblName)
                                        .addComponent(lblLastName)
                                        .addComponent(lblId)
                                        .addComponent(lblPhone)
                                        .addComponent(lblEmail)
                                        .addComponent(lblCompetition))
                                .addGap(18)
                                .addGroup(gl.createParallelGroup(Alignment.LEADING)
                                        .addComponent(txtName)
                                        .addComponent(txtLastName)
                                        .addComponent(txtId)
                                        .addComponent(txtPhone)
                                        .addComponent(txtEmail)
                                        .addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnOk, Alignment.TRAILING))
                                .addContainerGap()));
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addGroup(gl.createParallelGroup(Alignment.BASELINE).addComponent(lblName).addComponent(txtName))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl.createParallelGroup(Alignment.BASELINE).addComponent(lblLastName).addComponent(txtLastName))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl.createParallelGroup(Alignment.BASELINE).addComponent(lblId).addComponent(txtId))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl.createParallelGroup(Alignment.BASELINE).addComponent(lblPhone).addComponent(txtPhone))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl.createParallelGroup(Alignment.BASELINE).addComponent(lblEmail).addComponent(txtEmail))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl.createParallelGroup(Alignment.BASELINE).addComponent(lblCompetition).addComponent(comboBox))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(btnOk)
        );
        contentPane.setLayout(gl);
    }
}
