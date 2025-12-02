public class AmbienteSimple extends Ambiente {
public AmbienteSimple(int id, int piso, int numero) {
        super(id, piso, numero);
    }

    @Override
    public void mostrarTipo() {
        System.out.println("Ambiente Simple");
    }
}
