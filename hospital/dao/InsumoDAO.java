package hospital.dao;

import hospital.modelo.Insumo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InsumoDAO implements ICrudDAO<Insumo> {

    @Override
    public void insertar(Insumo i) throws SQLException {

        String sql = "INSERT INTO Insumo(nombre, categoria, descripcion, stock, precioUnitario) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, i.getNombre());
            ps.setString(2, i.getCategoria());
            ps.setString(3, i.getDescripcion());
            ps.setInt(4, i.getStock());
            ps.setDouble(5, i.getPrecioUnitario());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    i.setIdInsumo(rs.getInt(1));
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
    public Insumo buscar(int id) throws SQLException {

        String sql =
            "SELECT nombre, categoria, descripcion, stock, precioUnitario " +
            "FROM Insumo WHERE idInsumo = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String cat = rs.getString("categoria");
                    String desc = rs.getString("descripcion");
                    int stock = rs.getInt("stock");
                    double precio = rs.getDouble("precioUnitario");

                    return new Insumo(id, nombre, cat, desc, stock, precio);
                }
            }
        }
        return null;
    }

    @Override
    public List<Insumo> listarTodos() throws SQLException {
        List<Insumo> lista = new ArrayList<>();

        String sql =
            "SELECT idInsumo, nombre, categoria, descripcion, stock, precioUnitario FROM Insumo";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idInsumo");
                String nombre = rs.getString("nombre");
                String cat = rs.getString("categoria");
                String desc = rs.getString("descripcion");
                int stock = rs.getInt("stock");
                double precio = rs.getDouble("precioUnitario");

                lista.add(new Insumo(id, nombre, cat, desc, stock, precio));
            }
        }

        return lista;
    }

    @Override
    public void actualizar(Insumo i) throws SQLException {
        String sql =
            "UPDATE Insumo SET nombre=?, categoria=?, descripcion=?, stock=?, precioUnitario=? " +
            "WHERE idInsumo=?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, i.getNombre());
            ps.setString(2, i.getCategoria());
            ps.setString(3, i.getDescripcion());
            ps.setInt(4, i.getStock());
            ps.setDouble(5, i.getPrecioUnitario());
            ps.setInt(6, i.getIdInsumo());

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Insumo WHERE idInsumo=?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}