package at.kocmana.testservices.productservice.productinformation.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ProductDimension {

  private float width;
  private float height;
  private float depth;
}
