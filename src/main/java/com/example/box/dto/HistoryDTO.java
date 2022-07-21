package com.example.box.dto;

import com.example.box.domain.History;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoryDTO {

    private int quantity;

    private History.Type type;

    private int productId;

    private int productOptionId;
}
