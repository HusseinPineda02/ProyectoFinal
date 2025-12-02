
public class MedPrescrito {

    private Medicamento medicamento;
    private String dosis;
    private String duracion;

    public MedPrescrito(Medicamento medicamento, String dosis, String duracion) {
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.duracion = duracion;
    }

    public Medicamento getMedicamento() {
        return medicamento; 
    }
    public String getDosis() { 
        return dosis;
    }
    public String getDuracion() { 
        return duracion;
    }

    public void setMedicamento(Medicamento medicamento) { 
        this.medicamento = medicamento; 
    }
    public void setDosis(String dosis) { 
        this.dosis = dosis; 
    }
    public void setDuracion(String duracion) {
        this.duracion = duracion; 
    }

    @Override
    public String toString() {
        return medicamento.getNombre() + " | Dosis: " + dosis + " | Duración: " + duracion;
    }
}
