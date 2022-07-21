package com.example.box.mapper;

import com.example.box.domain.History;
import com.example.box.dto.HistoryDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HistoryMapper {

    HistoryMapper INSTANCE = Mappers.getMapper( HistoryMapper.class );

    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "productOption.id", source = "productOptionId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    History mappingHistory(HistoryDTO dto);

    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "productOption.id", source = "productOptionId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    History mappingHistoryToOrigin(HistoryDTO dto, @MappingTarget History history);
}
