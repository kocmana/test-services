package at.kocmana.testservices.ecommerceservice.purchase.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "purchase_item")
@Data
public class PurchaseItem implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @ManyToOne
  @JoinColumn(name="purchase_id", nullable=false)
  private Purchase purchase;
  @Column(name = "product_id")
  private Integer productId;
  private Integer amount;
  @Column(name = "price_per_unit")
  private Float pricePerUnit;
  @Column(name = "currency")
  private String currency;
}
