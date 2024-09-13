package pro.sky.mockito.service;

import pro.sky.mockito.exception.EmployeeAlreadyAddedException;
import pro.sky.mockito.exception.EmployeeNotFoundException;
import pro.sky.mockito.exception.EmployeeStorageIsFullException;
import pro.sky.mockito.model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService{
    private static final int SIZE_LIMIT = 5;
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>(SIZE_LIMIT);
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }


    public void add(Employee employee) {

    }

    public Employee add (String firstName, String lastName) {
        Employee employee = employees.get(createKey(firstName, lastName));
        if (employees.size() >= SIZE_LIMIT) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(createKey(employee))){
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(employee), employee);
        return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(createKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Employee add(String firstName, String lastName, int department, int salary) {
        return null;
    }

    public Employee remove (String firstName, String lastName) {
        return employees.remove(createKey(firstName, lastName));
    }

    public static String createKey(Employee employee) {
        return createKey(employee.getFirstName(), employee.getLastName());
    }

    public static String createKey(String firstName, String lastName) {
        return (firstName + lastName).toLowerCase();
    }
}
