package hospital.ui;

import hospital.dao.CirugiaDAO;
import hospital.dao.DoctorDAO;
import hospital.dao.HabitacionDAO;
import hospital.dao.PacienteDAO;
import hospital.modelo.Cirugia;
import hospital.modelo.Doctor;
import hospital.modelo.Habitacion;
import hospital.modelo.Paciente;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaCirugias extends JDialog {

    private CirugiaDAO cirugiaDAO;
    private PacienteDAO pacienteDAO;
    private DoctorDAO doctorDAO;
    private HabitacionDAO habitacionDAO;

    private JTable tabla;
    private DefaultTableModel modelo;

    public VentanaCirugias(Frame owner, CirugiaDAO cdao, PacienteDAO pdao,
                           DoctorDAO ddao, HabitacionDAO hdao) {
        super(owner, "Cirugías", true);
        this.cirugiaDAO = cdao;
        this.pacienteDAO = pdao;
        this.doctorDAO = ddao;
        this.habitacionDAO = hdao;

        setSize(900, 400);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        cargarCirugias();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Fecha", "Hora", "DNI Paciente", "Paciente",
                        "ID Doctor", "Doctor", "ID Habitación", "Estado", "Descripción"},
                0
        );
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(fondo);
        JButton btnNuevo = new JButton("Nueva");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCerrar = new JButton("Cerrar");
        JButton btnRealizada = new JButton("Marcar como realizada");
            
        btnNuevo.addActionListener(e -> nuevaCirugia());
        btnEditar.addActionListener(e -> editarCirugia());
        btnEliminar.addActionListener(e -> eliminarCirugia());
        btnCerrar.addActionListener(e -> dispose());
        btnRealizada.addActionListener(e -> marcarRealizada());

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);
        panelBotones.add(btnRealizada);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarCirugias() {
        modelo.setRowCount(0);
        try {
            List<Cirugia> lista = cirugiaDAO.listarTodos();
            for (Cirugia c : lista) {
                Paciente p = c.getPaciente();
                Doctor d = c.getCirujano();
                Habitacion h = c.getQuirofano();

                modelo.addRow(new Object[]{
                        c.getIdCirugia(),
                        c.getFecha(),
                        c.getHora(),
                        p != null ? p.getDni() : "",
                        p != null ? p.getNombreCompleto() : "",
                        d != null ? d.getIdTrabajador() : null,
                        d != null ? d.getNombreCompleto() : "",
                        h != null ? h.getIdHabitacion() : null,
                        c.getEstado().name(),
                        c.getDescripcion()
                });
            }
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void nuevaCirugia() {
        DialogoCirugiaFormulario dlg = new DialogoCirugiaFormulario(this, null,
                cirugiaDAO, pacienteDAO, doctorDAO, habitacionDAO);
        dlg.setVisible(true);
        cargarCirugias();
    }

    private void editarCirugia() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una cirugía", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        try {
            Cirugia c = cirugiaDAO.buscar(id);
            if (c == null) {
                JOptionPane.showMessageDialog(this, "Cirugía no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DialogoCirugiaFormulario dlg = new DialogoCirugiaFormulario(this, c,
                    cirugiaDAO, pacienteDAO, doctorDAO, habitacionDAO);
            dlg.setVisible(true);
            cargarCirugias();
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void eliminarCirugia() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una cirugía", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        int resp = JOptionPane.showConfirmDialog(this,
                "¿Eliminar cirugía con ID " + id + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                cirugiaDAO.eliminar(id);
                cargarCirugias();
            } catch (SQLException ex) {
                mostrarError(ex);
            }
        }
    }

    private void mostrarError(Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void marcarRealizada() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una cirugía", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        try {
            cirugiaDAO.marcarRealizadaYRegistrarHistoria(id);
            cargarCirugias();
            JOptionPane.showMessageDialog(this, "Cirugía marcada como REALIZADA y registrada en Historia Clínica.");
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }
}