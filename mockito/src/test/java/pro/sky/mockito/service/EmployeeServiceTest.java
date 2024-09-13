package pro.sky.mockito.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.mockito.model.Employee;

import java.util.Collection;

public class EmployeeServiceTest {
    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Test
    void findAll() {
        employeeService.add(new Employee("Ivan", "Ivanov", 1, 10000));
        employeeService.add(new Employee("David", "Petrov", 2, 25000));

        Collection<Employee> actual = employeeService.getAll();
        Assertions.assertEquals(2, actual.size());
    }

}
