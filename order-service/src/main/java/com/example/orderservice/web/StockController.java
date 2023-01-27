package com.example.orderservice.web;

import com.example.orderservice.dto.ProductsSaleMlutiDTO;
import com.example.orderservice.entities.StockFeedback;
import com.example.orderservice.services.StockService;
import com.example.orderservice.utilities.StockInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/stockFeedBack")
public class StockController {
    private StockService stockService;
//    @GetMapping("/totalPriceByMonth")
//    public float getTotalPriceByMonth(){
//        return  stockService.totalPriceByLastMonth();
//    }
//
//    @GetMapping("/totalPriceByYear")
//    public  float getTotalPriceByYear(){
//        return  stockService.totalPriceByLastYear();
//    }
//    @GetMapping("")
//    public List<StockFeedback> getStockService() {
//        return stockService.getStockFeedBack();
//    }
//    @GetMapping("/notPaid")
//    public int getOrdersNotPaid(){
//        return this.stockService.newOrders();
//    }
    @GetMapping("")
    public StockInfo getStockInfo(){
        return this.stockService.getStockInfo();
    }
}
