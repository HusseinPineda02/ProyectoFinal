package hospital.dao;

import hospital.modelo.Cita;
import hospital.modelo.Doctor;
import hospital.modelo.Paciente;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    public void insertar(Cita c) throws SQLException {

        String sql = "INSERT INTO Cita(dniPaciente, idDoctor, fecha, hora, motivo, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getPaciente().getDni());
            ps.setInt(2, c.getDoctor().getIdTrabajador());
            ps.setDate(3, Date.valueOf(c.getFecha()));
            ps.setTime(4, Time.valueOf(c.getHora()));
            ps.setString(5, c.getMotivo());

            ps.setString(6, c.getEstado().name());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setIdCita(rs.getInt(1));
            }
        }
    }

    public Cita buscar(int idCita) throws SQLException {

        String sql = "SELECT dniPaciente, idDoctor, fecha, hora, motivo, estado " +
                "FROM Cita WHERE idCita = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCita);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    String dni = rs.getString("dniPaciente");
                    int idDoctor = rs.getInt("idDoctor");
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    LocalTime hora = rs.getTime("hora").toLocalTime();
                    String motivo = rs.getString("motivo");
                    String estadoStr = rs.getString("estado");

                    PacienteDAO pdao = new PacienteDAO();
                    DoctorDAO ddao = new DoctorDAO();

                    Paciente p = pdao.buscarPorDni(dni);
                    Doctor d = ddao.buscar(idDoctor);
            
                    Cita.Estado estadoEnum = Cita.Estado.valueOf(estadoStr);

                    return new Cita(idCita, p, d, fecha, hora, motivo, estadoEnum);
                }
            }
        }
        return null;
    }

    public List<Cita> listarTodos() throws SQLException {
        List<Cita> lista = new ArrayList<>();

        String sql = "SELECT idCita, dniPaciente, idDoctor, fecha, hora, motivo, estado FROM Cita";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            PacienteDAO pdao = new PacienteDAO();
            DoctorDAO ddao = new DoctorDAO();

            while (rs.next()) {

                int id = rs.getInt("idCita");
                String dni = rs.getString("dniPaciente");
                int idDoctor = rs.getInt("idDoctor");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                LocalTime hora = rs.getTime("hora").toLocalTime();
                String motivo = rs.getString("motivo");
                String estadoStr = rs.getString("estado");

                Paciente p = pdao.buscarPorDni(dni);
                Doctor d = ddao.buscar(idDoctor);

                Cita.Estado estadoEnum = Cita.Estado.valueOf(estadoStr);

                lista.add(new Cita(id, p, d, fecha, hora, motivo, estadoEnum));
            }
        }
        return lista;
    }

    public void actualizar(Cita c) throws SQLException {

        String sql =
                "UPDATE Cita SET dniPaciente=?, idDoctor=?, fecha=?, hora=?, motivo=?, estado=? WHERE idCita=?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getPaciente().getDni());
            ps.setInt(2, c.getDoctor().getIdTrabajador());
            ps.setDate(3, Date.valueOf(c.getFecha()));
            ps.setTime(4, Time.valueOf(c.getHora()));
            ps.setString(5, c.getMotivo());
            ps.setString(6, c.getEstado().name());
            ps.setInt(7, c.getIdCita());

            ps.executeUpdate();
        }
    }

    public void eliminar(int idCita) throws SQLException {

        String sql = "DELETE FROM Cita WHERE idCita = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCita);
            ps.executeUpdate();
        }
    }
}