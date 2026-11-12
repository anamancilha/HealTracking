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
                // ROTAS PÚBLICAS: Apenas arquivos visuais (Bootstrap/JS) e o cadastro inicial de usuário
                .requestMatchers("/css/**", "/js/**", "/images/**", "/usuarios/novo", "/usuarios/salvar").permitAll()
                
                // REGRAS POR PERFIL 
                // Apenas o ADMIN gerencia os tipos de resíduos e a lista geral de usuários
                .requestMatchers("/tipos/**", "/usuarios").hasRole("ADMIN")
                
                // Apenas ADMIN ou GERADOR podem lançar novos resíduos
                .requestMatchers("/residuos/novo", "/residuos/salvar").hasAnyRole("ADMIN", "GERADOR")
                
                // Apenas ADMIN ou TRANSPORTADOR registram novas coletas
                .requestMatchers("/coletas/novo", "/coletas/salvar").hasAnyRole("ADMIN", "TRANSPORTADOR")
                
                // Apenas ADMIN ou entidades de TRATAMENTO gerenciam locais de destinação
                .requestMatchers("/tratamentos/novo", "/tratamentos/salvar").hasAnyRole("ADMIN", "TRATAMENTO")
                
                // ROTAS PROTEGIDAS GERAIS: Listagens e Relatórios exigem apenas que o usuário esteja logado (qualquer perfil)
                .requestMatchers("/relatorios/**", "/geradores/**", "/residuos", "/coletas", "/transportadores/**", "/tratamentos").authenticated()
                
                // Qualquer outra rota não mapeada exige autenticação
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login")
                // Após o login, redireciona para a página inicial que seus colegas estão criando
                .defaultSuccessUrl("/", true) 
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutSuccessUrl("/login?logout") // Volta para o login com aviso de saída
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
