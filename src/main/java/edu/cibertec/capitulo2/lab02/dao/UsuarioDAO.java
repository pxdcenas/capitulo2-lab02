package edu.cibertec.capitulo2.lab02.dao;

import edu.cibertec.capitulo2.lab02.dto.UsuarioDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDAO {

    private List<UsuarioDTO> listaUsuarios;

    public UsuarioDAO() {
        listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new UsuarioDTO("jose", "12345", "José Perez"));
        listaUsuarios.add(new UsuarioDTO("yaddif", "54321", "Yaddif Medina"));
        listaUsuarios.add(new UsuarioDTO("carmen", "c1rm3n", "Carmen Rios"));
        listaUsuarios.add(new UsuarioDTO("maria", "m2ria", "Maria Quispe"));
    }

    public List<UsuarioDTO> getListaUsuarios(){
        return listaUsuarios;
    }

    public void insertarUsuario(UsuarioDTO usuarioDTO){
        listaUsuarios.add(usuarioDTO);
    }

    public UsuarioDTO buscarUsuario(String usuario){
        return listaUsuarios
                .stream()
                .filter(u -> u.getUsuario().equalsIgnoreCase(usuario))
                .findFirst().orElse(null);
    }

    public void actualizarUsuario(UsuarioDTO usuarioDTO){
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if(listaUsuarios.get(i).getUsuario().equalsIgnoreCase(usuarioDTO.getUsuario())){
                listaUsuarios.set(i, usuarioDTO);
                break;
            }
        }
    }

    public void eliminarUsuario(String usuario){
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if(listaUsuarios.get(i).getUsuario().equalsIgnoreCase(usuario)){
                listaUsuarios.remove(i);
                break;
            }
        }
    }
}
