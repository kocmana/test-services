package at.kocmana.testservices.productservice.productreview;

import at.kocmana.testservices.productservice.productreview.model.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {

  List<ProductReview> findByProductId(Integer productId);

  List<ProductReview> findByCustomerId(Integer customerId);

  @Query(value = "SELECT r FROM ProductReview r WHERE r.customerId IN ?1 GROUP BY r.customerId")
  List<ProductReview> findByCustomerIdIn(List<Integer> customerIds);
}
