package com.example.box.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductOptionDTO {

    private int id;

    private String size;

    private String color;

    private int stock;
}
