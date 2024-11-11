/**
 * 
 */
package com.rajan.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * com.rajan.sms.dto
 * 
 * @author Rajan kumar
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

	private String name;
	private String email;
	private String address;
}
