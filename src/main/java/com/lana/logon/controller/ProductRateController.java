package com.lana.logon.controller;

import com.lana.logon.dto.ProductRateDto;
import com.lana.logon.dto.mapper.ProductRateMapper;
import com.lana.logon.repository.product.ProductRateRepo;
import io.github.perplexhub.rsql.RSQLJPASupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rates")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductRateController {

    private final ProductRateRepo productRateRepo;
    private final ProductRateMapper productRateMapper;

    @Autowired
    public ProductRateController(ProductRateRepo productRateRepo,
                                 ProductRateMapper productRateMapper) {
        this.productRateRepo = productRateRepo;
        this.productRateMapper = productRateMapper;
    }

    @GetMapping
    public ResponseEntity<Page<ProductRateDto>> getAllProduct(@RequestParam(defaultValue = "") String query,
                                                              Pageable pageable) {
        try {
            return ResponseEntity.ok(
                    productRateRepo
                            .findAll(RSQLJPASupport.toSpecification(query), pageable)
                            .map(productRateMapper::productRateToProductRateDto)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}