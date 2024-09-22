/**
 * 
 */
package com.fci.sms.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * com.fci.sms.dto
 * 
 * @author Rajan kumar
 */
@Getter
@Setter
public class OrderDTO {

	@NotNull(message = "Customers id required")
	private Long customerId;
	
	@NotEmpty(message = "Product IDs cannot be empty")
	private List<Long> productIds;
	private Double totalPrice;

}
