package com.application.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.model.Employee;
import com.application.model.FullTimeEmployee;
import com.application.repo.EmployeeRepository;
import com.application.service.EmployeeService;

import static org.mockito.Mockito.*;

import java.util.Optional;

@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new FullTimeEmployee();
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
    }

 // Success scenario for creating an employee
    @Test
    public void testCreateEmployeeSuccess() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee createdEmployee = employeeService.createEmployee(employee);
        Assertions.assertEquals("John Doe", createdEmployee.getName());
        Assertions.assertEquals("john.doe@example.com", createdEmployee.getEmail());
    }
 // Failure scenario for creating an employee
    @Test
    public void testCreateEmployeeFailure() {
        when(employeeRepository.save(any(Employee.class))).thenThrow(new RuntimeException("Failed to create employee"));

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.createEmployee(employee);
        });

        Assertions.assertEquals("Failed to create employee", exception.getMessage());
    }


 // Success scenario for updating an employee
    @Test
    public void testUpdateEmployeeSuccess() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee updatedEmployee = employeeService.updateEmployee(1L, employee);
        Assertions.assertEquals("John Doe", updatedEmployee.getName());
    }
    
    @Test
    public void testUpdateEmployeeFailure() {
        // Simulate the employee not being found in the repository
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that a RuntimeException is thrown with the correct message
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.updateEmployee(1L, employee);
        });

        // Check if the exception message matches the expected message
        Assertions.assertEquals("Employee not found with ID: 1", exception.getMessage());
    }

    
 // Success scenario for deleting an employee
    @Test
    public void testDeleteEmployeeSuccess() {
        doNothing().when(employeeRepository).deleteById(anyLong());

        employeeService.deleteEmployee(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }
    
    // Failure scenario for deleting an employee (employee not found)
    @Test
    public void testDeleteEmployeeFailure() {
        doThrow(new RuntimeException("Failed to delete employee")).when(employeeRepository).deleteById(1L);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.deleteEmployee(1L);
        });

        Assertions.assertEquals("Failed to delete employee", exception.getMessage());
    }
}

