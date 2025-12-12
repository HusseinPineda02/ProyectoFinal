package hospital.ui;

import hospital.dao.*;
import java.awt.*;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final DoctorDAO doctorDAO = new DoctorDAO();
    private final EnfermeroDAO enfermeroDAO = new EnfermeroDAO();
    private final MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
    private final InsumoDAO insumoDAO = new InsumoDAO();
    private final HabitacionDAO habitacionDAO = new HabitacionDAO();
    private final CitaDAO citaDAO = new CitaDAO();
    private final CirugiaDAO cirugiaDAO = new CirugiaDAO();

    public VentanaPrincipal() {
        setTitle("Sistema del Hospital");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
    }

    private void initComponentes(Color fondo) {
        JPanel panel = new JPanel();
        panel.setBackground(fondo);
        panel.setLayout(new GridLayout(3, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton btnPacientes = new JButton("Pacientes");
        JButton btnPersonal  = new JButton("Personal");
        JButton btnCitas     = new JButton("Citas");
        JButton btnCirugias  = new JButton("Cirugías");
        JButton btnInventario= new JButton("Inventario");
        JButton btnHabitaciones = new JButton("Habitaciones");

        btnPacientes.addActionListener(e ->
                new VentanaPacientes(this, pacienteDAO).setVisible(true)
        );

        btnPersonal.addActionListener(e ->
                new DialogoPersonal(this, doctorDAO, enfermeroDAO).setVisible(true)
        );

        btnInventario.addActionListener(e ->
                new DialogoInventario(this, medicamentoDAO, insumoDAO).setVisible(true)
        );

        btnHabitaciones.addActionListener(e ->
                new VentanaHabitaciones(this, habitacionDAO).setVisible(true)
        );

        btnCitas.addActionListener(e ->
                new VentanaCitas(this, citaDAO, pacienteDAO, doctorDAO, habitacionDAO).setVisible(true)
        );

        btnCirugias.addActionListener(e ->
                new VentanaCirugias(this, cirugiaDAO, pacienteDAO, doctorDAO, habitacionDAO).setVisible(true)
        );

        panel.add(btnPacientes);
        panel.add(btnPersonal);
        panel.add(btnCitas);
        panel.add(btnCirugias);
        panel.add(btnInventario);
        panel.add(btnHabitaciones);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}