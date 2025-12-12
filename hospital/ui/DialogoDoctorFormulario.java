package hospital.ui;

import hospital.dao.DoctorDAO;
import hospital.modelo.Doctor;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class DialogoDoctorFormulario extends JDialog {

    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApP;
    private JTextField txtApM;
    private JTextField txtSalario;
    private JTextField txtEspecialidad;

    private Doctor doctorOriginal;
    private DoctorDAO doctorDAO;

    public DialogoDoctorFormulario(Dialog owner, Doctor doctor, DoctorDAO dao) {
        super(owner, true);
        this.doctorOriginal = doctor;
        this.doctorDAO = dao;

        setTitle(doctor == null ? "Nuevo Doctor" : "Editar Doctor");
        setSize(400, 300);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        if (doctor != null) cargarDatos();
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
        txtEspecialidad = new JTextField();

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
        panelCampos.add(new JLabel("Especialidad:"));
        panelCampos.add(txtEspecialidad);

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
        txtDni.setText(doctorOriginal.getDni());
        txtNombre.setText(doctorOriginal.getNombre());
        txtApP.setText(doctorOriginal.getApellidoP());
        txtApM.setText(doctorOriginal.getApellidoM());
        txtSalario.setText(String.valueOf(doctorOriginal.getSalario()));
        txtEspecialidad.setText(doctorOriginal.getEspecialidad());
    }

    private void guardar() {
        try {
            String dni = txtDni.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apP = txtApP.getText().trim();
            String apM = txtApM.getText().trim();
            String especialidad = txtEspecialidad.getText().trim();
            double salario = 0;

            if (!txtSalario.getText().trim().isEmpty()) {
                salario = Double.parseDouble(txtSalario.getText().trim());
            }

            if (dni.isEmpty() || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "DNI y Nombre son obligatorios", "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (doctorOriginal == null) {
                Doctor nuevo = new Doctor(0, dni, nombre, apP, apM, salario, especialidad);
                doctorDAO.insertar(nuevo);
            } else {
                doctorOriginal.setDni(dni);
                doctorOriginal.setNombre(nombre);
                doctorOriginal.setApellidoP(apP);
                doctorOriginal.setApellidoM(apM);
                doctorOriginal.setSalario(salario);
                doctorOriginal.setEspecialidad(especialidad);
                doctorDAO.actualizar(doctorOriginal);
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