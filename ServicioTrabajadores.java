import java.util.ArrayList;

public class ServicioTrabajadores {

    private ArrayList<Trabajador> trabajadores;

    public ServicioTrabajadores() {
        trabajadores = new ArrayList<>();
    }

    public void registrarTrabajador(Trabajador t) {
        trabajadores.add(t);
    }
    public Trabajador buscarTrabajador(String id) {
        for(Trabajador t : trabajadores){
            if(t.getIdTrabajador().equals(id)){
                return t;
            }
        }
        return null;
    }
    public ArrayList<Trabajador> mostrarTrabajadores() {
        return trabajadores;
    }
}