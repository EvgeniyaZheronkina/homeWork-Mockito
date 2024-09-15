package pro.sky.mockito.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestComponent;
import pro.sky.mockito.exception.EmployeeNotFoundException;
import pro.sky.mockito.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void init() {
        when(employeeService.getAll())
                .thenReturn(List.of(
                        new Employee("Ivan", "Ivanov", 1, 100000),
                        new Employee("Petr", "Petrov", 2, 150000),
                        new Employee("Ivanna", "Sviridova", 1, 250000),
                        new Employee("Marina", "Ivanova", 2, 200000),
                        new Employee("Daniil", "Carpov", 1, 120000)
                ));
    }

    @Test
    void sum() {
        double actual = departmentService.getEmployeeSalarySum(1);
        assertEquals(470000,actual);
    }

    @Test
    void min() {
        double actual = departmentService.getEmpWithMinSalary(1);
        assertEquals(100000,actual);
    }

    @Test
    void min_negative() {
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmpWithMinSalary(3));
    }

    @Test
    void max() {
        double actual = departmentService.getEmpWithMaxSalary(2);
        assertEquals(200000,actual);
    }

    @Test
    void max_negative() {
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmpWithMaxSalary(3));
    }

    @Test
    void filter() {
        List<Employee> actual = departmentService.getAll(1);
        assertEquals(3,actual.size());
        actual.forEach(employee -> assertEquals(1, employee.getDepartment()));
    }

    @Test
    void filter_negative() {
        List<Employee> actual = departmentService.getAll(3);
        assertTrue(actual.isEmpty());

    }

    @Test
    void grouped() {
        Map<Integer, List<Employee>> actual = departmentService.getAll();
        assertEquals(2, actual.keySet().size());
        for (Map.Entry<Integer, List<Employee>> entry : actual.entrySet()) {
            Integer department = entry.getKey();
            List<Employee> employees = entry.getValue();
            employees.forEach(employee -> assertEquals(department, employee.getDepartment()));
        }
    }

    @Test
    void remove_in_department() {
        departmentService.fireAllInDepartment(1);
        verify(employeeService, times(3)).remove(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
    }


}

