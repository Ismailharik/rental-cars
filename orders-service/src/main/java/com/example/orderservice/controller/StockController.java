package com.example.orderservice.controller;

import com.example.orderservice.services.StockService;
import com.example.orderservice.utilities.StockInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/stockFeedBack")

public class StockController {
    private StockService stockService;
    @GetMapping("")
    public StockInfo getStockInfo() {
        return this.stockService.getStockInfo();
    }
}
