/**
 * 
 */
package com.crud.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.example.entity.Employee;

/**
 * com.crud.example.repository DelL
 * @author Rajan kumar
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
