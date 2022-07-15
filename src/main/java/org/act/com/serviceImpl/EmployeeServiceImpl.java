package org.act.com.serviceImpl;
import org.act.com.exception.ResourceNotFoundException;
import org.act.com.model.Employee;
import org.act.com.repository.EmployeeRepository;
import org.act.com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository)
    {
        super();
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getALLEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
      Optional<Employee> employee = employeeRepository.findById(id);
      if (employee.isPresent()){
          return employee.get();
      }else {
          throw new ResourceNotFoundException("Employee","Id",id);
      }

      }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        //we need to check whether employee within given id is exist in DB or not
        Employee existingemployee1 = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee","Id",id));
                existingemployee1.setFirstName(employee.getFirstName());
                existingemployee1.setLastName(employee.getLastName());
                existingemployee1.setEmail(employee.getEmail());
                //save existing emplyee to Db
        employeeRepository.save(existingemployee1);
        return existingemployee1;
    }

    @Override
    public void deleteEmployee(Long id) {
        //check whether a employee exist in a DB or not
        employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","Id",id));
        employeeRepository.deleteById(id);

    }
}


