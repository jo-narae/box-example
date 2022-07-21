package com.example.box.util;

import com.example.box.domain.History;
import com.example.box.repository.ProductOptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@RepositoryEventHandler
public class HistoryEventHandler {

    @Autowired
    ProductOptionRepository productOptionRepository;

    @HandleBeforeCreate
    public void handleHistoryBeforeCreate(History history){
        int finalStock = 0;
        switch (history.getType()){
            case Shipping:
                finalStock = history.getProductOption().getStock()-history.getQuantity();
                if(finalStock < 0){
                    throw new RuntimeException("재고없음");
                }
                break;
            case Receiving:
                finalStock = history.getProductOption().getStock()+history.getQuantity();
                break;
        }
        history.getProductOption().setStock(finalStock);
        productOptionRepository.save(history.getProductOption());

    }
}
