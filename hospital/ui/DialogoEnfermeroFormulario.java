package hospital.ui;

import hospital.dao.EnfermeroDAO;
import hospital.modelo.Enfermero;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DialogoEnfermeroFormulario extends JDialog {

    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApP;
    private JTextField txtApM;
    private JTextField txtSalario;
    private JTextField txtTurno;

    private Enfermero enfermeroOriginal;
    private EnfermeroDAO enfermeroDAO;

    public DialogoEnfermeroFormulario(Dialog owner, Enfermero enfermero, EnfermeroDAO dao) {
        super(owner, true);
        this.enfermeroOriginal = enfermero;
        this.enfermeroDAO = dao;

        setTitle(enfermero == null ? "Nuevo Enfermero" : "Editar Enfermero");
        setSize(400, 300);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        if (enfermero != null) cargarDatos();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCampos.setBackground(fondo);

        txtDni = new JTextField();
        txtNombre = new JTextField();
        txtApP = new JTextField();
        txtApM = new JTextField();
        txtSalario = new JTextField();
        txtTurno = new JTextField();

        panelCampos.add(new JLabel("DNI:"));
        panelCampos.add(txtDni);
        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Apellido Paterno:"));
        panelCampos.add(txtApP);
        panelCampos.add(new JLabel("Apellido Materno:"));
        panelCampos.add(txtApM);
        panelCampos.add(new JLabel("Salario:"));
        panelCampos.add(txtSalario);
        panelCampos.add(new JLabel("Turno:"));
        panelCampos.add(txtTurno);

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
        txtDni.setText(enfermeroOriginal.getDni());
        txtNombre.setText(enfermeroOriginal.getNombre());
        txtApP.setText(enfermeroOriginal.getApellidoP());
        txtApM.setText(enfermeroOriginal.getApellidoM());
        txtSalario.setText(String.valueOf(enfermeroOriginal.getSalario()));
        txtTurno.setText(enfermeroOriginal.getTurno());
    }

    private void guardar() {
        try {
            String dni = txtDni.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apP = txtApP.getText().trim();
            String apM = txtApM.getText().trim();
            String turno = txtTurno.getText().trim();
            double salario = 0;

            if (!txtSalario.getText().trim().isEmpty()) {
                salario = Double.parseDouble(txtSalario.getText().trim());
            }

            if (dni.isEmpty() || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "DNI y Nombre son obligatorios", "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (enfermeroOriginal == null) {
                Enfermero nuevo = new Enfermero(0, dni, nombre, apP, apM, salario, turno);
                enfermeroDAO.insertar(nuevo);
            } else {
                enfermeroOriginal.setDni(dni);
                enfermeroOriginal.setNombre(nombre);
                enfermeroOriginal.setApellidoP(apP);
                enfermeroOriginal.setApellidoM(apM);
                enfermeroOriginal.setSalario(salario);
                enfermeroOriginal.setTurno(turno);
                enfermeroDAO.actualizar(enfermeroOriginal);
            }

            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Salario inválido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}