package hospital.dao;

import hospital.modelo.Administrativo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AdministrativoDAO implements ICrudDAO<Administrativo> {

    @Override
    public void insertar(Administrativo a) throws SQLException {

        String sql = "INSERT INTO Administrativo(dni, nombre, apellidoP, apellidoM, salario, area) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, a.getDni());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellidoP());
            ps.setString(4, a.getApellidoM());
            ps.setDouble(5, a.getSalario());
            ps.setString(6, a.getArea());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    a.setIdTrabajador(rs.getInt(1));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Error al insertar: " + e.getMessage(),
                    "Error SQL",
                    JOptionPane.ERROR_MESSAGE);
                throw e;
            }
        }
    }

    @Override
    public Administrativo buscar(int id) throws SQLException {

        String sql =
            "SELECT dni, nombre, apellidoP, apellidoM, salario, area " +
            "FROM Administrativo WHERE idAdministrativo = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String dni = rs.getString("dni");
                    String nombre = rs.getString("nombre");
                    String apP = rs.getString("apellidoP");
                    String apM = rs.getString("apellidoM");
                    double salario = rs.getDouble("salario");
                    String area = rs.getString("area");

                    return new Administrativo(id, dni, nombre, apP, apM, salario, area);
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

    @Override
    public List<Administrativo> listarTodos() throws SQLException {
        List<Administrativo> lista = new ArrayList<>();

        String sql =
            "SELECT idAdministrativo, dni, nombre, apellidoP, apellidoM, salario, area " +
            "FROM Administrativo";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idAdministrativo");
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apP = rs.getString("apellidoP");
                String apM = rs.getString("apellidoM");
                double salario = rs.getDouble("salario");
                String area = rs.getString("area");

                lista.add(new Administrativo(id, dni, nombre, apP, apM, salario, area));
            }
        }

        return lista;
    }

    @Override
    public void actualizar(Administrativo a) throws SQLException {
        String sql =
            "UPDATE Administrativo SET nombre = ?, apellidoP = ?, apellidoM = ?, salario = ?, area = ? " +
            "WHERE idAdministrativo = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellidoP());
            ps.setString(3, a.getApellidoM());
            ps.setDouble(4, a.getSalario());
            ps.setString(5, a.getArea());
            ps.setInt(6, a.getIdTrabajador());

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Administrativo WHERE idAdministrativo = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

}