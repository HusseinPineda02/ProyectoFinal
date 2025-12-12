package hospital.ui;

import hospital.dao.*;
import hospital.modelo.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;

public class DialogoCitaFormulario extends JDialog {

    private JTextField txtDniPaciente;
    private JTextField txtIdDoctor;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextField txtMotivo;
    private JComboBox<Habitacion> cboHabitacion;
    private JComboBox<Cita.Estado> cboEstado;

    private Cita citaOriginal;
    private CitaDAO citaDAO;
    private PacienteDAO pacienteDAO;
    private DoctorDAO doctorDAO;
    private HabitacionDAO habitacionDAO;

    public DialogoCitaFormulario(Dialog owner, Cita cita,
                                 CitaDAO cdao, PacienteDAO pdao,
                                 DoctorDAO ddao, HabitacionDAO hdao) {

        super(owner, true);
        this.citaOriginal = cita;
        this.citaDAO = cdao;
        this.pacienteDAO = pdao;
        this.doctorDAO = ddao;
        this.habitacionDAO = hdao;

        setTitle(cita == null ? "Nueva Cita" : "Editar Cita");
        setSize(450, 360);
        setLocationRelativeTo(owner);

        initComponentes();

        if (cita != null) cargarDatos();
    }

    private void initComponentes() {

        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtDniPaciente = new JTextField();
        txtIdDoctor = new JTextField();
        txtFecha = new JTextField();
        txtHora = new JTextField();
        txtMotivo = new JTextField();
        cboEstado = new JComboBox<>(Cita.Estado.values());
        cboHabitacion = new JComboBox<>();

        cargarConsultorios();

        panel.add(new JLabel("DNI Paciente:"));
        panel.add(txtDniPaciente);
        panel.add(new JLabel("ID Doctor:"));
        panel.add(txtIdDoctor);
        panel.add(new JLabel("Fecha (yyyy-MM-dd):"));
        panel.add(txtFecha);
        panel.add(new JLabel("Hora (HH:mm):"));
        panel.add(txtHora);
        panel.add(new JLabel("Consultorio:"));
        panel.add(cboHabitacion);
        panel.add(new JLabel("Motivo:"));
        panel.add(txtMotivo);
        panel.add(new JLabel("Estado:"));
        panel.add(cboEstado);

        JPanel botones = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> dispose());

        botones.add(btnGuardar);
        botones.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    private void cargarConsultorios() {
        try {
            List<Habitacion> lista = habitacionDAO.listarTodos();
            for (Habitacion h : lista) {
                if (h.getTipo() == TipoHabitacion.CONSULTORIO) {
                    cboHabitacion.addItem(h);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error cargando consultorios");
        }
    }

    private void cargarDatos() {
        txtDniPaciente.setText(citaOriginal.getPaciente().getDni());
        txtIdDoctor.setText(String.valueOf(citaOriginal.getDoctor().getIdTrabajador()));
        txtFecha.setText(citaOriginal.getFecha().toString());
        txtHora.setText(citaOriginal.getHora().toString());
        txtMotivo.setText(citaOriginal.getMotivo());
        cboEstado.setSelectedItem(citaOriginal.getEstado());
        cboHabitacion.setSelectedItem(citaOriginal.getHabitacion());
    }

    private void guardar() {
        try {
            String dni = txtDniPaciente.getText().trim();
            int idDoctor = Integer.parseInt(txtIdDoctor.getText().trim());
            LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
            Habitacion h = (Habitacion) cboHabitacion.getSelectedItem();
            String motivo = txtMotivo.getText().trim();
            Cita.Estado estado = (Cita.Estado) cboEstado.getSelectedItem();
            
            DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("HH:mm");

            LocalTime hora = LocalTime.parse(txtHora.getText().trim(), fmtHora).withSecond(0).withNano(0);


            if (h == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un consultorio");
                return;
            }

            if (citaDAO.existeCitaEnHorario(
                    h.getIdHabitacion(),
                    fecha,
                    hora)) {

                JOptionPane.showMessageDialog(
                    this,
                    "La habitación ya está ocupada en ese horario",
                    "Conflicto",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            if (!h.isDisponible()) {
                JOptionPane.showMessageDialog(this,
                    "La habitación seleccionada no está habilitada (mantenimiento)",
                    "Habitación no disponible",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }


            Paciente p = pacienteDAO.buscarPorDni(dni);
            Doctor d = doctorDAO.buscar(idDoctor);

            if (p == null || d == null) {
                JOptionPane.showMessageDialog(this, "Paciente o Doctor no encontrado");
                return;
            }


            if (citaOriginal == null) {
                Cita nueva = new Cita(0, p, d, h, fecha, hora, motivo, estado);
                citaDAO.insertar(nueva);
            } else {
                citaOriginal.setPaciente(p);
                citaOriginal.setDoctor(d);
                citaOriginal.setHabitacion(h);
                citaOriginal.setFecha(fecha);
                citaOriginal.setHora(hora);
                citaOriginal.setMotivo(motivo);
                citaOriginal.setEstado(estado);
                citaDAO.actualizar(citaOriginal);
            }

            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}