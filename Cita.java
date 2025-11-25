import java.util.Date;

public class Cita {
    private int idCita;
    private Date fechaCita;
    private String hora;
    private String especialidad;
    private Paciente paciente;
    private Ambiente ambiente;

    public Cita(int idCita, Date fechaCita, String hora, String especialidad, 
                Paciente paciente, Ambiente ambiente) {
        this.idCita = idCita;
        this.fechaCita = fechaCita;
        this.hora = hora;
        this.especialidad = especialidad;
        this.paciente = paciente;
        this.ambiente = ambiente;
        ambiente.agregarCita(this); 
    }
    public void mostrarDatosCita() {
        System.out.println("---CITA #" + idCita + "---");
        System.out.println("Paciente: " + paciente.getNombreCompleto());
        System.out.println("Fecha: " + fechaCita + " | Hora: " + hora);
        System.out.println("Especialidad: " + especialidad);
        ambiente.mostrarInformacion();
    }
    public void cancelarCita() {
        ambiente.liberar();
        System.out.println("Cita #" + idCita + " cancelada y ambiente liberado");
    }
}
