package com.example.box.controller;

import com.example.box.domain.History;
import com.example.box.domain.Product;
import com.example.box.domain.ProductOption;
import com.example.box.dto.HistoryDTO;
import com.example.box.dto.ProductDTO;
import com.example.box.mapper.HistoryMapper;
import com.example.box.mapper.ProductMapper;
import com.example.box.mapper.ProductOptionMapper;
import com.example.box.service.HistoryService;
import com.example.box.service.ProductOptionService;
import com.example.box.service.ProductService;
import com.example.box.util.CustomPageRequest;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/histories")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @Autowired
    ProductOptionService productOptionService;

    @GetMapping
    public Page<History> list(@RequestParam(required = false) String type,
                              final CustomPageRequest pageable) {
        return historyService.findHistoryList(type, pageable.of());
    }

    @GetMapping("/{id}")
    public History getHistory(@PathVariable("id") int id) {
        return historyService.findById(id);
    }

    @PostMapping
    public void insertHistory(@RequestBody List<HistoryDTO> dto) {
        HistoryMapper mapper = Mappers.getMapper(HistoryMapper.class);

        List<History> history = new ArrayList<>();
        for(HistoryDTO element : dto) {
            history.add(mapper.mappingHistory(element));
        }

        historyService.saveHistory(history);
    }
}
