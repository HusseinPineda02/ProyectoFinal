import java.time.LocalDate;
import java.util.ArrayList;

public class Receta {

    private String idReceta;
    private LocalDate fecha;
    private String indicaciones;
    private ArrayList<MedPrescrito> medicamentos;

    public Receta(String idReceta, LocalDate fecha, String indicaciones) {
        this.idReceta = idReceta;
        this.fecha = fecha;
        this.indicaciones = indicaciones;
        this.medicamentos = new ArrayList<>();
    }

    public void agregarMedicamento(MedPrescrito mp) {
        medicamentos.add(mp);
    }

    public ArrayList<MedPrescrito> getMedicamentos() {
        return medicamentos;
    }

    @Override
    public String toString() {
        return "Receta{" + idReceta + ", fecha=" + fecha + "}";
    }
}
