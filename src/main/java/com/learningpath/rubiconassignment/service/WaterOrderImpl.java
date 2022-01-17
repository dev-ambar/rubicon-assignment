package com.learningpath.rubiconassignment.service;

import com.learningpath.rubiconassignment.model.WaterOrder;
import com.learningpath.rubiconassignment.repoes.OrderRepository;
import com.learningpath.rubiconassignment.utils.WaterOrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterOrderImpl implements WaterOrderService{

    @Autowired
    private OrderRepository orderRepo;

    @Override
    public WaterOrder save(WaterOrder wo) {

        List<WaterOrder> existWoList = orderRepo.findByFarmId(wo.getFarmId());
        if(existWoList!=null) {
            for(WaterOrder ewo : existWoList)
            {
                if(WaterOrderUtils.isOverlapped(wo,ewo)) {
                    ewo.setStatus("Overlapped");
                    return ewo;
                }
            }
        }
          WaterOrder createdWo = orderRepo.save(wo);
         if(createdWo!=null) {
             createdWo.setStatus("Created");
         }
         else {
             createdWo.setStatus("NotCreated");
         }
        return createdWo;
    }

    @Override
    public List<WaterOrder> getWaterOrderList(String farmId) {

        List<WaterOrder> wOList = orderRepo.findByFarmId(farmId);
          if (!wOList.isEmpty()) {
               wOList.forEach(wo-> wo.setStatus(WaterOrderUtils.calculateOrderStatus(wo)));
               return wOList;
          }
        return null;
    }

    @Override
    public WaterOrder getWaterOrder(Long orderId) {
        return null;
    }
}
