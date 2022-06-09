package at.kocmana.testservices.ecommerceservice.purchase.model.mapper;

import at.kocmana.testservices.ecommerceservice.purchase.model.domain.Purchase;
import at.kocmana.testservices.ecommerceservice.purchase.model.domain.PurchaseItem;
import at.kocmana.testservices.ecommerceservice.purchase.model.dto.PurchaseCreationRequest;
import at.kocmana.testservices.ecommerceservice.purchase.model.dto.PurchaseItemCreationRequest;
import at.kocmana.testservices.ecommerceservice.purchase.model.dto.PurchaseItemResponse;
import at.kocmana.testservices.ecommerceservice.purchase.model.dto.PurchaseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

  PurchaseResponse purchaseToPurchaseResponse(Purchase purchase);

  Purchase purchaseCreationRequestDtoToPurchase(PurchaseCreationRequest purchase);

  @Mapping(source = "purchase.id", target = "purchaseId")
  PurchaseItemResponse purchaseItemToPurchaseItemDto(PurchaseItem purchaseItem);

  PurchaseItem purchaseItemCreationRequestToPurchaseItem(PurchaseItemCreationRequest purchaseItem);

}
