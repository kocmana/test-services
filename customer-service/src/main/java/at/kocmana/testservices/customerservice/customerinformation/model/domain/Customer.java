package at.kocmana.testservices.customerservice.customerinformation.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
  private Integer customerId;
  @Column(name = "gender")
  @Enumerated(EnumType.STRING)
  private Gender gender;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  private String street;
  @Column(name = "house_number")
  private String houseNumber;
  private String top;
  @Column(name = "postal_code")
  private String postalCode;
  private String country;
  @Column(name = "telephone_number")
  private String telephoneNumber;
  @Column(name = "email_address")
  private String emailAddress;
}
