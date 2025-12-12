package hospital.dao;

import hospital.modelo.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DoctorDAO implements ICrudDAO<Doctor> {

    @Override
    public void insertar(Doctor d) throws SQLException {

        String sql = "INSERT INTO Doctor(dni, nombre, apellidoP, apellidoM, salario, especialidad)" +
            "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, d.getDni());
            ps.setString(2, d.getNombre());
            ps.setString(3, d.getApellidoP());
            ps.setString(4, d.getApellidoM());
            ps.setDouble(5, d.getSalario());
            ps.setString(6, d.getEspecialidad());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    d.setIdTrabajador(rs.getInt(1));
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
    public Doctor buscar(int idDoctor) throws SQLException {

        String sql =
            "SELECT dni, nombre, apellidoP, apellidoM, salario, especialidad " +
            "FROM Doctor WHERE idDoctor = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idDoctor);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String dni = rs.getString("dni");
                    String nombre = rs.getString("nombre");
                    String apP = rs.getString("apellidoP");
                    String apM = rs.getString("apellidoM");
                    double salario = rs.getDouble("salario");
                    String esp = rs.getString("especialidad");

                    return new Doctor(idDoctor, dni, nombre, apP, apM, salario, esp);
                }
            }
        }
        return null;
    }

    @Override
    public List<Doctor> listarTodos() throws SQLException {
        List<Doctor> lista = new ArrayList<>();

        String sql =
            "SELECT idDoctor, dni, nombre, apellidoP, apellidoM, salario, especialidad " +
            "FROM Doctor";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idDoctor");
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apP = rs.getString("apellidoP");
                String apM = rs.getString("apellidoM");
                double salario = rs.getDouble("salario");
                String esp = rs.getString("especialidad");

                lista.add(new Doctor(id, dni, nombre, apP, apM, salario, esp));
            }
        }
        return lista;
    }

    @Override
    public void actualizar(Doctor d) throws SQLException {
        String sql =
            "UPDATE Doctor SET nombre = ?, apellidoP = ?, apellidoM = ?, salario = ?, especialidad = ? " +
            "WHERE idDoctor = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, d.getNombre());
            ps.setString(2, d.getApellidoP());
            ps.setString(3, d.getApellidoM());
            ps.setDouble(4, d.getSalario());
            ps.setString(5, d.getEspecialidad());
            ps.setInt(6, d.getIdTrabajador());

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int idDoctor) throws SQLException {
        String sql = "DELETE FROM Doctor WHERE idDoctor = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idDoctor);
            ps.executeUpdate();
        }
    }
}