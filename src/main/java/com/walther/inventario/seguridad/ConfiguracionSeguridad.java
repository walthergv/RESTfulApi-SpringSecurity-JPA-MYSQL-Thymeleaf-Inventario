package com.walther.inventario.seguridad;

import com.walther.inventario.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Bean
    static public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioServicio);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/registro**", "/js/**", "/css/**", "/img/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> {
                            try {
                                formLogin
                                        .loginPage("/login")
                                        .successHandler(successHandler())
                                        .permitAll()
                                .and()
                                .logout()
                                        .invalidateHttpSession(true)
                                        .clearAuthentication(true)
                                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                        .logoutSuccessUrl("/login?logout")
                                        .permitAll();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }

                );

        return http.build();
    }
    public AuthenticationSuccessHandler successHandler(){
        return (request, response, authentication) -> response.sendRedirect("/home");
    }
}
