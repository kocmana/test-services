package at.kocmana.testservices.ecommerceservice.purchase;

import at.kocmana.testservice.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation;
import at.kocmana.testservices.ecommerceservice.purchase.model.domain.Purchase;
import at.kocmana.testservices.ecommerceservice.purchase.model.dto.PurchaseCreationRequest;
import at.kocmana.testservices.ecommerceservice.purchase.model.dto.PurchaseResponse;
import at.kocmana.testservices.ecommerceservice.purchase.model.mapper.PurchaseMapper;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    var purchase = purchaseService.getPurchaseById(id);
    return ResponseEntity.ok(purchaseMapper.purchaseToPurchaseResponse(purchase));
  }

  @GetMapping("/customer/{customerId}")
  @NormallyDistributedEndpointDelaySimulation(mean = 20, standardDeviation = 10)
  public ResponseEntity<List<PurchaseResponse>> retrievePurchasesByCustomerId(
      @PathVariable @Valid @NotNull Integer customerId) {
    var purchaseResponses = purchaseService.getPurchasesForCustomer(customerId)
        .stream()
        .map(purchaseMapper::purchaseToPurchaseResponse)
        .toList();
    return ResponseEntity.ok(purchaseResponses);
  }

  @PostMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 50, standardDeviation = 10)
  public ResponseEntity<Integer> savePurchase(@RequestBody @Valid PurchaseCreationRequest purchase) {
    var newPurchaseId = purchaseService
        .savePurchase(purchaseMapper.purchaseCreationRequestDtoToPurchase(purchase));
    return ResponseEntity.ok(newPurchaseId);
  }
}
