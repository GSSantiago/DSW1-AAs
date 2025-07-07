package br.ufscar.dc.dsw.AA2Veiculos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.ufscar.dc.dsw.AA2Veiculos.security.UsuarioDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())

                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/error", "/login", "/login/**", "/logout", "/home/**", "/veiculos", "/veiculos/listar","/veiculos/{id}", "/imagem/**", "/js/**",
                                "/css/**", "/image/**", "/webjars/**")
                        .permitAll()
                        .requestMatchers("/veiculos/cadastrar", "/veiculos/editar/**", "/veiculos/excluir/**",
                        		"/veiculos/salvar/**","/loja/veiculos", "/veiculos/meus")
                        .hasAnyAuthority("ROLE_LOJA", "ROLE_ADMIN")
                        .requestMatchers("/clientes/**", "/lojas/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/propostas/**").hasAnyAuthority("ROLE_CLIENTE", "ROLE_LOJA","ROLE_ADMIN")
                        .anyRequest().authenticated())

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/default")
                        .permitAll())

                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll());

        return http.build();
    }


}
