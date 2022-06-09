package at.kocmana.testservices.customerservice.customernetwork.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@NoArgsConstructor
@Data
public class CustomerInteractionId implements Serializable {

  private Integer sourceCustomerId;
  @Enumerated(EnumType.STRING)
  private InteractionType interactionType;
  private Integer targetCustomerId;
}
