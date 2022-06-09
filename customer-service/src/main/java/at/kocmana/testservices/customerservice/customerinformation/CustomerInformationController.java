package at.kocmana.testservices.customerservice.customerinformation;

import at.kocmana.testservice.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation;
import at.kocmana.testservices.customerservice.customerinformation.model.domain.Customer;
import at.kocmana.testservices.customerservice.customerinformation.model.dto.CustomerRegistrationRequest;
import at.kocmana.testservices.customerservice.customerinformation.model.dto.CustomerResponse;
import at.kocmana.testservices.customerservice.customerinformation.model.dto.CustomerUpdateRequest;
import at.kocmana.testservices.customerservice.customerinformation.model.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@RestController
@RequestMapping("/customer")
class CustomerInformationController {

  private final CustomerInformationService customerInformationService;
  private final CustomerMapper customerMapper;

  @Autowired
  CustomerInformationController(CustomerInformationService customerInformationService, CustomerMapper customerMapper) {
    this.customerInformationService = customerInformationService;
    this.customerMapper = customerMapper;
  }

  @GetMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 30, standardDeviation = 5)
  public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
    List<Customer> allCustomers = customerInformationService.retrieveAllCustomers();
    List<CustomerResponse> customerResponses = allCustomers.stream()
        .map(customerMapper::toCustomerResponse)
        .collect(toUnmodifiableList());
    return ResponseEntity.ok(customerResponses);
  }

  @GetMapping(path = "/{customerId}")
  @NormallyDistributedEndpointDelaySimulation(mean = 10, standardDeviation = 5)
  public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable int customerId) {
    Customer customer = customerInformationService.retrieveCustomerById(customerId);
    CustomerResponse customerResponse = customerMapper.toCustomerResponse(customer);
    return ResponseEntity.ok(customerResponse);
  }

  @PostMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 20, standardDeviation = 10)
  public ResponseEntity<CustomerResponse> saveCustomer(
      @RequestBody @Valid CustomerRegistrationRequest customerRegistrationRequest) {
    Customer customer = customerMapper.toCustomer(customerRegistrationRequest);
    Customer savedCustomer = customerInformationService.saveCustomer(customer);
    CustomerResponse customerResponse = customerMapper.toCustomerResponse(savedCustomer);
    return ResponseEntity.ok(customerResponse);
  }

  @PutMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 20, standardDeviation = 10)
  public void updateCustomer(@RequestBody @Valid CustomerUpdateRequest customerUpdateRequest) {
    Customer customer = customerMapper.toCustomer(customerUpdateRequest);
    customerInformationService.updateCustomer(customer);
  }

  @DeleteMapping(path = "/{customerId}")
  @NormallyDistributedEndpointDelaySimulation(mean = 15, standardDeviation = 10)
  public void deleteCustomer(@PathVariable int customerId) {
    customerInformationService.deleteCustomer(customerId);
  }

}
