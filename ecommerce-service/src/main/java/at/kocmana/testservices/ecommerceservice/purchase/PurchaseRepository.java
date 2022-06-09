package at.kocmana.testservices.ecommerceservice.purchase;

import at.kocmana.testservices.ecommerceservice.purchase.model.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

  List<Purchase> findPurchasesByCustomerId(Integer customerId);
}
