package hospital.dao;

import hospital.modelo.Medicamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MedicamentoDAO implements ICrudDAO<Medicamento> {

    @Override
    public void insertar(Medicamento m) throws SQLException {

        String sql = "INSERT INTO Medicamento(nombre, tipo, descripcion, stock, precioUnitario) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, m.getNombre());
            ps.setString(2, m.getTipo());
            ps.setString(3, m.getDescripcion());
            ps.setInt(4, m.getStock());
            ps.setDouble(5, m.getPrecioUnitario());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    m.setIdMedicamento(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Error al insertar: " + e.getMessage(),
                    "Error SQL",
                    JOptionPane.ERROR_MESSAGE);
                throw e;
            }
    }

    @Override
    public Medicamento buscar(int id) throws SQLException {

        String sql =
            "SELECT nombre, tipo, descripcion, stock, precioUnitario " +
            "FROM Medicamento WHERE idMedicamento = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String tipo = rs.getString("tipo");
                    String desc = rs.getString("descripcion");
                    int stock = rs.getInt("stock");
                    double precio = rs.getDouble("precioUnitario");

                    return new Medicamento(id, nombre, tipo, desc, stock, precio);
                }
            }
        }
        return null;
    }

    @Override
    public List<Medicamento> listarTodos() throws SQLException {
        List<Medicamento> lista = new ArrayList<>();

        String sql =
            "SELECT idMedicamento, nombre, tipo, descripcion, stock, precioUnitario FROM Medicamento";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idMedicamento");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                String desc = rs.getString("descripcion");
                int stock = rs.getInt("stock");
                double precio = rs.getDouble("precioUnitario");

                lista.add(new Medicamento(id, nombre, tipo, desc, stock, precio));
            }
        }

        return lista;
    }

    @Override
    public void actualizar(Medicamento m) throws SQLException {
        String sql =
            "UPDATE Medicamento SET nombre=?, tipo=?, descripcion=?, stock=?, precioUnitario=? " +
            "WHERE idMedicamento=?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());
            ps.setString(2, m.getTipo());
            ps.setString(3, m.getDescripcion());
            ps.setInt(4, m.getStock());
            ps.setDouble(5, m.getPrecioUnitario());
            ps.setInt(6, m.getIdMedicamento());

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Medicamento WHERE idMedicamento=?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}