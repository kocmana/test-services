package at.kocmana.testservices.ecommerceservice.purchase.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "purchase")
@Data
public class Purchase implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "customer_id")
  private Integer customerId;
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "purchase_id")
  private List<PurchaseItem> items;
  @Column(name = "payment_type")
  @Enumerated(value = EnumType.STRING)
  private PaymentType paymentType;
}
