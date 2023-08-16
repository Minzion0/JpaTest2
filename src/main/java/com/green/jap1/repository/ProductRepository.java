package com.green.jap1.repository;

import com.green.jap1.entity.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity>findAllByName(String name, Sort number);//쿼리 메서드
    List<ProductEntity>findAllByNameStartingWith(String name, Sort number);//like문

}
