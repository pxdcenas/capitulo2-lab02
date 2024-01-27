package edu.cibertec.capitulo2.lab02.controller;

import edu.cibertec.capitulo2.lab02.beans.RequestBean;
import edu.cibertec.capitulo2.lab02.beans.SessionBean;
import edu.cibertec.capitulo2.lab02.dto.UsuarioDTO;
import edu.cibertec.capitulo2.lab02.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Controller
public class UsuarioController {

    private static final String UPLOAD_FOLDER = System.getProperty("user.dir") + "/src/main/resources/uploads";

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String showLogin(){
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(UsuarioDTO usuarioDTO){

        UsuarioDTO usuarioDTOEncontrado = usuarioService.validarLogin(usuarioDTO);
        if (usuarioDTOEncontrado == null){
            return new ModelAndView("login","msgError", "Usuario y/o contraseña incorrectos");
        } else {
            sessionBean.setUsuarioLogueado(usuarioDTOEncontrado.getNombreCompleto());
            sessionBean.setAutenticado(true);
            return new ModelAndView("welcome", "mensaje", "Bienvenido Dany Cenas");
        }
    }

    @GetMapping("/listarUsuarios")
    public String listarUsuarios(Model modelo){
        if (sessionBean.isAutenticado()) {
            modelo.addAttribute("lista", usuarioService.getListaUsuarios());
            return "usuarios/listar";
        }
        return "redirect:/";
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

    @GetMapping("/foto/{usuario}")
    public ModelAndView foto(@ModelAttribute("usuario") String usuario){
        return new ModelAndView("usuarios/foto", "usuario", usuarioService.buscarUsuario(usuario));
    }

    @PostMapping("/uploadFile")
    public ModelAndView uploadFile(@RequestPart("file")MultipartFile image, @RequestParam("usuario") String usuario) throws IOException {

        String fileName = UUID.randomUUID() + "-" + image.getOriginalFilename();
        File file = new File(UPLOAD_FOLDER, fileName);
        image.transferTo(file);

        byte[] imageByes = Files.readAllBytes(file.toPath());
        String base64Image = Base64.getEncoder().encodeToString(imageByes);

        UsuarioDTO usuarioEntrado = usuarioService.buscarUsuario(usuario);
        usuarioEntrado.setFoto(imageByes);
        usuarioService.actualizarUsuario(usuarioEntrado);

        return new ModelAndView("redirect:/listarUsuarios");
    }

    @Autowired
    private RequestBean requestBean;

    @GetMapping("/solicitud")
    public ModelAndView solicitud(){
        return new ModelAndView("solicitud", "solicitudIndividual", requestBean.getIp());
    }
    @Autowired
    private SessionBean sessionBean;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
