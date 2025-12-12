package hospital.ui;

import hospital.dao.PacienteDAO;
import hospital.modelo.Paciente;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.*;

public class DialogoPacienteFormulario extends JDialog {

    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApP;
    private JTextField txtApM;
    private JTextField txtFechaNac;
    private JTextField txtTelefono;
    private JTextField txtDireccion;

    private Paciente pacienteOriginal;
    private PacienteDAO pacienteDAO;

    public DialogoPacienteFormulario(Dialog owner, Paciente paciente, PacienteDAO dao) {
        super(owner, true);
        this.pacienteOriginal = paciente;
        this.pacienteDAO = dao;

        setTitle(paciente == null ? "Nuevo Paciente" : "Editar Paciente");
        setSize(400, 350);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        if (paciente != null) cargarDatos();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        JPanel panelCampos = new JPanel(new GridLayout(7, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCampos.setBackground(fondo);

        txtDni = new JTextField();
        txtNombre = new JTextField();
        txtApP = new JTextField();
        txtApM = new JTextField();
        txtFechaNac = new JTextField();
        txtTelefono = new JTextField();
        txtDireccion = new JTextField();

        panelCampos.add(new JLabel("DNI:"));
        panelCampos.add(txtDni);
        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Apellido Paterno:"));
        panelCampos.add(txtApP);
        panelCampos.add(new JLabel("Apellido Materno:"));
        panelCampos.add(txtApM);
        panelCampos.add(new JLabel("Fecha Nac. (yyyy-MM-dd):"));
        panelCampos.add(txtFechaNac);
        panelCampos.add(new JLabel("Teléfono:"));
        panelCampos.add(txtTelefono);
        panelCampos.add(new JLabel("Dirección:"));
        panelCampos.add(txtDireccion);

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
        txtDni.setText(pacienteOriginal.getDni());
        txtDni.setEnabled(false);
        txtNombre.setText(pacienteOriginal.getNombre());
        txtApP.setText(pacienteOriginal.getApellidoP());
        txtApM.setText(pacienteOriginal.getApellidoM());
        txtTelefono.setText(pacienteOriginal.getTelefono());
        txtDireccion.setText(pacienteOriginal.getDireccion());
        if (pacienteOriginal.getFechaNacimiento() != null) {
            txtFechaNac.setText(pacienteOriginal.getFechaNacimiento().toString());
        }
    }

    private void guardar() {
        try {
            String dni = txtDni.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apP = txtApP.getText().trim();
            String apM = txtApM.getText().trim();
            String tel = txtTelefono.getText().trim();
            String dir = txtDireccion.getText().trim();
            String fechaStr = txtFechaNac.getText().trim();

            LocalDate fecha = null;
            if (!fechaStr.isEmpty()) {
                fecha = LocalDate.parse(fechaStr);
            }

            if (dni.isEmpty() || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "DNI y Nombre son obligatorios", "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (pacienteOriginal == null) {

                Paciente nuevo = new Paciente(dni, nombre, apP, apM, fecha, tel, dir);
                pacienteDAO.insertar(nuevo);
            } else {
        
                pacienteOriginal.setNombre(nombre);
                pacienteOriginal.setApellidoP(apP);
                pacienteOriginal.setApellidoM(apM);
                pacienteOriginal.setTelefono(tel);
                pacienteOriginal.setDireccion(dir);
                pacienteOriginal.setFechaNacimiento(fecha);

                pacienteDAO.actualizar(pacienteOriginal);
            }

            dispose();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error BD", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}