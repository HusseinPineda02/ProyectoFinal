package hospital.dao;

import hospital.modelo.Paciente;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void insertar(Paciente p) throws SQLException {

        String sql = "INSERT INTO Paciente(dni, nombre, apellidoP, apellidoM, fechaNacimiento, telefono, direccion) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getDni());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellidoP());
            ps.setString(4, p.getApellidoM());

            if (p.getFechaNacimiento() != null) {
                ps.setDate(5, Date.valueOf(p.getFechaNacimiento()));
            } else {
                ps.setNull(5, Types.DATE);
            }

            ps.setString(6, p.getTelefono());
            ps.setString(7, p.getDireccion());

            ps.executeUpdate();
        }
    }

    public Paciente buscarPorDni(String dni) throws SQLException {

        String sql =
            "SELECT nombre, apellidoP, apellidoM, fechaNacimiento, telefono, direccion " +
            "FROM Paciente WHERE dni = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apP = rs.getString("apellidoP");
                    String apM = rs.getString("apellidoM");
                    Date fecha = rs.getDate("fechaNacimiento");
                    String tel = rs.getString("telefono");
                    String dir = rs.getString("direccion");

                    LocalDate fn = (fecha != null) ? fecha.toLocalDate() : null;

                    return new Paciente(dni, nombre, apP, apM, fn, tel, dir);
                }
            }
        }
        return null;
    }

    public List<Paciente> listarTodos() throws SQLException {
        List<Paciente> lista = new ArrayList<>();

        String sql =
            "SELECT dni, nombre, apellidoP, apellidoM, fechaNacimiento, telefono, direccion " +
            "FROM Paciente";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apP = rs.getString("apellidoP");
                String apM = rs.getString("apellidoM");
                Date fecha = rs.getDate("fechaNacimiento");
                String tel = rs.getString("telefono");
                String dir = rs.getString("direccion");

                LocalDate fn = (fecha != null) ? fecha.toLocalDate() : null;

                lista.add(new Paciente(dni, nombre, apP, apM, fn, tel, dir));
            }
        }

        return lista;
    }

    public void actualizar(Paciente p) throws SQLException {

        String sql = "UPDATE Paciente SET nombre = ?, apellidoP = ?, apellidoM = ?, " +
                     "fechaNacimiento = ?, telefono = ?, direccion = ? WHERE dni = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellidoP());
            ps.setString(3, p.getApellidoM());

            if (p.getFechaNacimiento() != null) {
                ps.setDate(4, Date.valueOf(p.getFechaNacimiento()));
            } else {
                ps.setNull(4, Types.DATE);
            }

            ps.setString(5, p.getTelefono());
            ps.setString(6, p.getDireccion());
            ps.setString(7, p.getDni());

            ps.executeUpdate();
        }
    }

    public void eliminar(String dni) throws SQLException {
        String sql = "DELETE FROM Paciente WHERE dni = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            ps.executeUpdate();
        }
    }
}