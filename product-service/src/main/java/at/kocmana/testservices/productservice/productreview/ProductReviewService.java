package at.kocmana.testservices.productservice.productreview;

import at.kocmana.testservices.productservice.productreview.model.ProductReviewNotFoundException;
import at.kocmana.testservices.productservice.productreview.model.domain.ProductReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
class ProductReviewService {

  private final ProductReviewRepository productReviewRepository;

  @Autowired
  public ProductReviewService(ProductReviewRepository productReviewRepository) {
    this.productReviewRepository = productReviewRepository;
  }

  List<ProductReview> getAllReviews(Pageable pageable) {
    return productReviewRepository.findAll(pageable).getContent();
  }

  ProductReview getReviewById(int reviewId) {
    return productReviewRepository.findById(reviewId)
        .orElseThrow(() -> generateReviewNotFoundException(reviewId));
  }

  List<ProductReview> getReviewsForProduct(int productId) {
    return productReviewRepository.findByProductId(productId);
  }

  List<ProductReview> getReviewsForCustomer(int customerId) {
    return productReviewRepository.findByCustomerId(customerId);
  }

  MultiValueMap<Integer, ProductReview> getReviewsForCustomers(List<Integer> customerIds) {
    var reviews = productReviewRepository.findByCustomerIdIn(customerIds);
    MultiValueMap<Integer, ProductReview> reviewsByCustomer = new LinkedMultiValueMap<>();
    reviews.forEach(review -> reviewsByCustomer.add(review.getCustomerId(), review));

    return reviewsByCustomer;
  }

  int saveReview(ProductReview productReview) {
    var savedReview = productReviewRepository.save(productReview);
    return savedReview.getId();
  }

  void updateReview(ProductReview productReview) {
    var oldProductReview = getReviewById(productReview.getId());
    productReview.setProduct(oldProductReview.getProduct());
    productReviewRepository.save(productReview);
  }

  void deleteReviewById(Integer id) {
    if (!productReviewRepository.existsById(id)) {
      throw generateReviewNotFoundException(id);
    }
    productReviewRepository.deleteById(id);
  }

  private ProductReviewNotFoundException generateReviewNotFoundException(int reviewId) {
    return new ProductReviewNotFoundException(reviewId);
  }
}
