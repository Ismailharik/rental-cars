package com.example.orderservice.utilities;

import com.example.orderservice.entities.StockFeedback;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInfo {
    private List<StockFeedback> stockFeedbacks;
    private long totalUser;
    private float lastYearProfit;
    private float lastMonthProfit;
    private int newOrders;
}
