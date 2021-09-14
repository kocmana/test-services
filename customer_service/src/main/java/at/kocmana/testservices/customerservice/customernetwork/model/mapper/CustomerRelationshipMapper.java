package at.kocmana.testservices.customerservice.customernetwork.model.mapper;

import at.kocmana.testservices.customerservice.customernetwork.model.domain.CustomerInteraction;
import at.kocmana.testservices.customerservice.customernetwork.model.domain.CustomerNetwork;
import at.kocmana.testservices.customerservice.customernetwork.model.dto.CustomerInteractionDto;
import at.kocmana.testservices.customerservice.customernetwork.model.dto.CustomerNetworkDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerRelationshipMapper {

  //DTO to Domain Objects
  CustomerNetworkDto toCustomerNetworkDto(CustomerNetwork customerNetwork);

  CustomerInteractionDto toCustomerInteractionDto(CustomerInteraction customerInteraction);

  CustomerInteraction toCustomerInteraction(CustomerInteractionDto customerInteractionDto);

}
