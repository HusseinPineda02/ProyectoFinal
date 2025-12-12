package hospital.ui;

import hospital.dao.InsumoDAO;
import hospital.dao.MedicamentoDAO;
import java.awt.*;
import javax.swing.*;

public class DialogoInventario extends JDialog {

    public DialogoInventario(JFrame owner, MedicamentoDAO medicamentoDAO, InsumoDAO insumoDAO) {
        super(owner, "Inventario", true);
        setSize(320, 180);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(fondo);

        JButton btnMedicamentos = new JButton("Medicamentos");
        JButton btnInsumos = new JButton("Insumos");
        JButton btnCerrar = new JButton("Cerrar");

        btnMedicamentos.addActionListener(e ->
                new VentanaMedicamentos(owner, medicamentoDAO).setVisible(true)
        );

        btnInsumos.addActionListener(e ->
                new VentanaInsumos(owner, insumoDAO).setVisible(true)
        );

        btnCerrar.addActionListener(e -> dispose());

        panel.add(btnMedicamentos);
        panel.add(btnInsumos);
        panel.add(btnCerrar);

        add(panel);
    }
}