package br.ufscar.dc.dsw.AA1Veiculos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/error", "/login/**", "/home/**", "/veiculos", "/veiculos/listar", "/js/**",
                                "/css/**", "/image/**", "/webjars/**")
                        .permitAll()
                        .requestMatchers("/clientes/**").hasRole("ADMIN")
                        .requestMatchers("/lojas/**").hasRole("ADMIN")
                        .requestMatchers("/veiculos/cadastrar", "/veiculos/editar/**", "/veiculos/excluir/**",
                                "/loja/veiculos")
                        .hasRole("LOJA")
                        .requestMatchers("/propostas/**").hasRole("CLIENTE")
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/default", true)
                        .permitAll())
                .logout((logout) -> logout
                        .logoutSuccessUrl("/")
                        .permitAll());

        return http.build();
    }

}
