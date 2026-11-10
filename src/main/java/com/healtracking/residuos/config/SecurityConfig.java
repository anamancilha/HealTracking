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
            .authorizeHttpRequests((requests) -> requests
                // Libera arquivos estáticos (Bootstrap, imagens) e a tela de cadastro inicial de usuário
                .requestMatchers("/css/**", "/js/**", "/images/**", "/usuarios/novo", "/usuarios/salvar").permitAll()
                // Exemplo de bloqueio por perfil: só ADMIN gerencia tipos de resíduos
                .requestMatchers("/tipos/**").hasRole("ADMIN")
                // Qualquer outra página do sistema exige que o usuário esteja logado
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login") // Diz ao Spring para usar a tela em português
                .defaultSuccessUrl("/geradores", true) // Para onde vai após logar
                .permitAll()
            )

            .logout((logout) -> logout.permitAll());

        return http.build();
    }

    // Criptografa as senhas para não ficarem expostas em texto puro no MySQL
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

