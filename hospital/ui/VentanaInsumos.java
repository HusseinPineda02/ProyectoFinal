package hospital.ui;

import hospital.dao.InsumoDAO;
import hospital.modelo.Insumo;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaInsumos extends JDialog {

    private InsumoDAO insumoDAO;
    private JTable tabla;
    private DefaultTableModel modelo;

    public VentanaInsumos(Frame owner, InsumoDAO dao) {
        super(owner, "Insumos", true);
        this.insumoDAO = dao;

        setSize(700, 400);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        cargarInsumos();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Categoría", "Descripción", "Stock", "Precio"},
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

        btnNuevo.addActionListener(e -> nuevoInsumo());
        btnEditar.addActionListener(e -> editarInsumo());
        btnEliminar.addActionListener(e -> eliminarInsumo());
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarInsumos() {
        modelo.setRowCount(0);
        try {
            List<Insumo> lista = insumoDAO.listarTodos();
            for (Insumo i : lista) {
                modelo.addRow(new Object[]{
                        i.getIdInsumo(),
                        i.getNombre(),
                        i.getCategoria(),
                        i.getDescripcion(),
                        i.getStock(),
                        i.getPrecioUnitario()
                });
            }
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void nuevoInsumo() {
        DialogoInsumoFormulario dlg = new DialogoInsumoFormulario(this, null, insumoDAO);
        dlg.setVisible(true);
        cargarInsumos();
    }

    private void editarInsumo() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un insumo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        try {
            Insumo i = insumoDAO.buscar(id);
            if (i == null) {
                JOptionPane.showMessageDialog(this, "Insumo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DialogoInsumoFormulario dlg = new DialogoInsumoFormulario(this, i, insumoDAO);
            dlg.setVisible(true);
            cargarInsumos();
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void eliminarInsumo() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un insumo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        int resp = JOptionPane.showConfirmDialog(this,
                "¿Eliminar insumo con ID " + id + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                insumoDAO.eliminar(id);
                cargarInsumos();
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