package hospital.ui;

import hospital.modelo.HistoriaClinica;
import hospital.modelo.Paciente;
import hospital.modelo.RegistroClinico;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaHistoriaClinica extends JDialog {

    private HistoriaClinica historia;
    private JTable tabla;
    private DefaultTableModel modelo;

    public VentanaHistoriaClinica(Dialog owner, HistoriaClinica historia) {
        super(owner, "Historia Clínica", true);
        this.historia = historia;

        setSize(700, 400);
        setLocationRelativeTo(owner);

        Color fondo = new Color(235, 235, 235);
        getContentPane().setBackground(fondo);

        initComponentes(fondo);
        cargarRegistros();
    }

    private void initComponentes(Color fondo) {
        setLayout(new BorderLayout(10, 10));

        JPanel panelInfo = new JPanel(new GridLayout(2, 2, 10, 5));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Paciente"));
        panelInfo.setBackground(fondo);

        Paciente p = historia.getPaciente();

        panelInfo.add(new JLabel("DNI:"));
        panelInfo.add(new JLabel(p.getDni()));

        panelInfo.add(new JLabel("Nombre:"));
        panelInfo.add(new JLabel(p.getNombreCompleto()));

        modelo = new DefaultTableModel(
                new Object[]{"Fecha", "Tipo", "Doctor", "Descripción"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(fondo);
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        panelBoton.add(btnCerrar);

        add(panelInfo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
    }

    private void cargarRegistros() {
        modelo.setRowCount(0);

        List<RegistroClinico> registros = historia.getRegistros();

        if (registros.isEmpty()) {
            return;
        }

        for (RegistroClinico r : registros) {
            modelo.addRow(new Object[]{
                    r.getFecha(),
                    r.getTipo(),
                    r.getDoctor().getNombreCompleto(),
                    r.getDescripcion()
            });
        }
    }
}