public class Consultorio {

    private String codigo;
    private String especialidad;
    private boolean disponible;
    private Doctor doctor;
    //constructor con confirmaciones para evitar errores
    public Consultorio(String codigo, String especialidad) {

        if (codigo == null || codigo.equals("")) {
            System.out.println("Código inválido, se asignará 'SIN_CODIGO'");
            this.codigo = "SIN_CODIGO";
        } else {
            this.codigo = codigo;
        }

        if (especialidad == null || especialidad.equals("")) {
            System.out.println("Especialidad inválida, se asignará 'General'");
            this.especialidad = "General";
        } else {
            this.especialidad = especialidad;
        }

        this.disponible = true;
        this.doctor = null;
    }
    //getters
    public String getCodigo() {
        return codigo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    //setters
    public void setEspecialidad(String nueva) {
        if (!disponible) return;
        if (nueva == null || nueva.equals("")) return;
        this.especialidad = nueva;
    }
    //metodos
    public void asignarDoctor(Doctor d) {
        if (d == null) return;
        if (this.doctor != null) return;
        this.doctor = d;
    }

    public void desasignarDoctor() {
        if (!disponible) return;
        this.doctor = null;
    }
    public void ocupar() {
        if (doctor == null) return;
        disponible = false;
    }

    public void liberar() {
        disponible = true;
    }
    // confirmacion de espacio
    @Override
    public String toString() {
        String nombreDoc = (doctor != null) ? doctor.getNombre() : "Sin doctor";
        return codigo + "  " + especialidad + " " + nomDoc + " se encuentra: " +
                (disponible ? "Libre" : "Ocupado");
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
