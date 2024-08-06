package at.kocmana.testservices.productservice.productinformation.model.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ProductDimension {

  private float width;
  private float height;
  private float depth;
}
