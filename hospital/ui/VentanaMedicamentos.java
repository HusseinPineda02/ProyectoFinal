package hospital.ui;

import hospital.dao.MedicamentoDAO;
import hospital.modelo.Medicamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class VentanaMedicamentos extends JDialog {

    private MedicamentoDAO medicamentoDAO;
    private JTable tabla;
    private DefaultTableModel modelo;

    public VentanaMedicamentos(Frame owner, MedicamentoDAO dao) {
        super(owner, "Medicamentos", true);
        this.medicamentoDAO = dao;

        setSize(700, 400);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        cargarMedicamentos();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Tipo", "Descripción", "Stock", "Precio"},
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

        btnNuevo.addActionListener(e -> nuevoMedicamento());
        btnEditar.addActionListener(e -> editarMedicamento());
        btnEliminar.addActionListener(e -> eliminarMedicamento());
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarMedicamentos() {
        modelo.setRowCount(0);
        try {
            List<Medicamento> lista = medicamentoDAO.listarTodos();
            for (Medicamento m : lista) {
                modelo.addRow(new Object[]{
                        m.getIdMedicamento(),
                        m.getNombre(),
                        m.getTipo(),
                        m.getDescripcion(),
                        m.getStock(),
                        m.getPrecioUnitario()
                });
            }
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void nuevoMedicamento() {
        DialogoMedicamentoFormulario dlg = new DialogoMedicamentoFormulario(this, null, medicamentoDAO);
        dlg.setVisible(true);
        cargarMedicamentos();
    }

    private void editarMedicamento() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un medicamento", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        try {
            Medicamento m = medicamentoDAO.buscar(id);
            if (m == null) {
                JOptionPane.showMessageDialog(this, "Medicamento no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DialogoMedicamentoFormulario dlg = new DialogoMedicamentoFormulario(this, m, medicamentoDAO);
            dlg.setVisible(true);
            cargarMedicamentos();
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void eliminarMedicamento() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un medicamento", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        int resp = JOptionPane.showConfirmDialog(this,
                "¿Eliminar medicamento con ID " + id + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                medicamentoDAO.eliminar(id);
                cargarMedicamentos();
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