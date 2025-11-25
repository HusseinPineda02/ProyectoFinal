import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

public class Enfermero extends Trabajador {

    Scanner sc = new Scanner(System.in);

    private String turno;

    ArrayList<String> notasEnf = new ArrayList<>();

    public Enfermero(String nombre, String apellidoP, String apellidoM, String dni, LocalDate fechaNacimiento, String idTrabajador, double salario, String turno) {
        super(nombre, apellidoP, apellidoM, dni, fechaNacimiento, idTrabajador, salario);
        this.turno = turno;
    }

    public void setTurno(String turno){
        this.turno = turno;
    }
    public String getTurno(){
        return turno;
    }

    public void tomarInventario(ArrayList<Inventario> inventario, String tipoObjeto, int cantidad){
        for(Inventario obj : inventario ){
            if (obj.getTipoObjeto() == tipoObjeto) {
                obj.setCantidad(obj.getCantidad()-cantidad);
            }
        }
    } 

    public void administrarMedicamento(ArrayList<Medicamento> medicamentos, String nombreMedicamento, int cantidad){
        for(Medicamento obj : medicamentos ){
            if (obj.getNombreMedicamento() == nombreMedicamento) {
                obj.setCantidad(obj.getStock() - cantidad);
            }
        }
    }

    public void tomarSignosVitales() {
        System.out.println("Tomar signos vitales");
    }

    public void asistirDoctor(Doctor doctor) {
        System.out.println("Asistiendo al doctor " + doctor.getNombre());
    }

    public void registrarNotasEnfermeria() {
        String notas;
        System.out.println("Escribir notas: ");
        notas = sc.nextLine();
        notasEnf.add(notas);
    }
     @Override
    public void registrarEntrada() {

    }
    @Override
    public void registrarSalida() {
        
    @Override
    public double calcularSalarioMensual() { 
        return this.getSalario(); 
    }
}
