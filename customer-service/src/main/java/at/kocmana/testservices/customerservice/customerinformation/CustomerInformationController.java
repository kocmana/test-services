package at.kocmana.testservices.customerservice.customerinformation;

import at.kocmana.testservice.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation;
import at.kocmana.testservices.customerservice.customerinformation.model.dto.CustomerRegistrationRequest;
import at.kocmana.testservices.customerservice.customerinformation.model.dto.CustomerResponse;
import at.kocmana.testservices.customerservice.customerinformation.model.dto.CustomerUpdateRequest;
import at.kocmana.testservices.customerservice.customerinformation.model.mapper.CustomerMapper;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    var allCustomers = customerInformationService.retrieveAllCustomers();
    var customerResponses = allCustomers.stream()
        .map(customerMapper::toCustomerResponse)
        .toList();
    return ResponseEntity.ok(customerResponses);
  }

  @GetMapping(path = "/{customerId}")
  @NormallyDistributedEndpointDelaySimulation(mean = 10, standardDeviation = 5)
  public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable int customerId) {
    var customer = customerInformationService.retrieveCustomerById(customerId);
    var customerResponse = customerMapper.toCustomerResponse(customer);
    return ResponseEntity.ok(customerResponse);
  }

  @PostMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 20, standardDeviation = 10)
  public ResponseEntity<CustomerResponse> saveCustomer(
      @RequestBody @Valid CustomerRegistrationRequest customerRegistrationRequest) {
    var customer = customerMapper.toCustomer(customerRegistrationRequest);
    var savedCustomer = customerInformationService.saveCustomer(customer);
    var customerResponse = customerMapper.toCustomerResponse(savedCustomer);
    return ResponseEntity.ok(customerResponse);
  }

  @PutMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 20, standardDeviation = 10)
  public void updateCustomer(@RequestBody @Valid CustomerUpdateRequest customerUpdateRequest) {
    var customer = customerMapper.toCustomer(customerUpdateRequest);
    customerInformationService.updateCustomer(customer);
  }

  @DeleteMapping(path = "/{customerId}")
  @NormallyDistributedEndpointDelaySimulation(mean = 15, standardDeviation = 10)
  public void deleteCustomer(@PathVariable int customerId) {
    customerInformationService.deleteCustomer(customerId);
  }

}
