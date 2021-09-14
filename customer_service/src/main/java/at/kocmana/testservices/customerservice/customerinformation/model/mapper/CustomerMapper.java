package at.kocmana.testservices.customerservice.customerinformation.model.mapper;

import at.kocmana.testservices.customerservice.customerinformation.model.domain.Customer;
import at.kocmana.testservices.customerservice.customerinformation.model.dto.CustomerRegistrationRequest;
import at.kocmana.testservices.customerservice.customerinformation.model.dto.CustomerResponse;
import at.kocmana.testservices.customerservice.customerinformation.model.dto.CustomerUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  CustomerResponse toCustomerResponse(Customer customer);

  @Mapping(target = "customerId", ignore = true)
  Customer toCustomer(CustomerRegistrationRequest customerRegistrationRequest);

  Customer toCustomer(CustomerUpdateRequest customerUpdateRequest);

}
