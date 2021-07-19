import datos.Conexion;
import datos.IUsuarioDAO;
import datos.UsuarioDAO;
import domain.UsuarioDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }

            IUsuarioDAO usuarioDao = new UsuarioDAO(conexion);
            List<UsuarioDTO> usuarios = usuarioDao.seleccionar();
            for(UsuarioDTO usuario : usuarios){
                System.out.println(usuario);
            }
            conexion.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
            System.out.println("Entramos al Rollback");
            try {
                conexion.rollback();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}
