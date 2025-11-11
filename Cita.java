import java.util.Date;

public class Cita {
    private int idCita;
    private Date fechaCita;
    private String especialidad;

    // Constructor básico
    public Cita(int idCita, Date fechaCita, String especialidad) {
        this.idCita = idCita;
        this.fechaCita = fechaCita;
        this.especialidad = especialidad;
    }

    // Método principal
    public void mostrarDatosCita() {
        System.out.println("Cita N°: " + idCita);
        System.out.println("Fecha: " + fechaCita);
        System.out.println("Especialidad: " + especialidad);
    }

    public void cancelarCita() {
        System.out.println("La cita N° " + idCita + " ha sido cancelada.");
    }
}
