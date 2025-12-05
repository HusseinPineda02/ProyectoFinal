package hospital.modelo;

public abstract class Trabajador extends Persona {

    protected int idTrabajador;
    protected double salario;
    protected String cargo;

    public Trabajador(int idTrabajador, String dni, String nombre, String apellidoP,
                      String apellidoM, double salario, String cargo) {
        super(dni, nombre, apellidoP, apellidoM);
        this.idTrabajador = idTrabajador;
        this.salario = salario;
        this.cargo = cargo;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double calcularSalarioMensual() {
        return salario;
    }

    @Override
    public String toString() {
        return "[" + idTrabajador + "] " + super.toString() + " - " + cargo +
               " (S/ " + salario + ")";
    }
}