package hospital.dao;

import java.sql.SQLException;
import java.util.List;

public interface ICrudDAO<T> {

    void insertar(T obj) throws SQLException;

    void actualizar(T obj) throws SQLException;

    void eliminar(int id) throws SQLException;

    T buscar(int id) throws SQLException;

    List<T> listarTodos() throws SQLException;
}