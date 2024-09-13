package pro.sky.mockito.service;

import pro.sky.mockito.exception.EmployeeAlreadyAddedException;
import pro.sky.mockito.exception.EmployeeNotFoundException;
import pro.sky.mockito.exception.EmployeeStorageIsFullException;
import pro.sky.mockito.model.Employee;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface EmployeeService {
    Employee add (String firstName, String lastName, int department, int salary);

    Employee remove (String firstName, String lastName);

    Employee find (String firstName, String lastName);

    Collection<Employee> getAll();



}
