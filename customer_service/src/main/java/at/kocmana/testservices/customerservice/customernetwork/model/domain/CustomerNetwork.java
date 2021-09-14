package at.kocmana.testservices.customerservice.customernetwork.model.domain;

import at.kocmana.testservices.customerservice.customerinformation.model.domain.Customer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerNetwork {

  private InteractionType interactionType;
  private List<Customer> targetCustomer;
}
