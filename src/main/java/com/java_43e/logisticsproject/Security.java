package com.java_43e.logisticsproject;//package com.example.kisialeuskijavapizzaproject;
//
//import com.example.kisialeuskijavapizzaproject.entity.PersonSecurity;
//import com.example.kisialeuskijavapizzaproject.entity.Status;
//import com.example.kisialeuskijavapizzaproject.repositaries.PersonSecurityRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//@Configuration
//@EnableWebSecurity
//public class Security {
//    @Autowired
//    private PersonSecurityRepository personSecurityRepository;
/////get-all-cafes /get-cafe/{id} /get-all-pizzas /get-pizza/{id} /get-all-person /get-person/{id}
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        LogoutConfigurer<HttpSecurity> logout = http.authorizeHttpRequests()
//                .requestMatchers("/get-cafe/{id}").authenticated()
//                .requestMatchers("/get-all-cafes", "/get-all-pizzas ", "/get-all-person").hasRole(String.valueOf(Status.USER))
//                .requestMatchers("/get-person/{id}").hasRole(String.valueOf(Status.ADMIN))
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .and()
//                .logout();
//
//        return http.build();
//    }
//
//
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        List<PersonSecurity> personSecurities = personSecurityRepository.findAll();
//        List<UserDetails> users = new ArrayList<>();
//        for (PersonSecurity personSecurity : personSecurities) {
//            UserDetails userDetails = User
//                    .withUsername(personSecurity.getLogin())
//                    .password(personSecurity.getPassword())
//                    .roles(String.valueOf(personSecurity.getStatus()))
//                    .build();
//                        users.add(userDetails);
//        }
//
//
//        return new InMemoryUserDetailsManager(users);
//    }
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
//
//}
