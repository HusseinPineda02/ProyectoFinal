package hospital.dao;

import hospital.modelo.Habitacion;
import hospital.modelo.TipoHabitacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO implements ICrudDAO<Habitacion> {

    @Override
    public void insertar(Habitacion h) throws SQLException {

        String sql = "INSERT INTO Habitacion(tipo, piso, numero, disponible) " +
                     "VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, h.getTipo().name());
            ps.setInt(2, h.getPiso());
            ps.setString(3, h.getNumero());
            ps.setBoolean(4, h.isDisponible());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    h.setIdHabitacion(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Habitacion buscar(int id) throws SQLException {

        String sql =
            "SELECT tipo, piso, numero, disponible FROM Habitacion WHERE idHabitacion = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    int piso = rs.getInt("piso");
                    String numero = rs.getString("numero");
                    boolean disponible = rs.getBoolean("disponible");

                    TipoHabitacion th = TipoHabitacion.valueOf(tipo);

                    return new Habitacion(id, th, piso, numero, disponible);
                }
            }
        }
        return null;
    }

    @Override
    public List<Habitacion> listarTodos() throws SQLException {
        List<Habitacion> lista = new ArrayList<>();

        String sql =
            "SELECT idHabitacion, tipo, piso, numero, disponible FROM Habitacion";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idHabitacion");
                String tipo = rs.getString("tipo");
                int piso = rs.getInt("piso");
                String numero = rs.getString("numero");
                boolean disp = rs.getBoolean("disponible");

                TipoHabitacion th = TipoHabitacion.valueOf(tipo);

                lista.add(new Habitacion(id, th, piso, numero, disp));
            }
        }

        return lista;
    }

    @Override
    public void actualizar(Habitacion h) throws SQLException {
        String sql =
            "UPDATE Habitacion SET tipo=?, piso=?, numero=?, disponible=? WHERE idHabitacion=?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, h.getTipo().name());
            ps.setInt(2, h.getPiso());
            ps.setString(3, h.getNumero());
            ps.setBoolean(4, h.isDisponible());
            ps.setInt(5, h.getIdHabitacion());

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Habitacion WHERE idHabitacion=?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}