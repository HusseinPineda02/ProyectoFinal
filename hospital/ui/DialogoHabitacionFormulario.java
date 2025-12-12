package hospital.ui;

import hospital.dao.HabitacionDAO;
import hospital.modelo.Habitacion;
import hospital.modelo.TipoHabitacion;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DialogoHabitacionFormulario extends JDialog {

    private JComboBox<TipoHabitacion> cboTipo;
    private JTextField txtPiso;
    private JTextField txtNumero;
    private JCheckBox chkDisponible;

    private Habitacion habitacionOriginal;
    private HabitacionDAO habitacionDAO;

    public DialogoHabitacionFormulario(Dialog owner, Habitacion habitacion, HabitacionDAO dao) {
        super(owner, true);
        this.habitacionOriginal = habitacion;
        this.habitacionDAO = dao;

        setTitle(habitacion == null ? "Nueva Habitación" : "Editar Habitación");
        setSize(400, 250);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        if (habitacion != null) cargarDatos();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCampos.setBackground(fondo);

        cboTipo = new JComboBox<>(TipoHabitacion.values());
        txtPiso = new JTextField();
        txtNumero = new JTextField();
        chkDisponible = new JCheckBox("Disponible");
        chkDisponible.setBackground(fondo);

        panelCampos.add(new JLabel("Tipo:"));
        panelCampos.add(cboTipo);
        panelCampos.add(new JLabel("Piso:"));
        panelCampos.add(txtPiso);
        panelCampos.add(new JLabel("Número:"));
        panelCampos.add(txtNumero);
        panelCampos.add(new JLabel(" "));
        panelCampos.add(chkDisponible);

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
        cboTipo.setSelectedItem(habitacionOriginal.getTipo());
        txtPiso.setText(String.valueOf(habitacionOriginal.getPiso()));
        txtNumero.setText(habitacionOriginal.getNumero());
        chkDisponible.setSelected(habitacionOriginal.isDisponible());
    }

    private void guardar() {
        try {
            TipoHabitacion tipo = (TipoHabitacion) cboTipo.getSelectedItem();
            int piso = Integer.parseInt(txtPiso.getText().trim());
            String numero = txtNumero.getText().trim();
            boolean disponible = chkDisponible.isSelected();

            if (numero.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Número obligatorio", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (habitacionOriginal == null) {
                Habitacion nueva = new Habitacion(0, tipo, piso, numero, disponible);
                habitacionDAO.insertar(nueva);
            } else {
                habitacionOriginal.setTipo(tipo);
                habitacionOriginal.setPiso(piso);
                habitacionOriginal.setNumero(numero);
                habitacionOriginal.setDisponible(disponible);
                habitacionDAO.actualizar(habitacionOriginal);
            }

            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Piso inválido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
        }
    }
}