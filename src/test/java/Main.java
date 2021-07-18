import datos.Conexion;
import datos.UsuarioDAO;
import domain.Usuario;

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
            
            UsuarioDAO usuarioDao = new UsuarioDAO(conexion);
            Usuario usuarioNuevo1 = new Usuario("Maga123", "1234");
            Usuario usuarioNuevo2 = new Usuario("Marilyn", "hfijuf0");
            usuarioDao.insertar(usuarioNuevo1);
            usuarioDao.insertar(usuarioNuevo2);
            conexion.commit();
            System.out.println("Se han guardado los datos de manera exitosa");
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
