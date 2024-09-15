package pro.sky.mockito.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.mockito.exception.EmployeeNotFoundException;
import pro.sky.mockito.model.Employee;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceTest {
    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @BeforeEach
    void init() {
        employeeService.add(new Employee("Ivan", "Ivanov", 1, 10000));
        employeeService.add(new Employee("David", "Petrov", 2, 25000));
    }

    @AfterEach
    void clear() {
        employeeService.remove("Ivan", "Ivanov");
        employeeService.remove("David", "Petrov");
    }

    @Test
    void findAll() {
        Collection<Employee> actual = employeeService.getAll();
        assertEquals(2, actual.size());
    }

    @Test
    void find() {
        Employee actual = employeeService.find("Ivan", "Ivanov");
        assertEquals("Ivan", actual.getFirstName());
        assertEquals("Ivanov", actual.getLastName());
    }

    @Test
    void find_negative() {
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.find("Nikolai", "Sergeerv");
        });
    }

    @Test
    void remove (){
        int size = employeeService.getAll().size();
        employeeService.remove("Ivan", "Ivanov");
        assertEquals(size -1, employeeService.getAll().size());
    }
}
