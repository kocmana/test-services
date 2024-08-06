package at.kocmana.testservices.customerservice.customernetwork.model.domain;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class CustomerInteractionId implements Serializable {

  private Integer sourceCustomerId;
  @Enumerated(EnumType.STRING)
  private InteractionType interactionType;
  private Integer targetCustomerId;
}
