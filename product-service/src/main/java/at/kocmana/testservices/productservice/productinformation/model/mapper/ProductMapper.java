package at.kocmana.testservices.productservice.productinformation.model.mapper;

import at.kocmana.testservices.productservice.productinformation.model.domain.Product;
import at.kocmana.testservices.productservice.productinformation.model.dto.ProductCreationRequest;
import at.kocmana.testservices.productservice.productinformation.model.dto.ProductResponse;
import at.kocmana.testservices.productservice.productinformation.model.dto.ProductUpdateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductResponse productToProductResponse(Product product);

  Product productCreationRequestToProduct(ProductCreationRequest productCreationRequest);

  Product productUpdateRequestToProduct(ProductUpdateRequest productUpdateRequest);
}
