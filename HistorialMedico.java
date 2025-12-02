public class HistorialMedico {
    private String paciente;
    private String diagnostico;
    private String tratamiento;

    public HistorialMedico(String paciente, String diagnostico, String tratamiento) {
        this.paciente = paciente;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }

    public void mostrarConNumeros() {
        System.out.println("---- HISTORIAL MÉDICO ----");
        System.out.println("1. Paciente: " + paciente);
        System.out.println("2. Diagnóstico: " + diagnostico);
        System.out.println("3. Tratamiento: " + tratamiento);
    }
}
