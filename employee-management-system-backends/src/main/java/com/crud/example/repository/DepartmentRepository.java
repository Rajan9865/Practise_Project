/**
 * 
 */
package com.crud.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.example.entity.Department;

/**
 * com.crud.example.repository DelL
 * @author Rajan kumar
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
