package datos;

import domain.UsuarioDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO{
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

    @Override
    public List<UsuarioDTO> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        UsuarioDTO usuario = null;
        List<UsuarioDTO> usuarios = new ArrayList<>();

        conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
        stmnt = conn.prepareStatement(SQL_SELECT);
        rs = stmnt.executeQuery();

        while (rs.next()){
            int idUsuario = rs.getInt("id_usuario");
            String nombre = rs.getString("usuario");
            String password = rs.getString("password");
            usuario = new UsuarioDTO(idUsuario, nombre, password);
            usuarios.add(usuario);
        }
        Conexion.close(rs);
        Conexion.close(stmnt);
        if(this.conexionTransaccional == null){
            Conexion.close(conn);
        }
        return usuarios;
    }

    @Override
    public int insertar(UsuarioDTO usuario) throws SQLException {
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

    @Override
    public int actualizar(UsuarioDTO usuario) throws SQLException {
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

    @Override
    public int eliminar(UsuarioDTO usuario) throws SQLException {
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
