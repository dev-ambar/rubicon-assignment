package com.learningpath.rubiconassignment.controller;

import com.learningpath.rubiconassignment.model.WaterOrder;
import com.learningpath.rubiconassignment.service.WaterOrderService;
import com.learningpath.rubiconassignment.utils.WaterOrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("waterorderapi")
public class WaterOrderController {

    @Autowired
    private WaterOrderService waterOrderService;
    @GetMapping("/orders/{farmId}")
    public ResponseEntity getAllOrders(@PathVariable String farmId)
    {
         ResponseEntity responseEntity ;
        List<WaterOrder> list =  waterOrderService.getWaterOrderList(farmId);
        if(!list.isEmpty())
            responseEntity = new ResponseEntity(list, HttpStatus.FOUND);
        else
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        return responseEntity;
    }
    @PostMapping("/orders")
    public ResponseEntity createWaterOrder(@RequestBody WaterOrder waterOrder)
    {
        if(WaterOrderUtils.isPastDateTime(waterOrder))
            return  new ResponseEntity("can not create order in past date & time",HttpStatus.EXPECTATION_FAILED);

        WaterOrder wo =  waterOrderService.save(waterOrder);
         if(wo !=null && ("created").equalsIgnoreCase(wo.getStatus()))
          return new ResponseEntity(wo, HttpStatus.CREATED);
          else if(wo !=null && ("Overlapped").equalsIgnoreCase(wo.getStatus()))
              return new ResponseEntity("This order is overlapped with this existing order::>>"+wo.toString(), HttpStatus.CONFLICT);
          else
              return  new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }

}
