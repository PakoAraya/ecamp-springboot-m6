package com.pako.modulo_6.config;

import com.pako.modulo_6.services.UsuarioLoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import com.pako.modulo_6.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;


@EnableMethodSecurity //Habilita el uso de anotaciones para seguridad
@Configuration
public class SecurityConfig {

  private final UsuarioLoginService usuarioLoginService;

  @Value("${jwt.secret}")
  private String jwtKey;

  public SecurityConfig(UsuarioLoginService usuarioLoginService) {
    this.usuarioLoginService = usuarioLoginService;
  }

  //AuthenticationManager
  //encargado de realizar la autenticacion
  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http, UsuarioLoginService usuarioLoginService,
                                                     PasswordEncoder passwordEncoder) throws Exception {
    AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    authBuilder.userDetailsService(usuarioLoginService).passwordEncoder(passwordEncoder);

    return authBuilder.build();
  }

  //SecurityFilterChain
  //va a generar la reglas  de seguridad
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
    http
            .csrf(csrf -> csrf.disable()) //Para evitar hackeos
            .authorizeHttpRequests(auth ->
                    auth
                            .requestMatchers("/rest/**").authenticated()//Con JWT se obliga autenticar a usuarios con rutas rest
                            .requestMatchers("/auth/**").permitAll()//Con JWT se permite a todos acceder a rutas auth
                            .anyRequest().permitAll()//Con JWT se permite a todos acceder a cualquier ruta
            )
            //quitamos sesion y login de springSecurity
            .sessionManagement(sessionManagement ->
                    sessionManagement
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
//            .formLogin(form ->
//                    form
//                            .defaultSuccessUrl("/producto/lista", true)//cuando se hace el login nos envia a esta ruta
//                            .permitAll()//quienes-TODOS
//            )
//            .logout(logout ->
//                    logout
//                            .logoutUrl("/logout")//ruta de logout
//                            .logoutSuccessUrl("/login?logout")//ruta cuando el logout esta hecho
//            )
//            .exceptionHandling(exceptions -> exceptions
//                            .accessDeniedPage("/access-denied")//ruta de acceso denegado
//            )
            .addFilterBefore(new JwtAuthenticationFilter(authenticationManager, jwtKey), BasicAuthenticationFilter.class);
    return http.build();
  }
}