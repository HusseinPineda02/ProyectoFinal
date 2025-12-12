package hospital.ui;

import hospital.dao.MedicamentoDAO;
import hospital.modelo.Medicamento;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class DialogoMedicamentoFormulario extends JDialog {

    private JTextField txtNombre;
    private JTextField txtTipo;
    private JTextField txtDescripcion;
    private JTextField txtStock;
    private JTextField txtPrecio;

    private Medicamento medicamentoOriginal;
    private MedicamentoDAO medicamentoDAO;

    public DialogoMedicamentoFormulario(Dialog owner, Medicamento medicamento, MedicamentoDAO dao) {
        super(owner, true);
        this.medicamentoOriginal = medicamento;
        this.medicamentoDAO = dao;

        setTitle(medicamento == null ? "Nuevo Medicamento" : "Editar Medicamento");
        setSize(400, 300);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        if (medicamento != null) cargarDatos();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCampos.setBackground(fondo);

        txtNombre = new JTextField();
        txtTipo = new JTextField();
        txtDescripcion = new JTextField();
        txtStock = new JTextField();
        txtPrecio = new JTextField();

        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Tipo:"));
        panelCampos.add(txtTipo);
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
        txtNombre.setText(medicamentoOriginal.getNombre());
        txtTipo.setText(medicamentoOriginal.getTipo());
        txtDescripcion.setText(medicamentoOriginal.getDescripcion());
        txtStock.setText(String.valueOf(medicamentoOriginal.getStock()));
        txtPrecio.setText(String.valueOf(medicamentoOriginal.getPrecioUnitario()));
    }

    private void guardar() {
        try {
            String nombre = txtNombre.getText().trim();
            String tipo = txtTipo.getText().trim();
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

            if (medicamentoOriginal == null) {
                Medicamento nuevo = new Medicamento(0, nombre, tipo, desc, stock, precio);
                medicamentoDAO.insertar(nuevo);
            } else {
                medicamentoOriginal.setNombre(nombre);
                medicamentoOriginal.setTipo(tipo);
                medicamentoOriginal.setDescripcion(desc);
                medicamentoOriginal.setStock(stock);
                medicamentoOriginal.setPrecioUnitario(precio);
                medicamentoDAO.actualizar(medicamentoOriginal);
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