package datos;

import domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT id_usuario, usuario, password FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario(usuario, password) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET usuario = ?, password = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

    public UsuarioDAO(){

    }
    public UsuarioDAO(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<Usuario> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();

        conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
        stmnt = conn.prepareStatement(SQL_SELECT);
        rs = stmnt.executeQuery();

        while (rs.next()){
            int idUsuario = rs.getInt("id_usuario");
            String nombre = rs.getString("usuario");
            String password = rs.getString("password");
            usuario = new Usuario(idUsuario, nombre, password);
            usuarios.add(usuario);
        }
        Conexion.close(rs);
        Conexion.close(stmnt);
        if(this.conexionTransaccional == null){
            Conexion.close(conn);
        }
        return usuarios;
    }

    public int insertar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;

        conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
        stmnt = conn.prepareStatement(SQL_INSERT);
        stmnt.setString(1, usuario.getUsuario());
        stmnt.setString(2, usuario.getPassword());
        registros = stmnt.executeUpdate();

        Conexion.close(stmnt);
        if(this.conexionTransaccional == null){
            Conexion.close(conn);
        }
        return registros;
    }

    public int actualizar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;

        conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
        stmnt = conn.prepareStatement(SQL_UPDATE);
        stmnt.setString(1, usuario.getUsuario());
        stmnt.setString(2, usuario.getPassword());
        stmnt.setInt(3, usuario.getIdUsuario());
        registros = stmnt.executeUpdate();

        Conexion.close(stmnt);
        if(this.conexionTransaccional ==null){
            Conexion.close(conn);
        }
        return registros;
    }

    public int eliminar(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmnt = null;
        int registros = 0;

        conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
        stmnt = conn.prepareStatement(SQL_DELETE);
        stmnt.setInt(1, usuario.getIdUsuario());
        registros = stmnt.executeUpdate();

        Conexion.close(stmnt);
        if(this.conexionTransaccional == null){
            Conexion.close(conn);
        }
        return registros;
    }
}
