package com.example.orderservice.services;

import com.example.orderservice.entities.StockFeedback;
import com.example.orderservice.utilities.StockInfo;

import java.util.List;

public interface StockService {
    public List<StockFeedback> getStockFeedBack();
    float totalPriceByLastMonth();
    float totalPriceByLastYear();
    long totalClient();
    int newOrders();

    StockInfo getStockInfo();
}