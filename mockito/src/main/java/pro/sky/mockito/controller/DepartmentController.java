package pro.sky.mockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.mockito.model.Employee;
import pro.sky.mockito.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/min-salary")
    public double getMin(int departmentId) {
        return departmentService.getEmpWithMinSalary(departmentId);
    }

    @GetMapping("/max-salary")
    public double getMax(int departmentId) {
        return departmentService.getEmpWithMaxSalary(departmentId);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> getAll(int departmentId) {
        return departmentService.getAll(departmentId);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }


}
