package edu.cibertec.capitulo2.lab02.beans;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;

@RequestScope
@Component
public class RequestBean {

    private final LocalDateTime startTime;

    private String ip;

    public RequestBean(HttpServletRequest request) {
        this.startTime = LocalDateTime.now();
        this.ip = request.getRemoteAddr();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getIp() {
        return ip;
    }
}
