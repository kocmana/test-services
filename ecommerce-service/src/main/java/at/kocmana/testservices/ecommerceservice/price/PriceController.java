package at.kocmana.testservices.ecommerceservice.price;

import at.kocmana.testservice.commons.delay.annotation.NormallyDistributedEndpointDelaySimulation;
import at.kocmana.testservices.ecommerceservice.price.model.domain.Price;
import at.kocmana.testservices.ecommerceservice.price.model.dto.PriceDto;
import at.kocmana.testservices.ecommerceservice.price.model.mapper.PriceMapper;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
public class PriceController {

  private final PriceService priceService;
  private final PriceMapper priceMapper;

  @Autowired
  public PriceController(PriceService priceService, PriceMapper priceMapper) {
    this.priceService = priceService;
    this.priceMapper = priceMapper;
  }

  @GetMapping("/product/{productId}")
  @NormallyDistributedEndpointDelaySimulation(mean = 30, standardDeviation = 10)
  public ResponseEntity<PriceDto> getCurrentPriceForProduct(
      @PathVariable @Valid @NotNull Integer productId) {
    var price = priceService.getCurrentPriceForProduct(productId);
    var priceDto = priceMapper.priceToPriceDto(price);
    return ResponseEntity.ok(priceDto);
  }

  @GetMapping("/product/{productId}/all")
  @NormallyDistributedEndpointDelaySimulation(mean = 60, standardDeviation = 30)
  public ResponseEntity<List<PriceDto>> getAllPricesForProduct(
      @PathVariable @Valid @NotNull Integer productId) {
    var prices = priceService.getAllPricesForProduct(productId);
    return ResponseEntity.ok(toDtos(prices));
  }

  @GetMapping(value = "/product/{productId}", params = {"from", "to"})
  @NormallyDistributedEndpointDelaySimulation(mean = 60, standardDeviation = 30)
  public ResponseEntity<List<PriceDto>> getPricesForProductAndTimeframe(
      @PathVariable @Valid @NotNull Integer productId,
      @RequestParam @Valid @NotNull LocalDateTime from,
      @RequestParam @Valid @NotNull LocalDateTime to) {
    var prices = priceService.getPricesForProductAndTimeframe(productId, from, to);

    return ResponseEntity.ok(toDtos(prices));
  }

  @PostMapping
  @NormallyDistributedEndpointDelaySimulation(mean = 50, standardDeviation = 25)
  public ResponseEntity<PriceDto> savePrice(@RequestBody @Valid PriceDto priceDto) {
    var price = priceMapper.priceDtoToPrice(priceDto);
    var savedPrice = priceService.savePriceForProduct(price);
    var response = priceMapper.priceToPriceDto(savedPrice);
    return ResponseEntity.ok().body(response);
  }

  private List<PriceDto> toDtos(List<Price> prices) {
    return prices.stream()
        .map(priceMapper::priceToPriceDto)
        .toList();
  }

}
