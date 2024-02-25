/**
 * 
 */
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Employee;

/**
 * @author deby7
 *12:12:14 am
 *2024
 *demo
 *TODO
 */
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
