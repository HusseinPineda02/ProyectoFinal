package hospital.ui;

import hospital.dao.HistoriaClinicaDAO;
import hospital.dao.PacienteDAO;
import hospital.dao.RegistroClinicoDAO;
import hospital.modelo.HistoriaClinica;
import hospital.modelo.Paciente;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaPacientes extends JDialog {

    private PacienteDAO pacienteDAO;
    private JTable tabla;
    private DefaultTableModel modelo;

    public VentanaPacientes(Frame owner, PacienteDAO dao) {
        super(owner, "Pacientes", true);
        this.pacienteDAO = dao;

        setSize(750, 400);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        cargarPacientes();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        modelo = new DefaultTableModel(
                new Object[]{"DNI", "Nombre", "Ap. Paterno", "Ap. Materno",
                             "Fecha Nac.", "Teléfono", "Dirección"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(fondo);

        JButton btnNuevo = new JButton("Nuevo");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnHistoria = new JButton("Ver Historia Clínica");
        JButton btnCerrar = new JButton("Cerrar");

        btnNuevo.addActionListener(e -> nuevoPaciente());
        btnEditar.addActionListener(e -> editarPaciente());
        btnEliminar.addActionListener(e -> eliminarPaciente());
        btnHistoria.addActionListener(e -> verHistoriaClinica());
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnHistoria);
        panelBotones.add(btnCerrar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarPacientes() {
        modelo.setRowCount(0);
        try {
            List<Paciente> lista = pacienteDAO.listarTodos();
            for (Paciente p : lista) {
                modelo.addRow(new Object[]{
                        p.getDni(),
                        p.getNombre(),
                        p.getApellidoP(),
                        p.getApellidoM(),
                        p.getFechaNacimiento() != null ? p.getFechaNacimiento() : "",
                        p.getTelefono(),
                        p.getDireccion()
                });
            }
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void nuevoPaciente() {
        DialogoPacienteFormulario dlg =
                new DialogoPacienteFormulario(this, null, pacienteDAO);
        dlg.setVisible(true);
        cargarPacientes();
    }

    private void editarPaciente() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un paciente",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String dni = (String) modelo.getValueAt(fila, 0);
        try {
            Paciente p = pacienteDAO.buscarPorDni(dni);
            if (p == null) return;

            DialogoPacienteFormulario dlg =
                    new DialogoPacienteFormulario(this, p, pacienteDAO);
            dlg.setVisible(true);
            cargarPacientes();
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void eliminarPaciente() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un paciente",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String dni = (String) modelo.getValueAt(fila, 0);
        int resp = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar paciente con DNI " + dni + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (resp == JOptionPane.YES_OPTION) {
            try {
                pacienteDAO.eliminar(dni);
                cargarPacientes();
            } catch (SQLException ex) {
                mostrarError(ex);
            }
        }
    }

    private void verHistoriaClinica() {

        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona un paciente",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String dni = (String) modelo.getValueAt(fila, 0);

        try {
            Paciente p = pacienteDAO.buscarPorDni(dni);
            if (p == null) {
                JOptionPane.showMessageDialog(this,
                        "Paciente no encontrado",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            HistoriaClinicaDAO hdao = new HistoriaClinicaDAO();
            HistoriaClinica historia = hdao.buscarPorPaciente(dni);

            if (historia == null) {
                historia = hdao.crear(p);
                if (historia == null) {
                    JOptionPane.showMessageDialog(this,
                            "No se pudo crear la historia clínica",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            RegistroClinicoDAO rdao = new RegistroClinicoDAO();
            historia.getRegistros().clear();
            historia.getRegistros().addAll(rdao.listarPorPaciente(dni));

            VentanaHistoriaClinica dlg = new VentanaHistoriaClinica(this, historia);
            dlg.setVisible(true);

        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }


    private void mostrarError(Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}