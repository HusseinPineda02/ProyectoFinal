package hospital.dao;

import hospital.modelo.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CitaDAO {

    public void insertar(Cita c) throws SQLException {

        String sql = """
            INSERT INTO Cita(dniPaciente, idDoctor, idHabitacion, fecha, hora, motivo, estado)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getPaciente().getDni());
            ps.setInt(2, c.getDoctor().getIdTrabajador());
            ps.setInt(3, c.getHabitacion().getIdHabitacion());
            ps.setDate(4, Date.valueOf(c.getFecha()));
            LocalTime t = c.getHora().withSecond(0).withNano(0);
            ps.setTime(5, java.sql.Time.valueOf(t));

            ps.setString(6, c.getMotivo());
            ps.setString(7, c.getEstado().name());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setIdCita(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al insertar cita: " + e.getMessage(),
                    "Error SQL",
                    JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    public Cita buscar(int idCita) throws SQLException {

        String sql = """
            SELECT dniPaciente, idDoctor, idHabitacion, fecha, hora, motivo, estado
            FROM Cita WHERE idCita = ?
        """;

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCita);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    String dni = rs.getString("dniPaciente");
                    int idDoctor = rs.getInt("idDoctor");
                    int idHabitacion = rs.getInt("idHabitacion");
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    LocalTime hora = rs.getTime("hora").toLocalTime();
                    String motivo = rs.getString("motivo");
                    String estadoStr = rs.getString("estado");

                    Paciente p = new PacienteDAO().buscarPorDni(dni);
                    Doctor d = new DoctorDAO().buscar(idDoctor);
                    Habitacion h = new HabitacionDAO().buscar(idHabitacion);

                    Cita.Estado estado = Cita.Estado.valueOf(estadoStr);

                    return new Cita(idCita, p, d, h, fecha, hora, motivo, estado);
                }
            }
        }
        return null;
    }

    public List<Cita> listarTodos() throws SQLException {

        List<Cita> lista = new ArrayList<>();

        String sql = """
            SELECT idCita, dniPaciente, idDoctor, idHabitacion, fecha, hora, motivo, estado
            FROM Cita
        """;

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            PacienteDAO pdao = new PacienteDAO();
            DoctorDAO ddao = new DoctorDAO();
            HabitacionDAO hdao = new HabitacionDAO();

            while (rs.next()) {

                int id = rs.getInt("idCita");
                String dni = rs.getString("dniPaciente");
                int idDoctor = rs.getInt("idDoctor");
                int idHabitacion = rs.getInt("idHabitacion");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                LocalTime hora = rs.getTime("hora").toLocalTime();
                String motivo = rs.getString("motivo");
                String estadoStr = rs.getString("estado");

                Paciente p = pdao.buscarPorDni(dni);
                Doctor d = ddao.buscar(idDoctor);
                Habitacion h = hdao.buscar(idHabitacion);

                Cita.Estado estado = Cita.Estado.valueOf(estadoStr);

                lista.add(new Cita(id, p, d, h, fecha, hora, motivo, estado));
            }
        }
        return lista;
    }

    public void actualizar(Cita c) throws SQLException {

        String sql = """
            UPDATE Cita
            SET dniPaciente=?, idDoctor=?, idHabitacion=?, fecha=?, hora=?, motivo=?, estado=?
            WHERE idCita=?
        """;

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getPaciente().getDni());
            ps.setInt(2, c.getDoctor().getIdTrabajador());
            ps.setInt(3, c.getHabitacion().getIdHabitacion());
            ps.setDate(4, Date.valueOf(c.getFecha()));
            ps.setTime(5, Time.valueOf(c.getHora().withSecond(0).withNano(0)));
            ps.setString(6, c.getMotivo());
            ps.setString(7, c.getEstado().name());
            ps.setInt(8, c.getIdCita());

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

 /* public boolean existeCitaEnHorario(int idHabitacion, LocalDate fecha, LocalTime hora)
            throws SQLException {

        String sql = """
            SELECT COUNT(*)
            FROM Cita
            WHERE idHabitacion = ?
              AND fecha = ?
              AND hora = ?
        """;

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHabitacion);
            ps.setDate(2, Date.valueOf(fecha));
            ps.setTime(3, Time.valueOf(hora));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }*/

    public boolean existeCitaEnHorario(int idHabitacion, LocalDate fecha, LocalTime hora) throws SQLException {

        String sql = """
            SELECT COUNT(*)
            FROM Cita
            WHERE idHabitacion = ?
            AND fecha = ?
            AND hora = CONVERT(time(0), ?)
        """;

        try (Connection con = ConexionBD.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHabitacion);
            ps.setDate(2, Date.valueOf(fecha));

            LocalTime t = hora.withSecond(0).withNano(0);
            String hhmmss = t.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            ps.setString(3, hhmmss);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public void marcarAtendidaYRegistrarHistoria(int idCita) throws SQLException {

        Cita c = buscar(idCita);
        if (c == null) {
            throw new SQLException("Cita no encontrada con ID: " + idCita);
        }

        c.setEstado(Cita.Estado.ATENDIDA);
        actualizar(c);

        Paciente p = c.getPaciente();
        if (p == null) throw new SQLException("La cita no tiene paciente asociado.");

        HistoriaClinicaDAO hdao = new HistoriaClinicaDAO();
        HistoriaClinica historia = hdao.buscarPorPaciente(p.getDni());

        if (historia == null) {
            historia = hdao.crear(p);
            if (historia == null) throw new SQLException("No se pudo crear historia clínica.");
        }

        p.setHistoriaClinica(historia);

        Doctor d = c.getDoctor();
        if (d == null) throw new SQLException("La cita no tiene doctor asociado.");

        RegistroClinico r = new RegistroClinico(
                0,
                c.getFecha(),
                d,
                RegistroClinico.Tipo.CONSULTA,
                (c.getMotivo() != null && !c.getMotivo().isBlank())
                        ? c.getMotivo()
                        : "Consulta"
        );

        RegistroClinicoDAO rdao = new RegistroClinicoDAO();
        rdao.insertar(r, historia.getIdHistoria());

        historia.agregarRegistro(r);
    }
}