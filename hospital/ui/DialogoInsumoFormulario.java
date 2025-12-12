package hospital.ui;

import hospital.dao.InsumoDAO;
import hospital.modelo.Insumo;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class DialogoInsumoFormulario extends JDialog {

    private JTextField txtNombre;
    private JTextField txtCategoria;
    private JTextField txtDescripcion;
    private JTextField txtStock;
    private JTextField txtPrecio;

    private Insumo insumoOriginal;
    private InsumoDAO insumoDAO;

    public DialogoInsumoFormulario(Dialog owner, Insumo insumo, InsumoDAO dao) {
        super(owner, true);
        this.insumoOriginal = insumo;
        this.insumoDAO = dao;

        setTitle(insumo == null ? "Nuevo Insumo" : "Editar Insumo");
        setSize(400, 300);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        if (insumo != null) cargarDatos();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCampos.setBackground(fondo);

        txtNombre = new JTextField();
        txtCategoria = new JTextField();
        txtDescripcion = new JTextField();
        txtStock = new JTextField();
        txtPrecio = new JTextField();

        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Categoría:"));
        panelCampos.add(txtCategoria);
        panelCampos.add(new JLabel("Descripción:"));
        panelCampos.add(txtDescripcion);
        panelCampos.add(new JLabel("Stock:"));
        panelCampos.add(txtStock);
        panelCampos.add(new JLabel("Precio Unitario:"));
        panelCampos.add(txtPrecio);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(fondo);
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> dispose());

        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarDatos() {
        txtNombre.setText(insumoOriginal.getNombre());
        txtCategoria.setText(insumoOriginal.getCategoria());
        txtDescripcion.setText(insumoOriginal.getDescripcion());
        txtStock.setText(String.valueOf(insumoOriginal.getStock()));
        txtPrecio.setText(String.valueOf(insumoOriginal.getPrecioUnitario()));
    }

    private void guardar() {
        try {
            String nombre = txtNombre.getText().trim();
            String categoria = txtCategoria.getText().trim();
            String desc = txtDescripcion.getText().trim();
            int stock = 0;
            double precio = 0;

            if (!txtStock.getText().trim().isEmpty()) {
                stock = Integer.parseInt(txtStock.getText().trim());
            }
            if (!txtPrecio.getText().trim().isEmpty()) {
                precio = Double.parseDouble(txtPrecio.getText().trim());
            }

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre obligatorio", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (insumoOriginal == null) {
                Insumo nuevo = new Insumo(0, nombre, categoria, desc, stock, precio);
                insumoDAO.insertar(nuevo);
            } else {
                insumoOriginal.setNombre(nombre);
                insumoOriginal.setCategoria(categoria);
                insumoOriginal.setDescripcion(desc);
                insumoOriginal.setStock(stock);
                insumoOriginal.setPrecioUnitario(precio);
                insumoDAO.actualizar(insumoOriginal);
            }

            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Stock o Precio inválidos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}