package ro.msg.learning.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.controller.OrdersController;
import ro.msg.learning.shop.dto.OrderDetailDTO;
import ro.msg.learning.shop.dto.OrdersDTO;
import ro.msg.learning.shop.exception.InsufficientStockException;
import ro.msg.learning.shop.exception.ProductNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopApplicationTests {
	@Autowired
	private OrdersController ordersController;

	@Test
	public void createOrder(){
		List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
		orderDetailDTOList.add(new OrderDetailDTO(null, 2, 10));
		orderDetailDTOList.add(new OrderDetailDTO(null, 37, 15));
		OrdersDTO dto = new OrdersDTO("Romania", "Zalau", "Salaj", "Str.Bradet nr.12", LocalDateTime.now(), orderDetailDTOList);
		assertFalse(ordersController.insert(dto).getBody().isEmpty());
	}

	@Test(expected = InsufficientStockException.class)
	public void missingStock(){
		List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
		orderDetailDTOList.add(new OrderDetailDTO(null, 2, 10));
		orderDetailDTOList.add(new OrderDetailDTO(null, 37, 1500));
		OrdersDTO dto = new OrdersDTO("Romania", "Zalau", "Salaj", "Str.Bradet nr.12", LocalDateTime.now(), orderDetailDTOList);
		ordersController.insert(dto);
	}

	@Test(expected = ProductNotFoundException.class)
	public void missingItem(){
		List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
		orderDetailDTOList.add(new OrderDetailDTO(null, 2, 10));
		orderDetailDTOList.add(new OrderDetailDTO(null, 370, 15));
		OrdersDTO dto = new OrdersDTO("Romania", "Zalau", "Salaj", "Str.Bradet nr.12", LocalDateTime.now(), orderDetailDTOList);
		ordersController.insert(dto);
	}
}
