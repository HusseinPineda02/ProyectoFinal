package hospital.dao;

import hospital.modelo.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RegistroClinicoDAO {

    public void insertar(RegistroClinico r, int idHistoria) throws SQLException {

        String sql = "INSERT INTO RegistroClinico(idHistoria, fecha, idDoctor, tipo, descripcion) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idHistoria);
            ps.setDate(2, Date.valueOf(r.getFecha()));
            ps.setInt(3, r.getDoctor().getIdTrabajador());

            ps.setString(4, r.getTipo().name());

            ps.setString(5, r.getDescripcion());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) r.setIdRegistro(rs.getInt(1));
            }
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Error al insertar: " + e.getMessage(),
                    "Error SQL",
                    JOptionPane.ERROR_MESSAGE);
                throw e;
            }
    }

    public List<RegistroClinico> listarPorHistoria(int idHistoria) throws SQLException {
        List<RegistroClinico> lista = new ArrayList<>();

        String sql =
            "SELECT idRegistro, fecha, idDoctor, tipo, descripcion " +
            "FROM RegistroClinico WHERE idHistoria=?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHistoria);

            try (ResultSet rs = ps.executeQuery()) {

                DoctorDAO ddao = new DoctorDAO();

                while (rs.next()) {
                    int id = rs.getInt("idRegistro");
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    int idDoc = rs.getInt("idDoctor");
                    String tipoStr = rs.getString("tipo");
                    String desc = rs.getString("descripcion");

                    Doctor d = ddao.buscar(idDoc);

                    RegistroClinico.Tipo tipoEnum =
                        RegistroClinico.Tipo.valueOf(tipoStr);

                    RegistroClinico r =
                        new RegistroClinico(id, fecha, d, tipoEnum, desc);

                    lista.add(r);
                }
            }
        }

        return lista;
    }

    public List<RegistroClinico> listarPorPaciente(String dniPaciente) throws SQLException {

        List<RegistroClinico> lista = new ArrayList<>();

        String sql = """
            SELECT rc.idRegistro, rc.fecha, rc.idDoctor, rc.tipo, rc.descripcion
            FROM RegistroClinico rc
            JOIN HistoriaClinica hc ON hc.idHistoria = rc.idHistoria
            WHERE hc.dniPaciente = ?
            ORDER BY rc.fecha DESC, rc.idRegistro DESC
        """;

        try (Connection con = ConexionBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dniPaciente);

            try (ResultSet rs = ps.executeQuery()) {
                DoctorDAO ddao = new DoctorDAO();

                while (rs.next()) {
                    int idReg = rs.getInt("idRegistro");
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    int idDoctor = rs.getInt("idDoctor");
                    String tipo = rs.getString("tipo");
                    String desc = rs.getString("descripcion");

                    Doctor d = ddao.buscar(idDoctor);

                    lista.add(new RegistroClinico(
                            idReg,
                            fecha,
                            d,
                            RegistroClinico.Tipo.valueOf(tipo),
                            desc
                    ));
                }
            }
        }

        return lista;
    }
}