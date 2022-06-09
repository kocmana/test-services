package at.kocmana.testservices.customerservice.customernetwork;

import at.kocmana.testservices.customerservice.customernetwork.model.domain.CustomerInteraction;
import at.kocmana.testservices.customerservice.customernetwork.model.domain.CustomerInteractionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerNetworkRepository extends JpaRepository<CustomerInteraction, CustomerInteractionId> {

  List<CustomerInteraction> findBySourceCustomerId(Integer sourceCustomerId);

}
