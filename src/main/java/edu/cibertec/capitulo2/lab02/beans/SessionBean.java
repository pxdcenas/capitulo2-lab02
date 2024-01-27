package edu.cibertec.capitulo2.lab02.beans;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class SessionBean {

    private String usuarioLogueado;

    private boolean autenticado;

    public String getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(String usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
}
