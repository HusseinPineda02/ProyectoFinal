package hospital.ui;

import hospital.dao.CitaDAO;
import hospital.dao.DoctorDAO;
import hospital.dao.HabitacionDAO;
import hospital.dao.PacienteDAO;
import hospital.modelo.Cita;
import hospital.modelo.Doctor;
import hospital.modelo.Habitacion;
import hospital.modelo.Paciente;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaCitas extends JDialog {

    private CitaDAO citaDAO;
    private PacienteDAO pacienteDAO;
    private DoctorDAO doctorDAO;
    private HabitacionDAO habitacionDAO;

    private JTable tabla;
    private DefaultTableModel modelo;

    public VentanaCitas(Frame owner,
                        CitaDAO citaDAO,
                        PacienteDAO pacienteDAO,
                        DoctorDAO doctorDAO,
                        HabitacionDAO habitacionDAO) {

        super(owner, "Citas", true);
        this.citaDAO = citaDAO;
        this.pacienteDAO = pacienteDAO;
        this.doctorDAO = doctorDAO;
        this.habitacionDAO = habitacionDAO;

        setSize(950, 420);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        cargarCitas();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        modelo = new DefaultTableModel(
                new Object[]{
                        "ID",
                        "Fecha",
                        "Hora",
                        "DNI Paciente",
                        "Paciente",
                        "ID Doctor",
                        "Doctor",
                        "ID Hab.",
                        "Tipo Hab.",
                        "Ubicación",
                        "Motivo",
                        "Estado"
                },
                0
        );

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(fondo);

        JButton btnNuevo = new JButton("Nuevo");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCerrar = new JButton("Cerrar");
        JButton btnAtendida = new JButton("Marcar como atendida");
  

        
        btnNuevo.addActionListener(e -> nuevaCita());
        btnEditar.addActionListener(e -> editarCita());
        btnEliminar.addActionListener(e -> eliminarCita());
        btnCerrar.addActionListener(e -> dispose());
        btnAtendida.addActionListener(e -> marcarAtendida());

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);
        panelBotones.add(btnAtendida);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarCitas() {
        modelo.setRowCount(0);

        try {
            List<Cita> lista = citaDAO.listarTodos();

            for (Cita c : lista) {

                Paciente p = c.getPaciente();
                Doctor d = c.getDoctor();
                Habitacion h = c.getHabitacion();

                modelo.addRow(new Object[]{
                        c.getIdCita(),
                        c.getFecha(),
                        c.getHora(),
                        p != null ? p.getDni() : "",
                        p != null ? p.getNombreCompleto() : "",
                        d != null ? d.getIdTrabajador() : "",
                        d != null ? d.getNombreCompleto() : "",
                        h != null ? h.getIdHabitacion() : "",
                        h != null ? h.getTipo() : "",
                        h != null ? ("Piso " + h.getPiso() + " - N° " + h.getNumero()) : "",
                        c.getMotivo(),
                        c.getEstado().name()
                });
            }

        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void nuevaCita() {
        DialogoCitaFormulario dlg =
                new DialogoCitaFormulario(this, null,
                        citaDAO, pacienteDAO, doctorDAO, habitacionDAO);

        dlg.setVisible(true);
        cargarCitas();
    }

    private void editarCita() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona una cita",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        try {
            Cita c = citaDAO.buscar(id);
            if (c == null) {
                JOptionPane.showMessageDialog(this,
                        "Cita no encontrada",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            DialogoCitaFormulario dlg =
                    new DialogoCitaFormulario(this, c,
                            citaDAO, pacienteDAO, doctorDAO, habitacionDAO);

            dlg.setVisible(true);
            cargarCitas();

        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void eliminarCita() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona una cita",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        int resp = JOptionPane.showConfirmDialog(this,
                "¿Eliminar cita con ID " + id + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (resp == JOptionPane.YES_OPTION) {
            try {
                citaDAO.eliminar(id);
                cargarCitas();
            } catch (SQLException ex) {
                mostrarError(ex);
            }
        }
    }

    private void mostrarError(Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void marcarAtendida() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una cita", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        try {
            citaDAO.marcarAtendidaYRegistrarHistoria(id);
            cargarCitas();
            JOptionPane.showMessageDialog(this, "Cita marcada como ATENDIDA y registrada en Historia Clínica.");
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }
}