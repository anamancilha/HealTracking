package com.healtracking.residuos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desativa CSRF para evitar erros 403 ao submeter formulários POST
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        // ROTAS PÚBLICAS: Arquivos estáticos e cadastro/salvamento de novos usuários
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/usuarios/novo", "/usuarios/salvar").permitAll()

                        // ROTAS DE ADMINISTRAÇÃO: Inclui a rota raiz e os subdiretórios
                        .requestMatchers("/tipos", "/tipos/**", "/usuarios", "/usuarios/**").hasAnyAuthority("ADMIN", "ROLE_ADMIN")

                        // RESTANTES MÓDULOS
                        .requestMatchers("/residuos/**", "/coletas/**", "/tratamentos/**", "/transportadores/**", "/geradores/**", "/relatorios/**").authenticated()

                        // Qualquer outra rota exige autenticação
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}