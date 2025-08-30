// ...existing code...
package com.learningpath.rubiconassignment.controller;

import com.learningpath.rubiconassignment.model.WaterOrder;
import com.learningpath.rubiconassignment.model.Farm;
import com.learningpath.rubiconassignment.service.WaterOrderService;
import com.learningpath.rubiconassignment.service.FarmService;
import com.learningpath.rubiconassignment.utils.WaterOrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("waterorderapi")
public class WaterOrderController {
    @GetMapping({"/", ""})
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Rubicon Assignment API is running. Available endpoints: /waterorderapi/farms, /waterorderapi/orders/{farmId}, /waterorderapi/orders");
    }

    @Autowired
    private WaterOrderService waterOrderService;

    @Autowired
    private FarmService farmService;
    @GetMapping("/orders/{farmId}")
    public ResponseEntity<List<WaterOrder>> getAllOrders(@PathVariable String farmId) {
        List<WaterOrder> list = waterOrderService.getWaterOrderList(farmId);
        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        return ResponseEntity.ok(list);
    }
    @PostMapping("/orders")
    public ResponseEntity<?> createWaterOrder(@RequestBody WaterOrder waterOrder) {
        if (WaterOrderUtils.isPastDateTime(waterOrder)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiError("Cannot create order in past date & time"));
        }

        WaterOrder wo = waterOrderService.save(waterOrder);
        if (wo != null && "created".equalsIgnoreCase(wo.getStatus())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(wo);
        } else if (wo != null && "Overlapped".equalsIgnoreCase(wo.getStatus())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiError("This order is overlapped with this existing order: " + wo.toString()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiError("Failed to create water order"));
        }
    }

    @GetMapping("/farms")
    public ResponseEntity<List<Farm>> getAllFarms() {
        List<Farm> farms = farmService.getAllFarms();
        if (farms.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(farms);
    }
// Helper class for structured error responses
class ApiError {
    private String message;

    public ApiError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

}
