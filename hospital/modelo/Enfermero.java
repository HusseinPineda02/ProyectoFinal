package hospital.modelo;

public class Enfermero extends Trabajador {

    private String turno;
    
    public Enfermero(int idTrabajador, String dni, String nombre, String apellidoP,
                     String apellidoM, double salario, String turno) {
        super(idTrabajador, dni, nombre, apellidoP, apellidoM, salario, "Enfermero");
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return super.toString() + " - Turno: " + turno;
    }
}