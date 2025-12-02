public class Cita {
    private String paciente;
    private String doctor;
    private String fecha;
    private String motivo;

    public Cita(String paciente, String doctor, String fecha, String motivo) {
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public void mostrarDatosCita() {
        System.out.println("---- DATOS DE LA CITA ----");
        System.out.println("Paciente: " + paciente);
        System.out.println("Doctor: " + doctor);
        System.out.println("Fecha: " + fecha);
        System.out.println("Motivo: " + motivo);
    }
}
