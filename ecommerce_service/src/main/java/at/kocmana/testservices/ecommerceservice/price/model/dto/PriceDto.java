package at.kocmana.testservices.ecommerceservice.price.model.dto;

import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class PriceDto {

  @NotNull
  Integer productId;
  @NotNull
  Float value;
  @NotNull @Length(min = 1, max = 20)
  String currency;
  @NotNull
  String validFrom;
  @NotNull
  String validTo;
}
