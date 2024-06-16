package com.kabe.produits.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


import java.util.Collections;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    KeycloakRoleConverter keycloakRoleConverter;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws
            Exception {
        http.sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .csrf(csrf -> csrf.disable())

                .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration cors = new CorsConfiguration();
                        cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        cors.setAllowedMethods(Collections.singletonList("*"));
                        cors.setAllowedHeaders(Collections.singletonList("*"));
                        cors.setExposedHeaders(Collections.singletonList("Authorization"));

                        return cors;
                    }
                }))
//.authorizeHttpRequests().anyRequest().permitAll(); si on veut desactiver la securite
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers("/api/all/**").hasAnyAuthority("ADMIN","USER")
                                .requestMatchers(HttpMethod.GET,"/api/getbyid/**").hasAnyAuthority("ADMIN","USER")
                                .requestMatchers(HttpMethod.PUT,"/api/updateprod/**").hasAuthority("ADMIN")
                                //.requestMatchers(HttpMethod.POST,"/api/addprod/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/delprod/**").hasAuthority("ADMIN")
								
								.requestMatchers(HttpMethod.POST,"/api/image/uplaodImageProd/**").hasAuthority("ADMIN")
								.requestMatchers(HttpMethod.POST,"/api/image/uplaod/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/image/update/**").hasAuthority("ADMIN")
								.requestMatchers(HttpMethod.GET,"/api/image/getImagesProd/**").hasAuthority("ADMIN")
								.requestMatchers(HttpMethod.POST,"/api/image/uploadFS/**").hasAuthority("ADMIN")
								.requestMatchers(HttpMethod.GET,"/api/image/loadfromFS/**").hasAuthority("ADMIN")

                                .requestMatchers(HttpMethod.GET,"/api/image/get/info/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/image/load/**").hasAuthority("ADMIN")
								
                                .anyRequest().authenticated())

                //.oauth2ResourceServer(rs -> rs.jwt(Customizer.withDefaults()));
                .oauth2ResourceServer(ors->ors.jwt(jwt->
                        jwt.jwtAuthenticationConverter(keycloakRoleConverter)));


        return http.build();
    }
}
