
import java.time.LocalDate;

public abstract class Trabajador extends Persona {

    private String idTrabajador;
    private double salario;

    public Trabajador(String nombre, String apellidoP, String apellidoM, String dni, LocalDate fechaNacimiento, String idTrabajador, double salario){
        super(nombre, apellidoP, apellidoM, dni, fechaNacimiento);
        this.idTrabajador = idTrabajador;
        this.salario = salario;
    }
    public void setId(String idTrabajador){
        this.idTrabajador = idTrabajador;
    }
 
    public void setSalario(double salario){
        this.salario = salario;
    }
 
    public String getIdTrabajador(){
        return idTrabajador;
    }
    public double getSalario(){
        return salario;
    }
    public abstract void registrarEntrada();
    public abstract void registrarSalida();
    public abstract double calcularSalarioMensual();

}