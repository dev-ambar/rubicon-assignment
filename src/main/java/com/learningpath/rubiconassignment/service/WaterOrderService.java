package com.learningpath.rubiconassignment.service;

import com.learningpath.rubiconassignment.model.WaterOrder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface WaterOrderService {

    WaterOrder save(WaterOrder wo);
    List<WaterOrder> getWaterOrderList(String farmId);
    WaterOrder getWaterOrder(Long orderId);
}
