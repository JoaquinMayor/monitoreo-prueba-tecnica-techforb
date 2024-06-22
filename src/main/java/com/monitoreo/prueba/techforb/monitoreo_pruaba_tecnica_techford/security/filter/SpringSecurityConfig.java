package com.monitoreo.prueba.techforb.monitoreo_pruaba_tecnica_techford.security.filter;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration; 

    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager(); 
    }

    
    @Bean
    PasswordEncoder passwordEncoder(){ 
        return new BCryptPasswordEncoder(); 
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests((authz) -> authz
        .requestMatchers(HttpMethod.GET,"/api/lectura").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/lectura/lecturasOk").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/lectura/lecturasMedia").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/lectura/lecturasRoja").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/lectura/lecturasPorTipo/{tipoLectura}/{tipoAlerta}").hasRole("ADMIN")
        .requestMatchers(HttpMethod.POST,"/api/planta").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/planta").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/planta/encontrar/{id}").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT,"/api/planta/update/lecturas").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT,"/api/planta/update").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE,"/api/planta/eliminar/{id}").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/planta/contarOk/{id}").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/planta/contarMedia/{id}").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/planta/contarRoja/{id}").hasRole("ADMIN")
        .requestMatchers(HttpMethod.POST,"/api/sensor").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/sensor").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET,"/api/sensor/contar").hasRole("ADMIN")
        .requestMatchers(HttpMethod.POST,"/api/usuario").permitAll()
         .requestMatchers(HttpMethod.GET,"/api/usuario/existe/{email}").permitAll()
        .requestMatchers(HttpMethod.GET,"/api/usuario/email/{email}").hasRole("ADMIN")
        .anyRequest().permitAll())
        .addFilter(new JwtAuthenticationFilter(authenticationManager())) 
        .addFilter(new JwtValidationFilter(authenticationManager()))
        .csrf(config ->config.disable())
        .cors(cors-> cors.configurationSource(corsConfigurationSource())) 
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build(); 
    } 
        
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*")); 
        config.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT","PATCH")); 
        config.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); 
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter(){
        @SuppressWarnings("null")
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource())); 

        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return corsBean;
    }
}
