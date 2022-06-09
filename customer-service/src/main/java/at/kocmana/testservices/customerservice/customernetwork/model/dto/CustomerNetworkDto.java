package at.kocmana.testservices.customerservice.customernetwork.model.dto;

import at.kocmana.testservices.customerservice.customerinformation.model.domain.Customer;
import at.kocmana.testservices.customerservice.customernetwork.model.domain.InteractionType;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CustomerNetworkDto {

  InteractionType interactionType;
  List<Customer> targetCustomer;
}
