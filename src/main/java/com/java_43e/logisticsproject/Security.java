package com.java_43e.logisticsproject;


import com.java_43e.logisticsproject.entity.Employee;
import com.java_43e.logisticsproject.entity.Status;
import com.java_43e.logisticsproject.service.database.EmployeeDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {

    private final EmployeeDatabaseService employeeDatabaseService;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/bid/**").hasRole(Status.USER.toString())
                .requestMatchers("/**").hasRole(Status.ADMIN.toString())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        List<UserDetails> users = new ArrayList<>();

        UserDetails admin = User.withUsername(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .roles(Status.ADMIN.toString(), Status.USER.toString())
                .build();
        users.add(admin);

        List<Employee> employees = employeeDatabaseService.getAllEmployees();
        if (!employees.isEmpty()) {
            for (Employee employee : employees) {
                UserDetails userDetails = User.withUsername(employee.getEmployeeName())
                        .password(employee.getPassword())
                        .roles(Status.USER.toString())
                        .build();
                users.add(userDetails);
            }
        }
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
