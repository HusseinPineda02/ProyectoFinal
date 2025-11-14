public class Consultorio {
    private String especialidad;

    public Consultorio(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void cambioDeEspecialidad(String nueva) {
        this.especialidad = nueva;
    }
}
