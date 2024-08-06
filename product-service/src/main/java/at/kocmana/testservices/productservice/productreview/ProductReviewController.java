package at.kocmana.testservices.productservice.productreview;

import at.kocmana.testservice.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation;
import at.kocmana.testservices.productservice.model.ElementCreationResponse;
import at.kocmana.testservices.productservice.productinformation.ProductInformationService;
import at.kocmana.testservices.productservice.productinformation.model.ProductInformationNotFoundException;
import at.kocmana.testservices.productservice.productinformation.model.domain.Product;
import at.kocmana.testservices.productservice.productreview.model.domain.ProductReview;
import at.kocmana.testservices.productservice.productreview.model.dto.ProductReviewCreationRequest;
import at.kocmana.testservices.productservice.productreview.model.dto.ProductReviewResponse;
import at.kocmana.testservices.productservice.productreview.model.dto.ProductReviewUpdateRequest;
import at.kocmana.testservices.productservice.productreview.model.mapper.ProductReviewMapper;
import java.util.AbstractMap;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ProductReviewController {

  private final ProductReviewService productReviewService;
  private final ProductInformationService productInformationService;
  private final ProductReviewMapper productReviewMapper;

  @Autowired
  public ProductReviewController(ProductReviewService productReviewService,
                                 ProductInformationService productInformationService,
                                 ProductReviewMapper productReviewMapper) {
    this.productReviewService = productReviewService;
    this.productInformationService = productInformationService;
    this.productReviewMapper = productReviewMapper;
  }

  @GetMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 400, standardDeviation = 100)
  public ResponseEntity<List<ProductReviewResponse>> getAllReviews(Pageable pageable) {
    List<ProductReview> productReviews = productReviewService.getAllReviews(pageable);
    return toProductReviewResponse(productReviews);
  }

  @DeleteMapping("/{id}")
  @NormallyDistributedEndpointDelaySimulation(mean = 600, standardDeviation = 100)
  public void deleteReview(@PathVariable @NotNull Integer id) {
    productReviewService.deleteReviewById(id);
  }

  @GetMapping("/product/{productId}")
  @NormallyDistributedEndpointDelaySimulation(mean = 350, standardDeviation = 100)
  public ResponseEntity<List<ProductReviewResponse>> getReviewsForProduct(
      @PathVariable @Valid @NotNull int productId) {
    if (productInformationService.productDoesNotExist(productId)) {
      throw new ProductInformationNotFoundException(productId);
    }
    productInformationService.retrieveProductById(productId);
    List<ProductReview> productReviews = productReviewService.getReviewsForProduct(productId);
    return toProductReviewResponse(productReviews);
  }

  @GetMapping("/customer/{customerId}")
  @NormallyDistributedEndpointDelaySimulation(mean = 350, standardDeviation = 100)
  public ResponseEntity<List<ProductReviewResponse>> getReviewsForCustomer(
      @PathVariable @Valid @NotNull int customerId) {
    List<ProductReview> productReviews = productReviewService.getReviewsForCustomer(customerId);
    return toProductReviewResponse(productReviews);
  }


  @GetMapping("/customer")
  @NormallyDistributedEndpointDelaySimulation(mean = 350, standardDeviation = 100)
  public MultiValueMap<Integer, ProductReview> getReviewsForCustomers(
      @RequestParam @NotNull List<Integer> customerIds) {
    MultiValueMap<Integer, ProductReview> productReviews = productReviewService.getReviewsForCustomers(customerIds);
    MultiValueMap<Integer, ProductReviewResponse> productReviewResponses = new LinkedMultiValueMap<>();
    productReviews.entrySet().stream()
        .map(entry ->
            new AbstractMap.SimpleImmutableEntry<Integer, List<ProductReviewResponse>>
                (entry.getKey(), toProductReviewResponses(entry.getValue())))
        .forEach(entry -> productReviewResponses.addAll(entry.getKey(), entry.getValue()));

    return productReviews;
  }

  @PostMapping("/product/{productId}")
  @NormallyDistributedEndpointDelaySimulation(mean = 400, standardDeviation = 100)
  public ResponseEntity<ElementCreationResponse> postReview(
      @PathVariable @Valid @NotNull Integer productId,
      @RequestBody @Valid ProductReviewCreationRequest productReviewCreationRequest) {
    ProductReview productReview = productReviewMapper
        .productReviewCreationRequestToProductReview(productReviewCreationRequest);
    Product reviewedProduct = productInformationService.retrieveProductById(productId);
    productReview.setProduct(reviewedProduct);
    Integer reviewId = productReviewService.saveReview(productReview);
    return ResponseEntity.ok(new ElementCreationResponse(reviewId));
  }

  @PutMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 400, standardDeviation = 100)
  public void updateReview(
      @RequestBody @Valid ProductReviewUpdateRequest productReviewUpdateRequest) {
    ProductReview productReview = productReviewMapper
        .productReviewUpdateRequestToProductReview(productReviewUpdateRequest);
    productReviewService.updateReview(productReview);
  }

  private List<ProductReviewResponse> toProductReviewResponses(List<ProductReview> productReviews) {
    return productReviews.stream()
        .map(productReviewMapper::productReviewToProductReviewResponse)
        .toList();
  }

  private ResponseEntity<List<ProductReviewResponse>> toProductReviewResponse(
      List<ProductReview> productReviews) {
    List<ProductReviewResponse> productReviewResponses = toProductReviewResponses(productReviews);
    return ResponseEntity.ok(productReviewResponses);
  }

}
