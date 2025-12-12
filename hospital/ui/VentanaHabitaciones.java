package hospital.ui;

import hospital.dao.HabitacionDAO;
import hospital.modelo.Habitacion;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaHabitaciones extends JDialog {

    private HabitacionDAO habitacionDAO;
    private JTable tabla;
    private DefaultTableModel modelo;

    public VentanaHabitaciones(Frame owner, HabitacionDAO dao) {
        super(owner, "Habitaciones", true);
        this.habitacionDAO = dao;

        setSize(700, 400);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        cargarHabitaciones();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Tipo", "Piso", "Número", "Disponible"},
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

        btnNuevo.addActionListener(e -> nuevaHabitacion());
        btnEditar.addActionListener(e -> editarHabitacion());
        btnEliminar.addActionListener(e -> eliminarHabitacion());
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarHabitaciones() {
        modelo.setRowCount(0);
        try {
            List<Habitacion> lista = habitacionDAO.listarTodos();
            for (Habitacion h : lista) {
                modelo.addRow(new Object[]{
                        h.getIdHabitacion(),
                        h.getTipo().name(),
                        h.getPiso(),
                        h.getNumero(),
                        h.isDisponible() ? "Sí" : "No"
                });
            }
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void nuevaHabitacion() {
        DialogoHabitacionFormulario dlg = new DialogoHabitacionFormulario(this, null, habitacionDAO);
        dlg.setVisible(true);
        cargarHabitaciones();
    }

    private void editarHabitacion() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una habitación", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        try {
            Habitacion h = habitacionDAO.buscar(id);
            if (h == null) {
                JOptionPane.showMessageDialog(this, "Habitación no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DialogoHabitacionFormulario dlg = new DialogoHabitacionFormulario(this, h, habitacionDAO);
            dlg.setVisible(true);
            cargarHabitaciones();
        } catch (SQLException ex) {
            mostrarError(ex);
        }
    }

    private void eliminarHabitacion() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una habitación", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        int resp = JOptionPane.showConfirmDialog(this,
                "¿Eliminar habitación con ID " + id + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                habitacionDAO.eliminar(id);
                cargarHabitaciones();
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