package at.kocmana.testservices.productservice.productinformation;

import static org.springframework.http.ResponseEntity.ok;

import at.kocmana.testservice.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation;
import at.kocmana.testservices.productservice.model.ElementCreationResponse;
import at.kocmana.testservices.productservice.productinformation.model.dto.ProductCreationRequest;
import at.kocmana.testservices.productservice.productinformation.model.dto.ProductResponse;
import at.kocmana.testservices.productservice.productinformation.model.dto.ProductUpdateRequest;
import at.kocmana.testservices.productservice.productinformation.model.mapper.ProductMapper;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    var products = productInformationService.retrieveAllProductsWithPagination(pageable);
    var productResponses = products.stream()
        .map(productMapper::productToProductResponse)
        .toList();
    return ok(productResponses);
  }

  @GetMapping(value = "/{id}")
  @NormallyDistributedEndpointDelaySimulation(mean = 350, standardDeviation = 100)
  public ResponseEntity<ProductResponse> getProductById(@PathVariable int id) {
    var product = productInformationService.retrieveProductById(id);
    return ok(productMapper.productToProductResponse(product));
  }

  @DeleteMapping(value = "/{id}")
  @NormallyDistributedEndpointDelaySimulation(mean = 600, standardDeviation = 100)
  @RolesAllowed("ADMIN")
  public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
    productInformationService.deleteProductById(id);

    return ResponseEntity.noContent().build();
  }

  @PostMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 600, standardDeviation = 100)
  public ResponseEntity<ElementCreationResponse> saveProduct(@RequestBody @Valid ProductCreationRequest product) {
    var productToSave = productMapper.productCreationRequestToProduct(product);
    var idOfNewProduct = productInformationService.saveNewProduct(productToSave);
    return ok(new ElementCreationResponse(idOfNewProduct));
  }

  @PutMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 600, standardDeviation = 100)
  public void updateProductInformation(@RequestBody @Valid ProductUpdateRequest product) {
    var productToUpdate = productMapper.productUpdateRequestToProduct(product);
    productInformationService.updateProduct(productToUpdate);
  }
}
