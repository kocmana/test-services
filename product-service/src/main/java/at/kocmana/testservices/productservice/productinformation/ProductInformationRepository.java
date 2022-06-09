package at.kocmana.testservices.productservice.productinformation;

import at.kocmana.testservices.productservice.productinformation.model.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductInformationRepository extends JpaRepository<Product, Integer> {

}
