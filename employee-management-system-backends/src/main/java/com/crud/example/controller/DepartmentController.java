/**
 * 
 */
package com.crud.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.example.dto.DepartmentDto;
import com.crud.example.service.DepartmentService;


/**
 * com.crud.example.controller DelL
 * @author Rajan kumar
 */
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	// create a department
	@PostMapping
	public ResponseEntity<DepartmentDto> createDepart(@RequestBody DepartmentDto departmentDto)
	{
		DepartmentDto departmentDto2=departmentService.createDepartment(departmentDto);
		return new ResponseEntity<DepartmentDto>(departmentDto2,HttpStatus.CREATED);
	}
	
	// get a  single department 
	@GetMapping("{id}")
	public  ResponseEntity<DepartmentDto>getDepartmentById(@PathVariable("id") Long departmentId)
	{
		DepartmentDto departmentDto=departmentService.getDepartmentId(departmentId);
		return ResponseEntity.ok(departmentDto);
	}
	
	// get all department 
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAllDepartments()
	{
		List<DepartmentDto>departments=departmentService.getAllDepartments();
		return ResponseEntity.ok(departments);
	}
	
	// update the department
	@PutMapping("{id}")
	public ResponseEntity<DepartmentDto>updateDepartment(@PathVariable("id") Long departmentId
			,@RequestBody DepartmentDto updateDepartment)
	{
		DepartmentDto departmentDto=departmentService.updateDepartment(departmentId, updateDepartment);
		return ResponseEntity.ok(departmentDto);
	}
	
	// delete the department 
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long deparmentId)
	{
		departmentService.deleteDepartment(deparmentId);
		return ResponseEntity.ok("Department deleted successfully");
	}
	
	
	
	
	
	
	
}
