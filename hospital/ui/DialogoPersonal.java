package hospital.ui;

import hospital.dao.DoctorDAO;
import hospital.dao.EnfermeroDAO;
import java.awt.*;
import javax.swing.*;

public class DialogoPersonal extends JDialog {

    public DialogoPersonal(JFrame owner, DoctorDAO doctorDAO, EnfermeroDAO enfermeroDAO) {
        super(owner, "Gestión de Personal", true);
        setSize(300, 180);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(fondo);

        JButton btnDoctores = new JButton("Doctores");
        JButton btnEnfermeros = new JButton("Enfermeros");
        JButton btnCerrar = new JButton("Cerrar");

        btnDoctores.addActionListener(e ->
                new VentanaDoctores(owner, doctorDAO).setVisible(true)
        );

        btnEnfermeros.addActionListener(e ->
                new VentanaEnfermeros(owner, enfermeroDAO).setVisible(true)
        );

        btnCerrar.addActionListener(e -> dispose());

        panel.add(btnDoctores);
        panel.add(btnEnfermeros);
        panel.add(btnCerrar);

        add(panel);
    }
}