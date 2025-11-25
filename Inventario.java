import java.util.HashMap;

public class Inventario {

    private HashMap<String, Integer> stock; 

    public Inventario() {
        stock = new HashMap<>();
    }

    public void agregarStock(String idMed, int cantidad) {
        stock.put(idMed, stock.getOrDefault(idMed, 0) + cantidad);
    }

    public boolean reducirStock(String idMed, int cantidad) {
        int actual = stock.getOrDefault(idMed, 0);
        if (actual >= cantidad) {
            stock.put(idMed, actual - cantidad);
            return true;
        }
        return false;
    }

    public int consultarStock(String idMed) {
        return stock.getOrDefault(idMed, 0);
    }
}
