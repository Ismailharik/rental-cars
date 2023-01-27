package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class ProductsSaleMlutiDTO {
    private String name;
    private List<ProductsSaleDTO> series = new ArrayList<ProductsSaleDTO>();
}