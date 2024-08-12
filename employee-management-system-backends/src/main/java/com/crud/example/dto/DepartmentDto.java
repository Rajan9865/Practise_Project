/**
 * 
 */
package com.crud.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * com.crud.example.dto DelL
 * @author Rajan kumar
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

	private Long id;

	private String department;

	private String departmentDescription;

}
