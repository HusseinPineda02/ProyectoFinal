import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

public class Enfermero extends Trabajador {

    Scanner sc = new Scanner(System.in);

    private String turno;

    ArrayList<String> notasEnf = new ArrayList<>();

    public Enfermero(String nombre, String apellidoP, String apellidoM, String dni,
                     LocalDate fechaNacimiento, String idTrabajador, double salario, String turno) {

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
            if (obj.getTipoObjeto().equals(tipoObjeto)) {

                int nuevaCantidad = obj.getCantidad() - cantidad;
                if (nuevaCantidad < 0) nuevaCantidad = 0;

                obj.setCantidad(nuevaCantidad);
            }
        }
    }

    public void administrarMedicamento(ArrayList<Medicamento> medicamentos, String nombreMedicamento, int cantidad){
        for(Medicamento obj : medicamentos ){
            if (obj.getNombreMedicamento().equals(nombreMedicamento)) {

                int nuevoStock = obj.getStock() - cantidad;
                if (nuevoStock < 0) nuevoStock = 0;

                obj.setStock(nuevoStock);
            }
        }
    }

    public void tomarSignosVitales() {
        System.out.println("Tomando signos vitales...");
    }

    public void asistirDoctor(Doctor doctor) {
        System.out.println("Asistiendo al doctor " + doctor.getNombre());
    }

    public void registrarNotasEnfermeria() {
        System.out.println("Escribir notas:");
        String notas = sc.nextLine();
        notasEnf.add(notas);
    }

    @Override
    public void registrarEntrada() {
        System.out.println("Enfermero " + getNombre() + " registró entrada.");
    }

    @Override
    public void registrarSalida() {
        System.out.println("Enfermero " + getNombre() + " registró salida.");
    }

    @Override
    public double calcularSalarioMensual() {
        return this.getSalario();
    }
}
