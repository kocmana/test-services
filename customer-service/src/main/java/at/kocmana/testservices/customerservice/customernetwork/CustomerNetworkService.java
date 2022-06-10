package at.kocmana.testservices.customerservice.customernetwork;

import at.kocmana.testservices.customerservice.customerinformation.CustomerInformationService;
import at.kocmana.testservices.customerservice.customerinformation.model.domain.Customer;
import at.kocmana.testservices.customerservice.customernetwork.model.domain.CustomerInteraction;
import at.kocmana.testservices.customerservice.customernetwork.model.domain.CustomerNetwork;
import at.kocmana.testservices.customerservice.customernetwork.model.domain.InteractionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

@Service
class CustomerNetworkService {

  private final CustomerNetworkRepository customerNetworkRepository;
  private final CustomerInformationService customerInformationService;

  @Autowired
  public CustomerNetworkService(
      CustomerNetworkRepository customerNetworkRepository,
      CustomerInformationService customerInformationService) {
    this.customerNetworkRepository = customerNetworkRepository;
    this.customerInformationService = customerInformationService;
  }

  CustomerInteraction saveCustomerRelationship(CustomerInteraction customerInteraction) {
    return customerNetworkRepository.save(customerInteraction);
  }

  List<CustomerNetwork> getCustomerNetworksForCustomer(Integer customerId) {
    var network = customerNetworkRepository
        .findBySourceCustomerId(customerId);

    var targetCustomerIds = extractTargetCustomerIds(network);
    var targetCustomersByCustomerId = retrieveCustomerInformationForTargetCustomers(
        targetCustomerIds);
    var customersByInteractionType =
        groupCustomersPerRelationshipType(network);

    return transformToCustomerNetworks(customersByInteractionType, targetCustomersByCustomerId);
  }

  private List<Integer> extractTargetCustomerIds(List<CustomerInteraction> network) {
    return network.stream()
        .map(CustomerInteraction::getTargetCustomerId)
        .toList();
  }

  private Map<Integer, Customer> retrieveCustomerInformationForTargetCustomers(List<Integer> targetCustomerIds) {
    return customerInformationService.retrieveCustomersByIds(targetCustomerIds).stream()
        .collect(toConcurrentMap(Customer::getCustomerId, Function.identity()));
  }

  private Map<InteractionType, List<CustomerInteraction>> groupCustomersPerRelationshipType(
      List<CustomerInteraction> network) {
    return network.stream()
        .collect(groupingBy(CustomerInteraction::getInteractionType));
  }

  private List<CustomerNetwork> transformToCustomerNetworks(
      Map<InteractionType, List<CustomerInteraction>> customersPerRelationshipType,
      Map<Integer, Customer> targetCustomers) {
    return customersPerRelationshipType.entrySet().stream()
        .map(entry -> CustomerNetwork.builder()
            .interactionType(entry.getKey())
            .targetCustomer(entry.getValue().stream()
                .map(CustomerInteraction::getTargetCustomerId)
                .map(targetCustomers::get)
                .toList())
            .build())
        .toList();
  }

}
