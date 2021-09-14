package at.kocmana.testservices.ecommerceservice.price.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "price")
@IdClass(PriceId.class)
@Data
@NoArgsConstructor
public class Price {

  @Id
  @Column(name = "product_id")
  private Integer productId;
  private Float value;
  private String currency;
  @Id
  @Column(name = "valid_from")
  private LocalDateTime validFrom;
  @Id
  @Column(name = "valid_to")
  private LocalDateTime validTo;
}
