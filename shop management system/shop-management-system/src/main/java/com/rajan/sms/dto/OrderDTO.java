/**
 * 
 */
package com.rajan.sms.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.rajan.sms.entity.Customer;
import com.rajan.sms.entity.Product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class OrderDTO {

	@NotNull(message = "Customer ID required")
	private Long customerId;

	@NotEmpty(message = "Product IDs cannot be empty")
	private List<Long> productIds;

	@NotEmpty(message = "Quantities cannot be empty")
	private List<Integer> quantities;

	private Double totalPrice;

	private LocalDateTime orderDate;
	  public OrderDTO(List<Long> productIds, Long customerId, List<Integer> quantities, LocalDateTime orderDate, Double totalPrice) {
	        this.productIds = productIds;
	        this.customerId = customerId;
	        this.quantities = quantities;
	        this.orderDate = orderDate;
	        this.totalPrice = totalPrice;
	    }
}
