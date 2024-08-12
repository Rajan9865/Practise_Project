/**
 * 
 */
package com.crud.example.mapper;

import com.crud.example.dto.DepartmentDto;
import com.crud.example.entity.Department;

/**
 * com.crud.example.mapper DelL
 * @author Rajan kumar
 */
public class DepartmentMapper {
	//convert department jpa entity into department dto
	public static DepartmentDto mapToDepartmentDto(Department department)
	{
		return new DepartmentDto(
				department.getId(),
				department.getDepartmentName(),
				department.getDepartmentDescription()
				);
	}
	
	//convert department dto  into department jpa entity
		public static Department mapToDepartment(DepartmentDto departmentDto)
		{
			return new Department(
					departmentDto.getId(),
					departmentDto.getDepartment(),
					departmentDto.getDepartmentDescription()
					);
		}
}
