package com.example.box.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

    String name;

    int purchasePrice;

    int salesPrice;

    Date createDate;

    List<ProductOptionDTO> options;
}
