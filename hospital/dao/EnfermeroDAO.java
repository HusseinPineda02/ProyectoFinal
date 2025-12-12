package hospital.dao;

import hospital.modelo.Enfermero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EnfermeroDAO implements ICrudDAO<Enfermero> {

    @Override
    public void insertar(Enfermero e) throws SQLException {

        String sql = "INSERT INTO Enfermero(dni, nombre, apellidoP, apellidoM, salario, turno) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, e.getDni());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellidoP());
            ps.setString(4, e.getApellidoM());
            ps.setDouble(5, e.getSalario());
            ps.setString(6, e.getTurno());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    e.setIdTrabajador(rs.getInt(1));
                }
            }
        } catch (SQLException f) {
                JOptionPane.showMessageDialog(null,
                    "Error al insertar: " + f.getMessage(),
                    "Error SQL",
                    JOptionPane.ERROR_MESSAGE);
                throw f;
            }
    }

    @Override
    public Enfermero buscar(int idEnfermero) throws SQLException {

        String sql =
            "SELECT dni, nombre, apellidoP, apellidoM, salario, turno " +
            "FROM Enfermero WHERE idEnfermero = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEnfermero);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String dni = rs.getString("dni");
                    String nombre = rs.getString("nombre");
                    String apP = rs.getString("apellidoP");
                    String apM = rs.getString("apellidoM");
                    double salario = rs.getDouble("salario");
                    String turno = rs.getString("turno");

                    return new Enfermero(idEnfermero, dni, nombre, apP, apM, salario, turno);
                }
            }
        }
        return null;
    }

    @Override
    public List<Enfermero> listarTodos() throws SQLException {
        List<Enfermero> lista = new ArrayList<>();

        String sql =
            "SELECT idEnfermero, dni, nombre, apellidoP, apellidoM, salario, turno FROM Enfermero";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idEnfermero");
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apP = rs.getString("apellidoP");
                String apM = rs.getString("apellidoM");
                double salario = rs.getDouble("salario");
                String turno = rs.getString("turno");

                lista.add(new Enfermero(id, dni, nombre, apP, apM, salario, turno));
            }
        }
        return lista;
    }

    @Override
    public void actualizar(Enfermero e) throws SQLException {
        String sql =
            "UPDATE Enfermero SET nombre = ?, apellidoP = ?, apellidoM = ?, salario = ?, turno = ? " +
            "WHERE idEnfermero = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellidoP());
            ps.setString(3, e.getApellidoM());
            ps.setDouble(4, e.getSalario());
            ps.setString(5, e.getTurno());
            ps.setInt(6, e.getIdTrabajador());

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int idEnfermero) throws SQLException {
        String sql = "DELETE FROM Enfermero WHERE idEnfermero = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEnfermero);
            ps.executeUpdate();
        }
    }
}