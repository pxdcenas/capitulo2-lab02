package edu.cibertec.capitulo2.lab02.controller;

import edu.cibertec.capitulo2.lab02.dto.UsuarioDTO;
import edu.cibertec.capitulo2.lab02.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String showLogin(){
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(UsuarioDTO usuarioDTO){
        if (usuarioDTO.getUsuario().equalsIgnoreCase("admin") && usuarioDTO.getClave().equalsIgnoreCase("admin")){
            return new ModelAndView("welcome", "mensaje", "Bienvenido Dany Cenas");
        }
        return new ModelAndView("login","msgError", "Usuario y/o contraseña incorrectos");
    }

    @GetMapping("/listarUsuarios")
    public ModelAndView listarUsuarios(){
        return new ModelAndView("usuarios/listar", "lista", usuarioService.getListaUsuarios());
    }

    @GetMapping("/crear")
    public ModelAndView crear(){
        return new ModelAndView("usuarios/crear","usuarioBean",new UsuarioDTO());
    }

    @PostMapping("/grabar")
    public ModelAndView grabar(@Valid @ModelAttribute("usuarioBean") UsuarioDTO usuarioDTO, BindingResult result){
        ModelAndView mv = null;
        if (result.hasErrors()) {
            mv = new ModelAndView("usuarios/crear", "usuarioBean", usuarioDTO);
        } else {
            usuarioService.insertarUsuario(usuarioDTO);
            mv = new ModelAndView("redirect:/listarUsuarios");
        }
        return mv;
    }

    @GetMapping("/editar/{usuario}")
    public ModelAndView editar(@ModelAttribute("usuario") String usuario){
        return new ModelAndView("usuarios/editar", "usuarioBean", usuarioService.buscarUsuario(usuario));
    }

    @PostMapping("/actualizar")
    public String actualizar(UsuarioDTO usuarioDTO){
        usuarioService.actualizarUsuario(usuarioDTO);
        return "redirect:/listarUsuarios";
    }

    @GetMapping("/eliminar/{usuario}")
    public String eliminar(@ModelAttribute("usuario") String usuario){
        usuarioService.eliminarUsuario(usuario);
        return "redirect:/listarUsuarios";
    }

    @PostMapping("/eliminar")
    public String eliminarPost(@ModelAttribute("usuario") String usuario){
        usuarioService.eliminarUsuario(usuario);
        return "redirect:/listarUsuarios";
    }
}
