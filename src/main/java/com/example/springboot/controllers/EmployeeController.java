package com.example.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entities.Employee;
import com.example.springboot.exceptions.ResourceNotFoundException;
import com.example.springboot.services.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<List<Employee>> getAll() {
		List<Employee> employeeList = this.empService.findAll();
		return ResponseEntity.ok(employeeList);
	}
	
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Employee> getById(@PathVariable(value = "id") int empId) throws ResourceNotFoundException{
		Employee employee = this.empService.findById(empId);
		return ResponseEntity.ok(employee);
	}
	
	@RequestMapping(value = "/employees", method = RequestMethod.POST, produces = {"application/json"})
	public ResponseEntity<Employee> create(@Validated @RequestBody Employee emp) {
		Employee employee = this.empService.createOne(emp);
		return ResponseEntity.ok(employee);
	}
	
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT, produces = {"application/json"})
	public ResponseEntity<Employee> update(@PathVariable(value = "id") int empId, @Validated @RequestBody Employee empDetails) throws ResourceNotFoundException {
		Employee employee = this.empService.updateOne(empId, empDetails);
		return ResponseEntity.ok(employee);
	}
	
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
	public ResponseEntity<Employee> delete(@PathVariable(value = "id") int empId) throws ResourceNotFoundException {
		return ResponseEntity.ok(this.empService.deleteOne(empId));
	}	
}
