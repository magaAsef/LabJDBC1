import datos.UsuarioDAO;
import domain.Usuario;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UsuarioDAO usuarioDao = new UsuarioDAO();

        /*
        Usuario usuario1 = new Usuario("nikki123", "juegazo");
        usuarioDao.insertar(usuario1);

        Usuario usuario2 = new Usuario(2, "nikki120", "juegazo");
        usuarioDao.actualizar(usuario2);

        Usuario usuario3 = new Usuario(2);
        usuarioDao.eliminar(usuario3);
         */
        List<Usuario> usuarios = usuarioDao.seleccionar();
        for(Usuario usuario: usuarios){
            System.out.println(usuario);
        }
    }
}
