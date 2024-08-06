package at.kocmana.testservices.customerservice.customernetwork.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_interaction")
@IdClass(CustomerInteractionId.class)
@Data
@NoArgsConstructor
public class CustomerInteraction {

  @Id
  @Column(name = "source_customer_id")
  private Integer sourceCustomerId;
  @Id
  @Column(name = "interaction_type")
  @Enumerated(EnumType.STRING)
  private InteractionType interactionType;
  @Id
  @Column(name = "target_customer_id")
  private Integer targetCustomerId;

}
