/**
 * 
 */
package com.crud.example.service;

import java.util.List;

import com.crud.example.dto.DepartmentDto;

/**
 * com.crud.example.service DelL
 * @author Rajan kumar
 */
public interface DepartmentService {

	// to create department 
	DepartmentDto createDepartment(DepartmentDto departmentDto);
	
	//to get department by id
	DepartmentDto getDepartmentId(Long departmentId);
	
	//to get all department 
	List<DepartmentDto>getAllDepartments();
	
	//update department 
	DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment);
	
	// to delete department
	void deleteDepartment(Long departmentId);
	
}
