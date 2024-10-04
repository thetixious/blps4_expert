package org.tix.lab4_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.tix.lab4_1.util.UserRepository;
import org.tix.lab4_1.util.XmlUserMarshaller;
import org.tix.lab4_1.util.XmlUserRepository;


@Configuration
public class SecurityConfiguration {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserRepository userRepository(XmlUserMarshaller xml) {
        return new XmlUserRepository(xml);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN", "camunda-admin")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/**").permitAll()
//                                // Разрешаем доступ к Camunda интерфейсам только администраторам
//                                .requestMatchers("/app/**", "/api/**", "/engine-rest/**").hasRole("camunda-admin")
//                                .requestMatchers("/camunda/app/**").hasRole("camunda-admin")
//                                // Разрешаем доступ к статическим ресурсам (например, картинкам) и странице логина
//                                .requestMatchers("/resources/**", "/login").permitAll()
//                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}

