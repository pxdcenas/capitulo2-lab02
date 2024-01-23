package edu.cibertec.capitulo2.lab02.dto;

public class UsuarioDTO {

    private String usuario;
    private String clave;
    private String nombreCompleto;

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
}
