package br.ufscar.dc.dsw.AA1Veiculos.controller;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/default")
    public void defaultAfterLogin(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority auth : authorities) {
            String role = auth.getAuthority();

            if (role.equals("ADMIN")) {
                response.sendRedirect(request.getContextPath() + "/admin/home");
                return;
            } else if (role.equals("LOJA")) {
                response.sendRedirect(request.getContextPath() + "/veiculos/loja");
                return;
            } else if (role.equals("CLIENTE")) {
                response.sendRedirect(request.getContextPath() + "/propostas");
                return;
            }
        }

        response.sendRedirect(request.getContextPath() + "/");
    }
}

