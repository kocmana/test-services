package at.kocmana.testservices.productservice.productinformation.model.mapper;

import at.kocmana.testservices.productservice.productinformation.model.domain.ProductDimension;
import at.kocmana.testservices.productservice.productinformation.model.dto.ProductDimensionDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDimensionMapper {

  ProductDimension productDimensionDtoToProductDimension(ProductDimensionDto productDimensionDto);

  @InheritInverseConfiguration
  ProductDimensionDto productDimensionToProductDimensionDto(ProductDimension productDimension);

}


