package edu.cibertec.capitulo2.lab02.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Base64;

public class UsuarioDTO {

    @Size(min = 3, max = 20, message = "Error en el tamaño")
    private String usuario;

    @NotBlank(message = "NotBlank")
    @NotEmpty(message = "NotEmpty")
    private String clave;

    @NotNull(message = "NotNull")
    private String nombreCompleto;

    private byte[] foto;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String usuario, String clave, String nombreCompleto) {
        this.usuario = usuario;
        this.clave = clave;
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getImageDataBase64() {
        return Base64.getEncoder().encodeToString(foto);
    }


}
