import java.util.Scanner;
import java.util.ArrayList;

public class Enfermero extends Trabajador {
    Scanner sc = new Scanner(System.in);
    String especialidad;
    ArrayList<String> notasEnf = new ArrayList<>();

    public Enfermero(){

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

}
