package hospital.ui;

import hospital.dao.CirugiaDAO;
import hospital.dao.DoctorDAO;
import hospital.dao.HabitacionDAO;
import hospital.dao.PacienteDAO;
import hospital.modelo.Cirugia;
import hospital.modelo.Doctor;
import hospital.modelo.Habitacion;
import hospital.modelo.Paciente;
import hospital.modelo.TipoHabitacion;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;

public class DialogoCirugiaFormulario extends JDialog {

    private JTextField txtDniPaciente;
    private JTextField txtIdDoctor;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextField txtDescripcion;

    private JComboBox<Habitacion> cboQuirofano;
    private JCheckBox chkSoloDisponibles;
    private JComboBox<Cirugia.Estado> cboEstado;

    private Cirugia cirugiaOriginal;

    private CirugiaDAO cirugiaDAO;
    private PacienteDAO pacienteDAO;
    private DoctorDAO doctorDAO;
    private HabitacionDAO habitacionDAO;

    public DialogoCirugiaFormulario(Dialog owner, Cirugia cirugia,
                                    CirugiaDAO cdao, PacienteDAO pdao,
                                    DoctorDAO ddao, HabitacionDAO hdao) {
        super(owner, true);
        this.cirugiaOriginal = cirugia;
        this.cirugiaDAO = cdao;
        this.pacienteDAO = pdao;
        this.doctorDAO = ddao;
        this.habitacionDAO = hdao;

        setTitle(cirugia == null ? "Programar Cirugía" : "Editar Cirugía");
        setSize(520, 390);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);

        if (cirugia != null) {
            cargarDatos();
        }

        cargarQuirofanos();
        if (cirugia != null && cirugia.getQuirofano() != null) {
            cboQuirofano.setSelectedItem(cirugia.getQuirofano());
        }
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        JPanel panelCampos = new JPanel(new GridLayout(7, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCampos.setBackground(fondo);

        txtDniPaciente = new JTextField();
        txtIdDoctor = new JTextField();
        txtFecha = new JTextField();
        txtHora = new JTextField();
        txtDescripcion = new JTextField();

        cboEstado = new JComboBox<>(Cirugia.Estado.values());

        cboQuirofano = new JComboBox<>();
        chkSoloDisponibles = new JCheckBox("Mostrar solo quirófanos disponibles", true);
        chkSoloDisponibles.setBackground(fondo);
        chkSoloDisponibles.addActionListener(e -> cargarQuirofanos());

        panelCampos.add(new JLabel("DNI Paciente:"));
        panelCampos.add(txtDniPaciente);

        panelCampos.add(new JLabel("ID Doctor:"));
        panelCampos.add(txtIdDoctor);

        panelCampos.add(new JLabel("Fecha (yyyy-MM-dd):"));
        panelCampos.add(txtFecha);

        panelCampos.add(new JLabel("Hora (HH:mm):"));
        panelCampos.add(txtHora);

        panelCampos.add(new JLabel("Quirófano:"));
        panelCampos.add(cboQuirofano);

        panelCampos.add(new JLabel("Descripción:"));
        panelCampos.add(txtDescripcion);

        panelCampos.add(new JLabel("Estado:"));
        panelCampos.add(cboEstado);

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTop.setBackground(fondo);
        panelTop.add(chkSoloDisponibles);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(fondo);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        add(panelTop, BorderLayout.NORTH);
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarQuirofanos() {
        cboQuirofano.removeAllItems();
        try {
            List<Habitacion> lista = habitacionDAO.listarTodos();
            boolean soloDisp = chkSoloDisponibles.isSelected();

            for (Habitacion h : lista) {
                if (h.getTipo() == TipoHabitacion.QUIROFANO) {
                    if (!soloDisp || h.isDisponible()) {
                        cboQuirofano.addItem(h);
                    }
                }
            }

            if (cirugiaOriginal != null && cirugiaOriginal.getQuirofano() != null) {
                Habitacion q = cirugiaOriginal.getQuirofano();
                boolean yaEsta = false;
                for (int i = 0; i < cboQuirofano.getItemCount(); i++) {
                    if (cboQuirofano.getItemAt(i).getIdHabitacion() == q.getIdHabitacion()) {
                        yaEsta = true;
                        break;
                    }
                }
                if (!yaEsta) {
                    cboQuirofano.addItem(q);
                }
                cboQuirofano.setSelectedItem(q);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error cargando quirófanos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatos() {
        txtDniPaciente.setText(cirugiaOriginal.getPaciente().getDni());
        txtIdDoctor.setText(String.valueOf(cirugiaOriginal.getCirujano().getIdTrabajador()));
        txtFecha.setText(cirugiaOriginal.getFecha().toString());

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
        txtHora.setText(cirugiaOriginal.getHora().format(fmt));

        txtDescripcion.setText(cirugiaOriginal.getDescripcion());
        cboEstado.setSelectedItem(cirugiaOriginal.getEstado());
    }

    private void guardar() {
        try {
            String dniPac = txtDniPaciente.getText().trim();
            String idDocStr = txtIdDoctor.getText().trim();
            String fechaStr = txtFecha.getText().trim();
            String horaStr = txtHora.getText().trim();
            String desc = txtDescripcion.getText().trim();
            Cirugia.Estado estado = (Cirugia.Estado) cboEstado.getSelectedItem();

            if (dniPac.isEmpty() || idDocStr.isEmpty() || fechaStr.isEmpty() || horaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "DNI, Doctor, Fecha y Hora son obligatorios",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idDoctor = Integer.parseInt(idDocStr);
            LocalDate fecha = LocalDate.parse(fechaStr);

            DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime hora = LocalTime.parse(horaStr, fmtHora).withSecond(0).withNano(0);

            Habitacion q = (Habitacion) cboQuirofano.getSelectedItem();
            if (q == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un quirófano",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!q.isDisponible()) {
                JOptionPane.showMessageDialog(this,
                        "El quirófano seleccionado no está disponible (mantenimiento).",
                        "No disponible",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Paciente p = pacienteDAO.buscarPorDni(dniPac);
            if (p == null) {
                JOptionPane.showMessageDialog(this, "Paciente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Doctor d = doctorDAO.buscar(idDoctor);
            if (d == null) {
                JOptionPane.showMessageDialog(this, "Doctor no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cirugiaDAO.existeCirugiaEnHorario(q.getIdHabitacion(), fecha, hora)) {

                if (cirugiaOriginal == null) {
                    JOptionPane.showMessageDialog(this,
                            "El quirófano ya está ocupado en esa fecha y hora.",
                            "Horario no disponible", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            if (cirugiaOriginal == null) {
                Cirugia nueva = new Cirugia(0, p, d, q, fecha, hora, desc, estado);
                cirugiaDAO.insertar(nueva);
            } else {
                cirugiaOriginal.setPaciente(p);
                cirugiaOriginal.setCirujano(d);
                cirugiaOriginal.setQuirofano(q);
                cirugiaOriginal.setFecha(fecha);
                cirugiaOriginal.setHora(hora);
                cirugiaOriginal.setDescripcion(desc);
                cirugiaOriginal.setEstado(estado);
                cirugiaDAO.actualizar(cirugiaOriginal);
            }

            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID de doctor inválido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}   