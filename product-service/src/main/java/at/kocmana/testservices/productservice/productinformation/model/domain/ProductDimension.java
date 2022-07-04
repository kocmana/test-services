package at.kocmana.testservices.productservice.productinformation.model.domain;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDimension {

  private float width;
  private float height;
  private float depth;
}
