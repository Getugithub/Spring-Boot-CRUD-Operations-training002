package org.act.com.service;

import org.act.com.model.Employee;

import java.util.List;

public interface EmployeeService {
Employee saveEmployee(Employee employee);
List<Employee> getALLEmployees();
Employee getEmployeeById(Long id);
Employee updateEmployee(Employee employee ,Long id);
void deleteEmployee(Long id);
}
