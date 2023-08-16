package com.green.jap1.entity;

import com.green.jap1.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class ProductEntityTest {
    @Autowired
    private ProductRepository productRep;

    @Test
    void insProduct(){
        ProductEntity productEntity = ProductEntity.builder()
                .stock(25)
                .price(1_000)
                .name("솜사탕")
                .build();

        productRep.save(productEntity);
    }

    @Test
    @Transactional//영속성
    void updProduct(){
        ProductEntity productEntity = productRep.getReferenceById(1L);

        log.info("productEntity : {}",productEntity);

        productEntity.setName("사탕");
        productEntity.setPrice(2_000);

        productRep.save(productEntity);

    }

    @Test
    void findProductAll(){

        List<ProductEntity> all = productRep.findAll();
        log.info("findAll : {}",all.get(0));
    }

}