package at.kocmana.testservices.productservice.productinformation;

import at.kocmana.testservices.productservice.productinformation.model.ProductInformationNotFoundException;
import at.kocmana.testservices.productservice.productinformation.model.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInformationService {

  private final ProductInformationRepository productInformationRepository;

  @Autowired
  public ProductInformationService(
      ProductInformationRepository productInformationRepository) {
    this.productInformationRepository = productInformationRepository;
  }

  List<Product> retrieveAllProducts() {
    return productInformationRepository.findAll();
  }

  List<Product> retrieveAllProductsWithPagination(Pageable pageable) {
    return productInformationRepository.findAll(pageable).toList();
  }

  public Product retrieveProductById(Integer productId) {
    return productInformationRepository.findById(productId)
        .orElseThrow(() -> generateNotFoundException(productId));
  }

  Integer saveNewProduct(Product product) {
    var savedProduct = productInformationRepository.save(product);
    return savedProduct.getId();
  }

  void updateProduct(Product product) {
    if (productDoesNotExist(product.getId())) {
      throw generateNotFoundException(product.getId());
    }
    productInformationRepository.save(product);
  }

  void deleteProductById(Integer id) {
    if (productDoesNotExist(id)) {
      throw generateNotFoundException(id);
    }
    productInformationRepository.deleteById(id);
  }

  public boolean productDoesNotExist(Integer productId) {
    return !productInformationRepository.existsById(productId);
  }

  private ProductInformationNotFoundException generateNotFoundException(int productId) {
    return new ProductInformationNotFoundException(productId);
  }
}
