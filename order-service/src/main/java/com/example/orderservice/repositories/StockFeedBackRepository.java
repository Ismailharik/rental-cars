package com.example.orderservice.repositories;

import com.example.orderservice.entities.StockFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockFeedBackRepository extends JpaRepository<StockFeedback,Long> {

    @Query(value = "SELECT * FROM `stock_feedback` GROUP BY DATE  ",nativeQuery = true)
     List<StockFeedback> getAllOrdersByDate() ;
    @Query(value = "SELECT * FROM stock_feedback ORDER BY DATE DESC LIMIT 1",nativeQuery = true)
    StockFeedback findLastRegistration();
    // to get stock feed back of the actual month
    @Query(value = "SELECT SUM(total_price) total_price FROM `stock_feedback` GROUP BY MONTH( DATE )  DESC LIMIT 1",nativeQuery = true)
    float totalPriceByLastMonth();
    // to get stock feed back of the actual year
    @Query(value = "SELECT SUM(total_price) total_price  FROM `stock_feedback` GROUP BY YEAR( DATE )  DESC LIMIT 1 ;",nativeQuery = true)
    float totalPriceByLastYear();

}
