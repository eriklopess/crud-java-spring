package eriklopess.crud.services;

import eriklopess.crud.entities.Employee;
import eriklopess.crud.entities.repositories.EmployeeRepository;
import eriklopess.crud.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        Optional<Employee> employee = repository.findById(id);
        return employee.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        try {
            var employee = repository.findById(id);
            if (employee.isEmpty()) {
                System.out.println("Employee not exists.");
            } else {
                repository.deleteById(id);
            }
        } catch(RuntimeException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    private Employee updateData(Employee employee, Integer id) {
        var oldEmployeeData = getEmployeeById(id);
        oldEmployeeData.setName(employee.getName());
        oldEmployeeData.setRole(employee.getRole());
        oldEmployeeData.setSalary(employee.getSalary());
        return oldEmployeeData;
    }

    public Employee updateEmployee(Employee employee, Integer id) {
        var newEmployeeData = updateData(employee, id);
        return repository.save(newEmployeeData);
    }
}
