package at.kocmana.testservices.ecommerceservice.price.model.mapper;

import at.kocmana.testservices.ecommerceservice.price.model.domain.Price;
import at.kocmana.testservices.ecommerceservice.price.model.dto.PriceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  PriceDto priceToPriceDto(Price price);

  Price priceDtoToPrice(PriceDto priceDto);

}
