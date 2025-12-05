package hospital.dao;

import hospital.modelo.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CirugiaDAO {

    public void insertar(Cirugia c) throws SQLException {

        String sql = "INSERT INTO Cirugia(dniPaciente, idDoctor, idHabitacion, fecha, hora, descripcion, estado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getPaciente().getDni());
            ps.setInt(2, c.getCirujano().getIdTrabajador()); 
            ps.setInt(3, c.getQuirofano().getIdHabitacion());
            ps.setDate(4, Date.valueOf(c.getFecha()));
            ps.setTime(5, Time.valueOf(c.getHora()));
            ps.setString(6, c.getDescripcion());

            ps.setString(7, c.getEstado().name());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setIdCirugia(rs.getInt(1));
            }
        }
    }

    public Cirugia buscar(int idCirugia) throws SQLException {

        String sql =
            "SELECT dniPaciente, idDoctor, idHabitacion, fecha, hora, descripcion, estado " +
            "FROM Cirugia WHERE idCirugia = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCirugia);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String dni = rs.getString("dniPaciente");
                    int idDoc = rs.getInt("idDoctor");
                    int idHab = rs.getInt("idHabitacion");
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    LocalTime hora = rs.getTime("hora").toLocalTime();
                    String desc = rs.getString("descripcion");
                    String est = rs.getString("estado");

                    Paciente p = new PacienteDAO().buscarPorDni(dni);
                    Doctor d = new DoctorDAO().buscar(idDoc);
                    Habitacion h = new HabitacionDAO().buscar(idHab);

                    Cirugia.Estado estadoEnum = Cirugia.Estado.valueOf(est);

                    return new Cirugia(idCirugia, p, d, h, fecha, hora, desc, estadoEnum);
                }
            }
        }
        return null;
    }

    public List<Cirugia> listarTodos() throws SQLException {
        List<Cirugia> lista = new ArrayList<>();

        String sql =
            "SELECT idCirugia, dniPaciente, idDoctor, idHabitacion, fecha, hora, descripcion, estado FROM Cirugia";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            PacienteDAO pdao = new PacienteDAO();
            DoctorDAO ddao = new DoctorDAO();
            HabitacionDAO hdao = new HabitacionDAO();

            while (rs.next()) {
                int id = rs.getInt("idCirugia");
                String dni = rs.getString("dniPaciente");
                int idDoc = rs.getInt("idDoctor");
                int idHab = rs.getInt("idHabitacion");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                LocalTime hora = rs.getTime("hora").toLocalTime();
                String desc = rs.getString("descripcion");
                String est = rs.getString("estado");

                Paciente p = pdao.buscarPorDni(dni);
                Doctor d = ddao.buscar(idDoc);
                Habitacion h = hdao.buscar(idHab);

                Cirugia.Estado estadoEnum = Cirugia.Estado.valueOf(est);

                lista.add(new Cirugia(id, p, d, h, fecha, hora, desc, estadoEnum));
            }
        }

        return lista;
    }

    public void actualizar(Cirugia c) throws SQLException {

        String sql =
            "UPDATE Cirugia SET dniPaciente=?, idDoctor=?, idHabitacion=?, fecha=?, hora=?, descripcion=?, estado=? " +
            "WHERE idCirugia=?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getPaciente().getDni());
            ps.setInt(2, c.getCirujano().getIdTrabajador());
            ps.setInt(3, c.getQuirofano().getIdHabitacion());
            ps.setDate(4, Date.valueOf(c.getFecha()));
            ps.setTime(5, Time.valueOf(c.getHora()));
            ps.setString(6, c.getDescripcion());
            ps.setString(7, c.getEstado().name());
            ps.setInt(8, c.getIdCirugia());

            ps.executeUpdate();
        }
    }

    public void eliminar(int idCirugia) throws SQLException {

        String sql = "DELETE FROM Cirugia WHERE idCirugia=?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCirugia);
            ps.executeUpdate();
        }
    }
}