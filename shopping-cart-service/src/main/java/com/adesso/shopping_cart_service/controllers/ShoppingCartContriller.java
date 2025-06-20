package com.adesso.shopping_cart_service.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adesso.shopping_cart_service.domain.ShoppingCartEntity;
import com.adesso.shopping_cart_service.domain.dto.ShoppingCartDTO;
import com.adesso.shopping_cart_service.mappers.ShoppingCartMapper;
import com.adesso.shopping_cart_service.services.ShoppingCartService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ShoppingCartContriller {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartMapper shoppingCartMapper;
    private static final String PATH = "v1/api/shopping-cart";

  @PostMapping(PATH)
  public ResponseEntity<ShoppingCartDTO> createShoppingCart(@Valid @RequestBody ShoppingCartDTO shoppingCart) {
    ShoppingCartEntity shoppingCartEntity = shoppingCartMapper.mapFrom(shoppingCart);
    ShoppingCartEntity sevedShoppingCart = shoppingCartService.createShoppingCartEntity(shoppingCartEntity);
    return new ResponseEntity<ShoppingCartDTO>(shoppingCartMapper.mapTo(sevedShoppingCart), HttpStatus.CREATED);
  }

  @GetMapping(PATH + "/{id}")
  public ResponseEntity<ShoppingCartDTO> getShoppingCartById(@PathVariable Long id) {
      Optional<ShoppingCartEntity> result = shoppingCartService.getShoppingCartEntityById(id);
      return result
        .map(shoppingCart -> new ResponseEntity<>(shoppingCartMapper.mapTo(shoppingCart), HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping(PATH + "/{id}")
  public ResponseEntity<ShoppingCartDTO> updateShoppingCart(@Valid @RequestBody ShoppingCartDTO shoppingCart, @PathVariable Long id) {
    if (!shoppingCartService.isExists(id)){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    ShoppingCartEntity shoppingCartEntity = shoppingCartMapper.mapFrom(shoppingCart);
    shoppingCartEntity.setId(id);
    ShoppingCartEntity updatedShoppingCart = shoppingCartService.createShoppingCartEntity(shoppingCartEntity);
    return new ResponseEntity<>(shoppingCartMapper.mapTo(updatedShoppingCart), HttpStatus.OK);
  }

  @DeleteMapping(PATH + "/{id}")
  public ResponseEntity deleteShoppingCart(@PathVariable Long id) {
    if (!shoppingCartService.isExists(id)){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    shoppingCartService.deleteShoppingCartEntity(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(PATH + "/checkout/{id}" )
  public ResponseEntity checkout(@PathVariable Long id) {
    Optional<ShoppingCartEntity> result = shoppingCartService.getShoppingCartEntityById(id);
    if (result.isPresent()) {
        ShoppingCartDTO cartDTO = shoppingCartMapper.mapTo(result.get());
        shoppingCartService.checkout(cartDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  } 
}
