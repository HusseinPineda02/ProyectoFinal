package hospital.dao;

import hospital.modelo.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
            //ps.setTime(5, Time.valueOf(c.getHora().withSecond(0).withNano(0)));

           LocalTime t = c.getHora().withSecond(0).withNano(0);
            ps.setTime(5, java.sql.Time.valueOf(t));

            ps.setString(6, c.getDescripcion());

            ps.setString(7, c.getEstado().name());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setIdCirugia(rs.getInt(1));
            }
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Error al insertar: " + e.getMessage(),
                    "Error SQL",
                    JOptionPane.ERROR_MESSAGE);
                throw e;
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

            LocalTime t = c.getHora().withSecond(0).withNano(0);
            ps.setTime(5, Time.valueOf(t)); 
           // ps.setTime(5, Time.valueOf(c.getHora().withSecond(0).withNano(0)));
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
    public boolean existeCirugiaEnHorario(int idHabitacion, LocalDate fecha, LocalTime hora) throws SQLException {

        String sql = """
            SELECT COUNT(*)
            FROM Cirugia
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

    public void marcarRealizadaYRegistrarHistoria(int idCirugia) throws SQLException {

        Cirugia c = buscar(idCirugia);
        if (c == null) throw new SQLException("Cirugía no encontrada con ID: " + idCirugia);

        c.setEstado(Cirugia.Estado.REALIZADA);
        actualizar(c);

        Paciente p = c.getPaciente();
        if (p == null) throw new SQLException("La cirugía no tiene paciente asociado.");

        HistoriaClinicaDAO hdao = new HistoriaClinicaDAO();
        HistoriaClinica historia = hdao.buscarPorPaciente(p.getDni());

        if (historia == null) {
            historia = hdao.crear(p);
            if (historia == null) throw new SQLException("No se pudo crear historia clínica.");
        }

        p.setHistoriaClinica(historia);

        Doctor d = c.getCirujano();
        if (d == null) throw new SQLException("La cirugía no tiene doctor asociado.");

        RegistroClinico r = new RegistroClinico(
                0,
                c.getFecha(),
                d,
                RegistroClinico.Tipo.CIRUGIA,
                (c.getDescripcion() != null && !c.getDescripcion().isBlank())
                        ? c.getDescripcion()
                        : "Cirugía"
        );

        RegistroClinicoDAO rdao = new RegistroClinicoDAO();
        rdao.insertar(r, historia.getIdHistoria());

        historia.agregarRegistro(r);
    }
}