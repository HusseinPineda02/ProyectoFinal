import java.util.ArrayList;
import java.util.Scanner;
public class Administrativo extends Trabajador {

    Scanner sc = new Scanner(System.in);

    ArrayList<Paciente> pacientes = new ArrayList<>();
    ArrayList<Cita> citas = new ArrayList<>();
    
    public Administrativo() {

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





}