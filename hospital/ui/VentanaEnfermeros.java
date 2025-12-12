package hospital.ui;

import hospital.dao.EnfermeroDAO;
import hospital.modelo.Enfermero;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaEnfermeros extends JDialog {

    private EnfermeroDAO enfermeroDAO;
    private JTable tabla;
    private DefaultTableModel modelo;

    public VentanaEnfermeros(Frame owner, EnfermeroDAO dao) {
        super(owner, "Enfermeros", true);
        this.enfermeroDAO = dao;

        setSize(700, 400);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        cargarEnfermeros();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        modelo = new DefaultTableModel(
                new Object[]{"ID", "DNI", "Nombre", "Ap. Paterno", "Ap. Materno", "Salario", "Turno"},
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

        btnNuevo.addActionListener(e -> nuevoEnfermero());
        btnEditar.addActionListener(e -> editarEnfermero());
        btnEliminar.addActionListener(e -> eliminarEnfermero());
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarEnfermeros() {
        modelo.setRowCount(0);
        try {
            List<Enfermero> lista = enfermeroDAO.listarTodos();
            for (Enfermero e : lista) {
                modelo.addRow(new Object[]{
                        e.getIdTrabajador(),
                        e.getDni(),
                        e.getNombre(),
                        e.getApellidoP(),
                        e.getApellidoM(),
                        e.getSalario(),
                        e.getTurno()
                });
            }
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void nuevoEnfermero() {
        DialogoEnfermeroFormulario dlg = new DialogoEnfermeroFormulario(this, null, enfermeroDAO);
        dlg.setVisible(true);
        cargarEnfermeros();
    }

    private void editarEnfermero() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un enfermero", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        try {
            Enfermero e = enfermeroDAO.buscar(id);
            if (e == null) {
                JOptionPane.showMessageDialog(this, "Enfermero no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DialogoEnfermeroFormulario dlg = new DialogoEnfermeroFormulario(this, e, enfermeroDAO);
            dlg.setVisible(true);
            cargarEnfermeros();
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void eliminarEnfermero() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un enfermero", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        int resp = JOptionPane.showConfirmDialog(this,
                "¿Eliminar enfermero con ID " + id + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                enfermeroDAO.eliminar(id);
                cargarEnfermeros();
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