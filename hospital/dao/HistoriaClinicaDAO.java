package hospital.dao;

import hospital.modelo.HistoriaClinica;
import hospital.modelo.Paciente;
import java.sql.*;
import javax.swing.JOptionPane;

public class HistoriaClinicaDAO {

    public HistoriaClinica crear(Paciente p) throws SQLException {

        String sql = "INSERT INTO HistoriaClinica(dniPaciente) VALUES (?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getDni());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idHistoria = rs.getInt(1);
                    return new HistoriaClinica(idHistoria, p);
                }
            }
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Error al insertar: " + e.getMessage(),
                    "Error SQL",
                    JOptionPane.ERROR_MESSAGE);
                throw e;
            }
        return null;
    }

    public HistoriaClinica buscarPorPaciente(String dniPaciente) throws SQLException {

        String sql =
            "SELECT idHistoria FROM HistoriaClinica WHERE dniPaciente=?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dniPaciente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idHistoria = rs.getInt("idHistoria");
                    Paciente p = new PacienteDAO().buscarPorDni(dniPaciente);
                    return new HistoriaClinica(idHistoria, p);
                }
            }
        }
        return null;
    }
}