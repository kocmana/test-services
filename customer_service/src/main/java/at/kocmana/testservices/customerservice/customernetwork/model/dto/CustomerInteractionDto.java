package at.kocmana.testservices.customerservice.customernetwork.model.dto;

import at.kocmana.testservices.customerservice.customernetwork.model.domain.InteractionType;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

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
