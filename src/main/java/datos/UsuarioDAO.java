package datos;

import domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final String SQL_SELECT = "SELECT id_usuario, usuario, password FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario(usuario, password) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET usuario = ?, password = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

    public List<Usuario> seleccionar(){
        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmnt = conn.prepareStatement(SQL_SELECT);
            rs = stmnt.executeQuery();

            while (rs.next()){
                int idUsuario = rs.getInt("id_usuario");
                String nombre = rs.getString("usuario");
                String password = rs.getString("password");
                usuario = new Usuario(idUsuario, nombre, password);
                usuarios.add(usuario);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        }
        finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmnt);
                Conexion.close(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace(System.out);
            }
        }
        return usuarios;
    }
    public int insertar(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmnt = conn.prepareStatement(SQL_INSERT);
            stmnt.setString(1, usuario.getUsuario());
            stmnt.setString(2, usuario.getPassword());
            registros = stmnt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        }
        finally {
            try {
                Conexion.close(stmnt);
                Conexion.close(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace(System.out);
            }
        }
        return registros;
    }
    public int actualizar(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmnt = conn.prepareStatement(SQL_UPDATE);
            stmnt.setString(1, usuario.getUsuario());
            stmnt.setString(2, usuario.getPassword());
            stmnt.setInt(3, usuario.getIdUsuario());
            registros = stmnt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        }
        finally {
            try {
                Conexion.close(stmnt);
                Conexion.close(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace(System.out);
            }
        }
        return registros;
    }
    public int eliminar(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;

        try {
            conn = Conexion.getConnection();
            stmnt = conn.prepareStatement(SQL_DELETE);
            stmnt.setInt(1, usuario.getIdUsuario());
            registros = stmnt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace(System.out);
        }
        finally {
            try {
                Conexion.close(stmnt);
                Conexion.close(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
