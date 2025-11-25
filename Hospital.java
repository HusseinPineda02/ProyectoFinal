import java.util.ArrayList;

public class Hospital {

    private String nombre;
    private String distrito;
    private String direccion;
    private int capacidadTotal; 
    private ArrayList<Paciente> pacientes;
    private ArrayList<Sala> salas;
    private ArrayList<Consultorio> consultorios;
    //constructor
    public Hospital(String nombre, String distrito, String direccion, int capacidadTotal) {
        this.nombre = nombre;
        this.distrito = distrito;
        this.direccion = direccion;
        this.capacidadTotal = capacidadTotal;

        this.pacientes = new ArrayList<>();
        this.salas = new ArrayList<>();
        this.consultorios = new ArrayList<>();
    }

    //getters
    public String getNombre() { return nombre; }
    public String getDistrito() { return distrito; }
    public String getDireccion() { return direccion; }
    public int getCapacidadTotal() { return capacidadTotal; }
    //setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDistrito(String distrito) { this.distrito = distrito; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setCapacidadTotal(int capacidadTotal) {
        if (capacidadTotal >= 0) this.capacidadTotal = capacidadTotal;
        else System.out.println("La capacidad del hospital no puede ser negativa");
    }

    //Metodos_Gestión de pacientes
    public void agregarPaciente(Paciente paciente) {
        if (paciente == null) {
            System.out.println("No se pueden agreagar pacientes 0 ");
            return;
        }
        for (Paciente p : pacientes) {
            if (p.getDni() == paciente.getDni()) {
                System.out.println("ya existe un paciente con DNI " + paciente.getDni());
                return;
            }
        }
        pacientes.add(paciente);
        System.out.println("Paciente " + paciente.getNombre() + " se ha correctamente");
    }

    public void eliminarPaciente(int dni) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getDni() == dni) {
                Paciente p = pacientes.remove(i);
                if (p.getConsultorioActual() != null) p.liberarDeConsultorio();
                System.out.println("Paciente " + p.getNombre() + " eliminado.");
                return;
            }
        }
        System.out.println("No se encontró al paciente con DNI " + dni);
    }

    public Paciente buscarPacientePorDni(int dni) {
        for (Paciente p : pacientes) {
            if (p.getDni() == dni) return p;
        }
        System.out.println("Paciente con DNI " + dni + " no encontrado.");
        return null;
    }

    public void mostrarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        System.out.println("Lista de Pacientes");
        for (Paciente p : pacientes) System.out.println(p.mostrarResumen());
    }

    //metodo_Gestión de consultorios
    public void agregarConsultorio(Consultorio consultorio) {
        if (consultorio == null) {
            System.out.println("No se puede agregar un consultorio nulo.");
            return;
        }
        consultorios.add(consultorio);
        System.out.println("Consultorio " + consultorio.getCodigoConsultorio() + " agregado.");
    }

    public void mostrarConsultorios() {
        if (consultorios.isEmpty()) {
            System.out.println("No hay consultorios registrados.");
            return;
        }
        System.out.println("Lista de Consultorios");
        for (Consultorio c : consultorios) System.out.println(c);
    }

    public void asignarPacienteAConsultorio(int dniPaciente, String codigoConsultorio) {
        Paciente paciente = buscarPacientePorDni(dniPaciente);
        if (paciente == null) {
            System.out.println("No se puede asignar: paciente no encontrado.");
            return;
        }

        Consultorio consultorioDestino = null;
        for (Consultorio c : consultorios) {
            if (c.getCodigoConsultorio().equals(codigoConsultorio)) {
                consultorioDestino = c;
                break;
            }
        }

        if (consultorioDestino == null) {
            System.out.println("Error: consultorio " + codigoConsultorio + " no encontrado.");
            return;
        }

        if (!consultorioDestino.estaDisponible()) {
            System.out.println("Error: el consultorio " + codigoConsultorio + " está ocupado.");
            return;
        }

        if (consultorioDestino.getDoctorAsignado() == null) {
            System.out.println("Error: el consultorio no tiene doctor asignado.");
            return;
        }

        paciente.asignarAConsultorio(consultorioDestino);
        System.out.println("Paciente " + paciente.getNombre() + " asignado al consultorio " + codigoConsultorio + ".");
    }

    //Metodos_Gestion de salas
    public void agregarSala(Sala sala) {
        if (sala == null) {
            System.out.println("no se puede agregar una sala nula.");
            return;
        }
        salas.add(sala);
        System.out.println("Sala agregada.");
    }

    public void mostrarSalas() {
        if (salas.isEmpty()) {
            System.out.println("No hay salas registradas.");
            return;
        }
        System.out.println("Lista de Salas");
        for (Sala s : salas) System.out.println("Sala: aforo=" + s.getAforo() + ", camillas=" + s.getCamillas() + ", tipo=" + s.getTipoSala());
    }

    //Metodos_reportes del hospital
    public void mostrarInformacion() {
        System.out.println("Hospital " + nombre + "");
        System.out.println("Ubicación: " + distrito + " - " + direccion);
        System.out.println("Capacidad total: " + capacidadTotal);
        System.out.println("Pacientes registrados: " + pacientes.size());
        System.out.println("Consultorios: " + consultorios.size());
        System.out.println("Salas: " + salas.size());
    }

    //Metodos de gestión genéricos "stos métodos no están completamente implementados porque dependen de otras clases
    //muestran mensajes indicativos y metodos vacios ()temporal
    public void nuevoTrabajador() { System.out.println("Funcionalidad no implementada."); }
    public void eliminarTrabajador() { System.out.println("Funcionalidad no implementada."); }
    public void consultaTrabajadores() { System.out.println("Lista de trabajadores no disponible."); }
    public void consultaInventario() { System.out.println("Inventario no implementado."); }
    public void consultaMedicamentos() { System.out.println("Gestión de medicamentos no implementada."); }
    public void consultasAmbientes() {
        System.out.println("Ambientes del Hospital");
        mostrarSalas();
        System.out.println();
        mostrarConsultorios();
    }
}
