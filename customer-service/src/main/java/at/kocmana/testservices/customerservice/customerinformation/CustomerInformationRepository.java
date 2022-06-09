package at.kocmana.testservices.customerservice.customerinformation;

import at.kocmana.testservices.customerservice.customerinformation.model.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CustomerInformationRepository extends JpaRepository<Customer, Integer> {

}
