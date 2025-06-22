package br.ufscar.dc.dsw.AA1Veiculos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.ufscar.dc.dsw.AA1Veiculos.security.UsuarioDetailsServiceImpl;

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
                        .requestMatchers("/error", "/login", "/login/**", "/home/**", "/veiculos", "/veiculos/listar", "/js/**",
                                "/css/**", "/image/**", "/webjars/**")
                        .permitAll()
                        .requestMatchers("/veiculos/cadastrar", "/veiculos/editar/**", "/veiculos/excluir/**",
                                "/loja/veiculos", "/veiculos/meus")
                        .hasAuthority("ROLE_LOJA")
                        .requestMatchers("/clientes/**", "/lojas/**", "/veiculos/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/propostas/**").hasAuthority("ROLE_CLIENTE")
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
