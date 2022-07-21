package com.example.box.mapper;

import com.example.box.domain.Product;
import com.example.box.dto.ProductDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "options", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Product mappingProduct(ProductDTO dto, @MappingTarget Product product);

}
