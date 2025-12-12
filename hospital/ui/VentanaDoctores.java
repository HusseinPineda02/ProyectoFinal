package hospital.ui;

import hospital.dao.DoctorDAO;
import hospital.modelo.Doctor;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaDoctores extends JDialog {

    private DoctorDAO doctorDAO;
    private JTable tabla;
    private DefaultTableModel modelo;

    public VentanaDoctores(Frame owner, DoctorDAO dao) {
        super(owner, "Doctores", true);
        this.doctorDAO = dao;

        setSize(700, 400);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        cargarDoctores();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        modelo = new DefaultTableModel(
                new Object[]{"ID", "DNI", "Nombre", "Ap. Paterno", "Ap. Materno", "Salario", "Especialidad"},
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

        btnNuevo.addActionListener(e -> nuevoDoctor());
        btnEditar.addActionListener(e -> editarDoctor());
        btnEliminar.addActionListener(e -> eliminarDoctor());
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarDoctores() {
        modelo.setRowCount(0);
        try {
            List<Doctor> lista = doctorDAO.listarTodos();
            for (Doctor d : lista) {
                modelo.addRow(new Object[]{
                        d.getIdTrabajador(),
                        d.getDni(),
                        d.getNombre(),
                        d.getApellidoP(),
                        d.getApellidoM(),
                        d.getSalario(),
                        d.getEspecialidad()
                });
            }
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void nuevoDoctor() {
        DialogoDoctorFormulario dlg = new DialogoDoctorFormulario(this, null, doctorDAO);
        dlg.setVisible(true);
        cargarDoctores();
    }

    private void editarDoctor() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un doctor", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        try {
            Doctor d = doctorDAO.buscar(id);
            if (d == null) {
                JOptionPane.showMessageDialog(this, "Doctor no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DialogoDoctorFormulario dlg = new DialogoDoctorFormulario(this, d, doctorDAO);
            dlg.setVisible(true);
            cargarDoctores();
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void eliminarDoctor() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un doctor", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        int resp = JOptionPane.showConfirmDialog(this,
                "¿Eliminar doctor con ID " + id + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                doctorDAO.eliminar(id);
                cargarDoctores();
            } catch (SQLException ex) {
                mostrarError(ex);
            }
        }
    }

    private void mostrarError(Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}