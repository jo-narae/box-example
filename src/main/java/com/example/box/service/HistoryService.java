package com.example.box.service;

import com.example.box.domain.History;
import com.example.box.domain.ProductOption;
import com.example.box.repository.HistoryRepository;
import com.example.box.repository.HistorySpec;
import com.example.box.repository.ProductOptionRepository;
import com.example.box.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    ProductOptionRepository productOptionRepository;

    public Page<History> findHistoryList(String type, PageRequest pageable) {
        Map<String, Object> searchKeys = new HashMap<>();
        if (type != null) searchKeys.put("type", type);

        Specification<History> spec = Specification.where(HistorySpec.searchWith(searchKeys));
        return historyRepository.findAll(spec, pageable);
    }

    public History findById(int id) {
        return historyRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void saveHistory(List<History> history) {
        for (History element : history) {
            int finalStock = 0;
            ProductOption option = productOptionRepository.findById(element.getProductOption().getId()).orElseThrow(NoSuchElementException::new);
            switch (element.getType()) {
                case Shipping:
                    finalStock = option.getStock() - element.getQuantity();
                    if (finalStock < 0) {
                        throw new RuntimeException("재고없음");
                    }
                    break;
                case Receiving:
                    finalStock = option.getStock() + element.getQuantity();
                    break;
            }
            option.setStock(finalStock);
            productOptionRepository.save(option);

            historyRepository.save(element);
        }
    }

    public void deleteHistory(int id) {
        historyRepository.deleteById(id);
    }

}
