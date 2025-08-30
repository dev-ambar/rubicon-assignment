package com.learningpath.rubiconassignment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.learningpath.rubiconassignment.model.Farm;
import com.learningpath.rubiconassignment.model.WaterOrder;
import com.learningpath.rubiconassignment.repoes.OrderRepository;
import com.learningpath.rubiconassignment.service.FarmService;
import com.learningpath.rubiconassignment.service.WaterOrderService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RubiconAssignmentApplicationTests {
	@Autowired
	private FarmService farmService;
	@Autowired
	private WaterOrderService waterOrderService;
	@Autowired
	private OrderRepository orderRepository;

	@Test
	void contextLoads() {
		assertNotNull(farmService);
		assertNotNull(waterOrderService);
		assertNotNull(orderRepository);
	}

	@Test
	void testFarmDataLoaded() {
		List<Farm> farms = farmService.getAllFarms();
		assertNotNull(farms);
		assertFalse(farms.isEmpty(), "Farm data should be loaded from Data.sql");
	}

	@Test
	void testWaterOrderTableExists() {
		List<WaterOrder> orders = orderRepository.findAll();
		assertNotNull(orders);
	}
}
