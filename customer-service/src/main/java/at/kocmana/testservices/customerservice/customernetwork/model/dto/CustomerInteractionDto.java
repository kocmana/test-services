package at.kocmana.testservices.customerservice.customernetwork.model.dto;

import at.kocmana.testservices.customerservice.customernetwork.model.domain.InteractionType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomerInteractionDto {

  @NotNull
  Integer sourceCustomerId;
  @NotNull
  InteractionType interactionType;
  @NotNull
  Integer targetCustomerId;
}
