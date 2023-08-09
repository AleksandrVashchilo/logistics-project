package com.java_43e.logisticsproject;
import com.java_43e.logisticsproject.entity.Employee;
import com.java_43e.logisticsproject.service.database.EmployeeDatabaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
@SpringJUnitWebConfig
@WebMvcTest(Security.class)
public class SecurityTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private EmployeeDatabaseService employeeDatabaseService;

    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    @WithMockUser(username = "${admin.username}", password = "${admin.password}", roles = {"ADMIN", "USER"})
    public void testSecurityConfig() throws Exception {
        // Mocking employees
        Employee employee1 = new Employee();
        employee1.setEmployeeName("JohnDoe");
        employee1.setPassword("$2a$10$2SYb33d3w.hM5AhXCn6foOcxqB0FXCTDgLrV86yUVRGg6uWtv37GK");
        List<Employee> employees = Arrays.asList(employee1);

        when(employeeDatabaseService.getAllEmployees()).thenReturn(employees);

        // Test SecurityFilterChain
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/some-url"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/some-other-url"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Add more test scenarios as needed

        // Verify that the employeeDatabaseService.getAllEmployees() is called
        verify(employeeDatabaseService, times(1)).getAllEmployees();
    }
}
