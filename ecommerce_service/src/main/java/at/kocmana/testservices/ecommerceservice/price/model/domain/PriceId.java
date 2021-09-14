package at.kocmana.testservices.ecommerceservice.price.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PriceId implements Serializable {

  private Integer productId;
  private LocalDateTime validFrom;
  private LocalDateTime validTo;
}
