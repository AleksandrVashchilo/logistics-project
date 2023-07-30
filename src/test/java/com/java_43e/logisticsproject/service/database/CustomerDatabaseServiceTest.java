package com.java_43e.logisticsproject.service.database;
import com.java_43e.logisticsproject.entity.Customer;
import com.java_43e.logisticsproject.repository.CustomerRepository;
import com.java_43e.logisticsproject.service.database.CustomerDatabaseService;
import com.java_43e.logisticsproject.service.impl.CustomerDatabaseServiceImpl;
import com.java_43e.logisticsproject.service.impl.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
public class CustomerDatabaseServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerDatabaseServiceImpl customerDatabaseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCustomer() {
        List<Customer> mockCustomerList = new ArrayList<>();
        mockCustomerList.add(new Customer());
        mockCustomerList.add(new Customer());

        when(customerRepository.findAll()).thenReturn(mockCustomerList);

        List<Customer> result = customerDatabaseService.getCustomer();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById_ValidId() {
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(1);

        when(customerRepository.findById(1)).thenReturn(Optional.of(mockCustomer));

        Customer result = customerDatabaseService.findById(1);

        assertEquals(mockCustomer, result);
    }

    @Test
    public void testFindById_InvalidId() {
        when(customerRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> customerDatabaseService.findById(100));
    }

    @Test
    public void testSaveOrUpdateCustomer() {
        Customer customerToSave = new Customer();
        // Здесь можно задать необходимые значения для объекта customerToSave

        when(customerRepository.save(any(Customer.class))).thenReturn(customerToSave);

        Customer result = customerDatabaseService.saveOrUpdateCustomer(customerToSave);

        assertNotNull(result);
    }
}
