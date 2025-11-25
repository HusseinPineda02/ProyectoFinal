import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
public class Administrativo extends Trabajador {

    Scanner sc = new Scanner(System.in);
    String area;

    public Administrativo(String nombre, String apellidoP, String apellidoM, String dni, LocalDate fechaNacimiento, String idTrabajador, double salario, String area) {
        super(nombre, apellidoP, apellidoM, dni, fechaNacimiento, idTrabajador, salario);
        this.area = area;
    }

    public void setArea(String area){
        this.area = area;
    }
    public String getArea(){
        return area;
    }
    
    public void generarCita(Paciente paciente){
        System.out.println("Cita generada para paciente: " + paciente.getNombre());
        int idCita = citas.size();
        Cita cita = new Cita(idCita);
        citas.add(cita);
    }

    public void cancelarCita(int idCita) {
        int i = 0;
        for(Cita obj : citas) {
            if(obj.getIdCita == idCita){
                citas.remove(i);
            }
            i++;
        }
    }

    public void nuevoPaciente(String dni, String nombre, String apep, String apem) {
        Paciente p = new Paciente(dni, nombre, apep, apem);

        pacientes.add(p);
    }


    public void eliminarPaciente(String dni) {
        int i=0;
        for(Paciente p : pacientes){
            if(p.getDni().equals(dni)){
                pacientes.remove(i);
            }
            i++;
        }
    }

    public void modificarDatosPaciente(String dni) {
        int i=0;
        for(Paciente p : pacientes){
            if(p.getDni().equals(dni)){
                System.out.println("Dato a modificar:\n1. Nombre\n2. Apellido paterno\n3. Apellido materno\nOtro. Salir");
                String opcion = sc.nextLine();
                switch(opcion){
                    case "1": System.out.print("Nuevo nombre: "); pacientes(i).setNombre(sc.nextLine()); break;
                    case "2": System.out.print("Nuevo apellido paterno: "); pacientes(i).setApePaterno(sc.nextLine()); break;
                    case "3": System.out.print("Nuevo apellido materno: "); pacientes(i).setApeMaterno(sc.nextLine()); break;
                    default: System.out.println("Cambios realizados.");
                }
            }
            i++;
        }
    }

    @Override
    public void registrarEntrada() {

    }
    @Override
    public void registrarSalida() {

    }

    @Override
    public double calcularSalarioMensual() {
        return this.getSalario();
    }

}