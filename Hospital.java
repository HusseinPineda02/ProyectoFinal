import java.util.ArrayList;

public class Hospital {

    private String nombre;
    private String distrito;
    private String direccion;
    private int capacidadTotal;

    // DAO
    private PacienteDAO pacienteDAO;
    private SalaDAO salaDAO;
    private ConsultorioDAO consultorioDAO;
    private TrabajadorDAO trabajadorDAO;
    private MedicamentoDAO medicamentoDAO;
    private InventarioDAO inventarioDAO;

    public Hospital(String nombre, String distrito, String direccion, int capacidadTotal) {
        this.nombre = nombre;
        this.distrito = distrito;
        this.direccion = direccion;
        this.capacidadTotal = capacidadTotal;

        pacienteDAO = new PacienteDAO();
        salaDAO = new SalaDAO();
        consultorioDAO = new ConsultorioDAO();
        trabajadorDAO = new TrabajadorDAO();
        medicamentoDAO = new MedicamentoDAO();
        inventarioDAO = new InventarioDAO();
    }

    // Getters setters
    public String getNombre() { return nombre; }
    public String getDistrito() { return distrito; }
    public String getDireccion() { return direccion; }
    public int getCapacidadTotal() { return capacidadTotal; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDistrito(String distrito) { this.distrito = distrito; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setCapacidadTotal(int capacidadTotal) {
        if (capacidadTotal >= 0) this.capacidadTotal = capacidadTotal;
        else System.out.println("La capacidad del hospital no puede ser negativa.");
    }

    // conexiones hacia los DAO 

    // Pacientes
    public void agregarPaciente(Paciente p) { pacienteDAO.agregar(p); }
    public void eliminarPaciente(int dni) { pacienteDAO.eliminar(dni); }
    public void mostrarPacientes() { pacienteDAO.mostrar(); }
    public Paciente buscarPaciente(int dni) { return pacienteDAO.buscar(dni); }

    // Salas
    public void agregarSala(Sala s) { salaDAO.agregar(s); }
    public void mostrarSalas() { salaDAO.mostrar(); }

    // Consultorios
    public void agregarConsultorio(Consultorio c) { consultorDAO.agregar(c); }
    public void mostrarConsultorios() { consultorDAO.mostrar(); }

    public void asignarPacienteAConsultorio(int dni, String codigo) {
        Paciente p = pacienteDAO.buscar(dni);
        if (p == null) return;

        Consultorio c = consultorDAO.buscar(codigo);
        if (c == null) return;

        if (!c.estaDisponible()) {
            System.out.println("Error: consultorio ocupado.");
            return;
        }

        if (c.getDoctorAsignado() == null) {
            System.out.println("Error: el consultorio no tiene doctor asignado.");
            return;
        }

        p.asignarAConsultorio(c);
        System.out.println("Paciente " + p.getNombre() + " asignado al consultorio " + codigo);
    }

    // Trabajadores
    public void nuevoTrabajador(Trabajador t) { trabajadorDAO.agregar(t); }
    public void eliminarTrabajador(int dni) { trabajadorDAO.eliminar(dni); }
    public void consultaTrabajadores() { trabajadorDAO.mostrar(); }

    // Medicamentos
    public void consultaMedicamentos() { medicamentoDAO.mostrar(); }

    // Inventario
    public void consultaInventario() { inventarioDAO.mostrar(); }

    // Ambientes
    public void consultasAmbientes() {
        System.out.println("Ambientes del Hospital");
        mostrarSalas();
        System.out.println();
        mostrarConsultorios();
    }

    // Info general
    public void mostrarInformacion() {
        System.out.println("Hospital " + nombre);
        System.out.println("Ubicación: " + distrito + " - " + direccion);
        System.out.println("Capacidad total: " + capacidadTotal);
        System.out.println("Pacientes: " + pacienteDAO.count());
        System.out.println("Consultorios: " + consultorDAO.count());
        System.out.println("Salas: " + salaDAO.count());
        System.out.println("Trabajadores: " + trabajadorDAO.count());
    }
}
