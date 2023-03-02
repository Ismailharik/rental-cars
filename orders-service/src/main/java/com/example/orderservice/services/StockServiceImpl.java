package com.example.orderservice.services;

import com.example.orderservice.entities.StockFeedback;
import com.example.orderservice.oepnfeign.CustomerRestClient;
import com.example.orderservice.repositories.ReservationRepository;
import com.example.orderservice.repositories.StockFeedBackRepository;
import com.example.orderservice.utilities.StockInfo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class StockServiceImpl implements StockService {
    private  StockFeedBackRepository stockFeedBackRepository;
    private ReservationRepository reservationRepository;
    private CustomerRestClient customerRestClient;

    @Override
    public List<StockFeedback> getStockFeedBack() {
        List<StockFeedback> stockFeedbacks = stockFeedBackRepository.getAllOrdersByDate();
        return stockFeedbacks;
    }

    @Override
    public float totalPriceByLastMonth() {
        return stockFeedBackRepository.totalPriceByLastMonth();
    }

    @Override
    public float totalPriceByLastYear() {
        return stockFeedBackRepository.totalPriceByLastYear();
    }

    @Override
    public long totalClient() {
       return customerRestClient.getTotalClient();
    }

    @Override
    public int newOrders() {
        return this.reservationRepository.countByIsPaid(false);
    }

    @Override
    public StockInfo getStockInfo() {
        StockInfo stockInfo = new StockInfo();
        stockInfo.setStockFeedbacks(this.getStockFeedBack());
        try {
            stockInfo.setTotalUser(this.customerRestClient.getTotalClient());
        }catch (Exception e){
            System.out.println(e.getMessage());
            stockInfo.setTotalUser(0);
        }

        stockInfo.setLastMonthProfit(this.totalPriceByLastMonth());
        stockInfo.setLastYearProfit(this.totalPriceByLastYear());
        stockInfo.setNewOrders(this.newOrders());
        return stockInfo;
    }
}
