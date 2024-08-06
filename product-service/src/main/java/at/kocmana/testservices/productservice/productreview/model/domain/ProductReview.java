package at.kocmana.testservices.productservice.productreview.model.domain;

import at.kocmana.testservices.productservice.productinformation.model.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_review")
@Getter
@Setter
@NoArgsConstructor
public class ProductReview {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int customerId;
  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;
  private int stars;
  private String review;
}
