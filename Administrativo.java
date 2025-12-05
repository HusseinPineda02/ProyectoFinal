package hospital.modelo;

public class Administrativo extends Trabajador {

    private String area;

    public Administrativo(int idTrabajador, String dni, String nombre, String apellidoP,
                          String apellidoM, double salario, String area) {
        super(idTrabajador, dni, nombre, apellidoP, apellidoM, salario, "Administrativo");
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return super.toString() + " - Área: " + area;
    }

}
