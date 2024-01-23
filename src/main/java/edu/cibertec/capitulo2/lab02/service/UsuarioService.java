package edu.cibertec.capitulo2.lab02.service;

import edu.cibertec.capitulo2.lab02.dao.UsuarioDAO;
import edu.cibertec.capitulo2.lab02.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public List<UsuarioDTO> getListaUsuarios(){
        return usuarioDAO.getListaUsuarios();
    }

    public void insertarUsuario(UsuarioDTO usuarioDTO){
        usuarioDAO.insertarUsuario(usuarioDTO);
    }

    public UsuarioDTO buscarUsuario(String usuario){
        return usuarioDAO.buscarUsuario(usuario);
    }

    public void actualizarUsuario(UsuarioDTO usuarioDTO){
        usuarioDAO.actualizarUsuario(usuarioDTO);
    }

    public void eliminarUsuario(String usuario){
        usuarioDAO.eliminarUsuario(usuario);
    }
}
