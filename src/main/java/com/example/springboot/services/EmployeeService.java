package com.example.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.repositories.EmployeeRepository;
import com.example.springboot.entities.Employee;
import com.example.springboot.exceptions.ResourceNotFoundException;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	public List<Employee> findAll() {
		return this.employeeRepo.findAll();
	}
	
	public Employee findById(int empId) throws ResourceNotFoundException {
		Employee employee = this.employeeRepo.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for id :: " + empId)
				);
		return employee;
	}
	
	public Employee createOne(Employee emp) {
		return this.employeeRepo.save(emp);
	}
	
	public Employee updateOne(int empId, Employee empDetails) throws ResourceNotFoundException{
						
		Optional<Employee> emp = this.employeeRepo.findById(empId); // isPresent() true or false
		
		if(!emp.isPresent()) {
			throw new ResourceNotFoundException("Employee not found for id :: " + empId);
		}
		
		Employee employee = emp.get();
		employee.setFirstname(empDetails.getFirstname());
		employee.setLastname(empDetails.getLastname());
		employee.setEmail(empDetails.getEmail());
		
		return this.employeeRepo.save(employee);
	}
	
	public Employee deleteOne(int empId) throws ResourceNotFoundException{
		Optional<Employee> emp = this.employeeRepo.findById(empId);
		
		if(!emp.isPresent()) {
			throw new ResourceNotFoundException("Employee not found for id :: " + empId);
		}

		this.employeeRepo.delete(emp.get());
		return emp.get();		
	}
}
