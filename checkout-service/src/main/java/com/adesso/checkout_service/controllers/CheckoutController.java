package com.adesso.checkout_service.controllers;

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

import com.adesso.checkout_service.domain.CheckoutEntity;
import com.adesso.checkout_service.domain.dto.CheckoutDTO;
import com.adesso.checkout_service.mappers.Mapper;
import com.adesso.checkout_service.services.CheckoutService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;
    private static final String PATH = "v1/api/checkout";
    private final Mapper<CheckoutEntity, CheckoutDTO> checkoutMapper;

    @PostMapping(PATH)
    public ResponseEntity<CheckoutDTO> addNewCheckout(@Valid @RequestBody CheckoutDTO newCheckout) {
        CheckoutEntity checkout = checkoutMapper.mapFrom(newCheckout);
        CheckoutEntity sevedCheckout = checkoutService.saveCheckout(checkout);
        return new ResponseEntity<CheckoutDTO>(checkoutMapper.mapTo(sevedCheckout), HttpStatus.CREATED);
    }

    @GetMapping(PATH + "/{id}")
    public ResponseEntity<CheckoutDTO> getCheckoutById(@PathVariable Long id) {
        Optional<CheckoutEntity> result = checkoutService.getCheckoutById(id);
        return result
            .map(checkout -> new ResponseEntity<>(checkoutMapper.mapTo(checkout), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(PATH + "/{id}")
    public ResponseEntity<CheckoutDTO> updateCheckout(@Valid @RequestBody CheckoutDTO checkout, @PathVariable Long id) {
        if (!checkoutService.isExists(id)){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CheckoutEntity checkoutEntity = checkoutMapper.mapFrom(checkout);
        //checkoutEntity.setId(id);
        CheckoutEntity updatedCheckout = checkoutService.saveCheckout(checkoutEntity);
        return new ResponseEntity<>(checkoutMapper.mapTo(updatedCheckout), HttpStatus.OK);
    }

    @DeleteMapping(PATH + "/{id}")
    public ResponseEntity deleteCheckout(@PathVariable Long id) {
        if (!checkoutService.isExists(id)){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        checkoutService.deleteCheckout(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
