package com.example.box.mapper;

import com.example.box.domain.Product;
import com.example.box.domain.ProductOption;
import com.example.box.dto.ProductOptionDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface ProductOptionMapper {

    ProductOptionMapper INSTANCE = Mappers.getMapper( ProductOptionMapper.class );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    List<ProductOption> dtoListMapping(List<ProductOptionDTO> dto);
}
