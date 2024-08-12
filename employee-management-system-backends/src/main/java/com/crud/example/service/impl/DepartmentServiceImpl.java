/**
 * 
 */
package com.crud.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.example.dto.DepartmentDto;
import com.crud.example.entity.Department;
import com.crud.example.exception.ResourceNotFoundException;
import com.crud.example.mapper.DepartmentMapper;
import com.crud.example.repository.DepartmentRepository;
import com.crud.example.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

/**
 * com.crud.example.service.impl DelL
 * @author Rajan kumar
 */
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public DepartmentDto createDepartment(DepartmentDto departmentDto) {
		Department department=DepartmentMapper.mapToDepartment(departmentDto);
		Department saveDepartment=departmentRepository.save(department);
		return DepartmentMapper.mapToDepartmentDto(saveDepartment);
	}

	@Override
	public DepartmentDto getDepartmentId(Long departmentId) {
		Department department = departmentRepository.findById(departmentId).orElseThrow(()->
		new ResourceNotFoundException("department is not exist with given id : "+departmentId));
		return DepartmentMapper.mapToDepartmentDto(department);
	}

	@Override
	public List<DepartmentDto> getAllDepartments() {
		List<Department>departments=departmentRepository.findAll();
		return departments.stream().map((department)->DepartmentMapper.mapToDepartmentDto(department))
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updateDepartment) {
		Department department=departmentRepository.findById(departmentId).orElseThrow(
				()->new ResourceNotFoundException("Department is not exist with given id :"+departmentId));
		department.setDepartmentName(updateDepartment.getDepartment());
		department.setDepartmentDescription(updateDepartment.getDepartmentDescription());
		Department saveDepartment = departmentRepository.save(department);
		return DepartmentMapper.mapToDepartmentDto(saveDepartment);
	}

	@Override
	public void deleteDepartment(Long departmentId) {
		departmentRepository.findById(departmentId).orElseThrow(
				()->new ResourceNotFoundException("Department is not exist wit given id :"+departmentId));
		departmentRepository.deleteById(departmentId);
	}

}
