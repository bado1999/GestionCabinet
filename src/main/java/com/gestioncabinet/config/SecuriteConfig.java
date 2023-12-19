package com.gestioncabinet.config;

import com.gestioncabinet.metier.ServiceMetier;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.Collection;
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecuriteConfig {
    private ServiceMetier serviceMetier;
    @Bean
    public UserDetailsService userDetails() {
        return username -> {
            com.gestioncabinet.entities.User appUser=serviceMetier.loadByEmail(username);
            Collection<GrantedAuthority> authorities=new ArrayList<>();
            appUser.getRoles().forEach(appRole -> {
                authorities.add(new SimpleGrantedAuthority(appRole.getRoleName()));
            });
            return new User(username,appUser.getPassword(), authorities);
        };
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        return http
                .authorizeHttpRequests(
                        requests->requests
                                .requestMatchers("/apointment","/makeappointment").authenticated()
                                .requestMatchers("/admin_panel","/admin_add_consultation","/addSoin","supprimerSoin","/modifierSoin","/admin_appointment").hasAuthority("ADMIN")
                                .anyRequest().permitAll()
                )
                .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/home").and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and()
                .build();
    }
}