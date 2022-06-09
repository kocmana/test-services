package at.kocmana.testservices.customerservice.customernetwork;

import at.kocmana.testservice.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation;
import at.kocmana.testservices.customerservice.customernetwork.model.domain.CustomerInteraction;
import at.kocmana.testservices.customerservice.customernetwork.model.domain.CustomerNetwork;
import at.kocmana.testservices.customerservice.customernetwork.model.dto.CustomerInteractionDto;
import at.kocmana.testservices.customerservice.customernetwork.model.dto.CustomerNetworkDto;
import at.kocmana.testservices.customerservice.customernetwork.model.mapper.CustomerRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/network")
public class CustomerNetworkController {

  private final CustomerNetworkService customerNetworkService;
  private final CustomerRelationshipMapper customerRelationshipMapper;

  @Autowired
  public CustomerNetworkController(
      CustomerNetworkService customerNetworkService,
      CustomerRelationshipMapper customerRelationshipMapper) {
    this.customerNetworkService = customerNetworkService;
    this.customerRelationshipMapper = customerRelationshipMapper;
  }

  @GetMapping("/{customerId}")
  @NormallyDistributedEndpointDelaySimulation(mean = 500, standardDeviation = 250)
  public ResponseEntity<List<CustomerNetworkDto>> getCustomerNetworkForCustomer(@PathVariable int customerId) {
    List<CustomerNetwork> customerNetworks = customerNetworkService.getCustomerNetworksForCustomer(customerId);
    List<CustomerNetworkDto> customerNetworkDtos = customerNetworks.stream()
        .map(customerRelationshipMapper::toCustomerNetworkDto)
        .collect(Collectors.toUnmodifiableList());
    return ResponseEntity.ok(customerNetworkDtos);
  }

  @PostMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 20, standardDeviation = 15)
  public ResponseEntity<CustomerInteractionDto> postNewCustomerRelationship(
      @RequestBody @Valid CustomerInteractionDto relationshipDto) {
    CustomerInteraction relationship = customerRelationshipMapper.toCustomerInteraction(relationshipDto);
    relationship = customerNetworkService.saveCustomerRelationship(relationship);
    relationshipDto = customerRelationshipMapper.toCustomerInteractionDto(relationship);
    return ResponseEntity.ok(relationshipDto);
  }

}
