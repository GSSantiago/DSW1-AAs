package br.ufscar.dc.dsw.AA1Veiculos.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Collection;

@Controller
public class DefaultController {

    @GetMapping("/default")
    public void defaultAfterLogin(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority auth : authorities) {
            String role = auth.getAuthority();

            if (role.equals("ROLE_ADMIN")) {
                response.sendRedirect(request.getContextPath() + "/clientes");
                return;
            } else if (role.equals("ROLE_LOJA")) {
                response.sendRedirect(request.getContextPath() + "/veiculos/meus");
                return;
            } else if (role.equals("ROLE_CLIENTE")) {
                response.sendRedirect(request.getContextPath() + "/veiculos");
                return;
            }
        }

        response.sendRedirect(request.getContextPath() + "/");
    }
}
