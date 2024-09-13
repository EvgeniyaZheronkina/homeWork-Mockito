package pro.sky.mockito.service;

import org.springframework.stereotype.Service;
import pro.sky.mockito.exception.EmployeeNotFoundException;
import pro.sky.mockito.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public double getEmployeeSalarySum(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public double getEmpWithMinSalary(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow(EmployeeNotFoundException::new);

    }

    public double getEmpWithMaxSalary(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .max()
                .orElseThrow(EmployeeNotFoundException::new);
    }
    public List<Employee>getAll(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }


    public Map<Integer, List<Employee>> getAll() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
