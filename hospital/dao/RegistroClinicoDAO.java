package hospital.dao;

import hospital.modelo.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}