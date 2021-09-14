package at.kocmana.testservices.ecommerceservice.purchase;

import at.kocmana.testservice.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation;
import at.kocmana.testservices.ecommerceservice.purchase.model.domain.Purchase;
import at.kocmana.testservices.ecommerceservice.purchase.model.dto.PurchaseCreationRequest;
import at.kocmana.testservices.ecommerceservice.purchase.model.dto.PurchaseResponse;
import at.kocmana.testservices.ecommerceservice.purchase.model.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@RestController
@RequestMapping(value = "/purchase")
public class PurchaseController {

  private final PurchaseService purchaseService;
  private final PurchaseMapper purchaseMapper;

  @Autowired
  public PurchaseController(PurchaseService purchaseService,
      PurchaseMapper purchaseMapper) {
    this.purchaseService = purchaseService;
    this.purchaseMapper = purchaseMapper;
  }

  @GetMapping("/{id}")
  @NormallyDistributedEndpointDelaySimulation(mean = 30, standardDeviation = 10)
  public ResponseEntity<PurchaseResponse> retrievePurchaseById(@PathVariable int id) {
    Purchase purchase = purchaseService.getPurchaseById(id);
    return ResponseEntity.ok(purchaseMapper.purchaseToPurchaseResponse(purchase));
  }

  @GetMapping("/customer/{customerId}")
  @NormallyDistributedEndpointDelaySimulation(mean = 20, standardDeviation = 10)
  public ResponseEntity<List<PurchaseResponse>> retrievePurchasesByCustomerId(
      @PathVariable @Valid @NotNull Integer customerId) {
    List<PurchaseResponse> purchaseResponses = purchaseService.getPurchasesForCustomer(customerId)
        .stream()
        .map(purchaseMapper::purchaseToPurchaseResponse)
        .collect(toUnmodifiableList());
    return ResponseEntity.ok(purchaseResponses);
  }

  @PostMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 50, standardDeviation = 10)
  public ResponseEntity<Integer> savePurchase(@RequestBody @Valid PurchaseCreationRequest purchase) {
    int newPurchaseId = purchaseService
        .savePurchase(purchaseMapper.purchaseCreationRequestDtoToPurchase(purchase));
    return ResponseEntity.ok(newPurchaseId);
  }
}
