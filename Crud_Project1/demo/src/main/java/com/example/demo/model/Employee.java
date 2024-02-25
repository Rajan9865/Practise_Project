/**
 * 
 */
package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author deby7
 *11:51:32 pm
 *2024
 *demo
 *TODO
 */
@Data
//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name",nullable = false)
	private String name;
	
	@Column(name = " last_name")
	private String lastname;
	
	@Column(name = "email")
	private String email;

}
