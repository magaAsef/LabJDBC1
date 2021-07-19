package datos;

import domain.UsuarioDTO;

import java.sql.SQLException;
import java.util.List;

public interface IUsuarioDAO {
    List<UsuarioDTO> seleccionar() throws SQLException;

    int insertar(UsuarioDTO usuario) throws SQLException;

    int actualizar(UsuarioDTO usuario) throws SQLException;

    int eliminar(UsuarioDTO usuario) throws SQLException;
}
