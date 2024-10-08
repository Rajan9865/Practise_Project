/**
 * 
 */
package com.crud.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * com.crud.example.entity DelL
 * 
 * @author Rajan kumar
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_id", nullable = false, unique = true)
	private String email;
}
