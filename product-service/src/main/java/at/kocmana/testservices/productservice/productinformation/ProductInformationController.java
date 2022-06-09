package at.kocmana.testservices.productservice.productinformation;

import at.kocmana.testservice.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation;
import at.kocmana.testservices.productservice.model.ElementCreationResponse;
import at.kocmana.testservices.productservice.productinformation.model.domain.Product;
import at.kocmana.testservices.productservice.productinformation.model.dto.ProductCreationRequest;
import at.kocmana.testservices.productservice.productinformation.model.dto.ProductResponse;
import at.kocmana.testservices.productservice.productinformation.model.dto.ProductUpdateRequest;
import at.kocmana.testservices.productservice.productinformation.model.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/product")
public class ProductInformationController {

  private final ProductMapper productMapper;
  private final ProductInformationService productInformationService;

  @Autowired
  public ProductInformationController(
      ProductMapper productMapper,
      ProductInformationService productInformationService) {
    this.productMapper = productMapper;
    this.productInformationService = productInformationService;
  }

  @GetMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 400, standardDeviation = 100)
  public ResponseEntity<List<ProductResponse>> getAllProducts(Pageable pageable) {
    List<Product> products = productInformationService.retrieveAllProductsWithPagination(pageable);
    List<ProductResponse> productResponses = products.stream()
        .map(productMapper::productToProductResponse)
        .collect(Collectors.toUnmodifiableList());
    return ok(productResponses);
  }

  @GetMapping(value = "/{id}")
  @NormallyDistributedEndpointDelaySimulation(mean = 350, standardDeviation = 100)
  public ResponseEntity<ProductResponse> getProductById(@PathVariable int id) {
    Product product = productInformationService.retrieveProductById(id);
    return ok(productMapper.productToProductResponse(product));
  }

  @DeleteMapping(value = "/{id}")
  @NormallyDistributedEndpointDelaySimulation(mean = 600, standardDeviation = 100)
  public void deleteProductById(@PathVariable @NotNull Integer id) {
    productInformationService.deleteProductById(id);
  }

  @PostMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 600, standardDeviation = 100)
  public ResponseEntity<ElementCreationResponse> saveProduct(@RequestBody @Valid ProductCreationRequest product) {
    Product productToSave = productMapper.productCreationRequestToProduct(product);
    Integer idOfNewProduct = productInformationService.saveNewProduct(productToSave);
    return ok(new ElementCreationResponse(idOfNewProduct));
  }

  @PutMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 600, standardDeviation = 100)
  public void updateProductInformation(@RequestBody @Valid ProductUpdateRequest product) {
    Product productToUpdate = productMapper.productUpdateRequestToProduct(product);
    productInformationService.updateProduct(productToUpdate);
  }
}
