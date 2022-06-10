package at.kocmana.testservices.customerservice.customernetwork;

import at.kocmana.testservice.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation;
import at.kocmana.testservices.customerservice.customernetwork.model.dto.CustomerInteractionDto;
import at.kocmana.testservices.customerservice.customernetwork.model.dto.CustomerNetworkDto;
import at.kocmana.testservices.customerservice.customernetwork.model.mapper.CustomerRelationshipMapper;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    var customerNetworks = customerNetworkService.getCustomerNetworksForCustomer(customerId);
    var customerNetworkDtos = customerNetworks.stream()
        .map(customerRelationshipMapper::toCustomerNetworkDto)
        .toList();
    return ResponseEntity.ok(customerNetworkDtos);
  }

  @PostMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 20, standardDeviation = 15)
  public ResponseEntity<CustomerInteractionDto> postNewCustomerRelationship(
      @RequestBody @Valid CustomerInteractionDto relationshipDto) {
    var relationship = customerRelationshipMapper.toCustomerInteraction(relationshipDto);
    relationship = customerNetworkService.saveCustomerRelationship(relationship);
    relationshipDto = customerRelationshipMapper.toCustomerInteractionDto(relationship);
    return ResponseEntity.ok(relationshipDto);
  }

}
