package com.learningpath.rubiconassignment.repoes;

import com.learningpath.rubiconassignment.model.WaterOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<WaterOrder,Long> {

    List<WaterOrder> findByFarmId(String farmId);
}
