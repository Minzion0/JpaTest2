package com.green.jap1.product;

import com.green.jap1.entity.ProductEntity;
import com.green.jap1.product.model.ProductUpDto;
import com.green.jap1.product.model.ProductVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public List<ProductVo>getProductAll(@RequestParam(required = false) String name){
        return service.getProductAll(name);
    }

    @GetMapping("/{number}")
    public ProductVo getProduct(@PathVariable long number){
        return service.getProduct(number);
    }
    @GetMapping("/search")
    public List<ProductVo>getProductAllSearch(@RequestParam String name){
        return service.getProductAllSearch(name);
    }


    @PutMapping
    public ProductEntity putProduct(@RequestBody ProductUpDto dto){
        return service.updProduct(dto);
    }

    @DeleteMapping("/{number}")
    public int delProduct(@PathVariable long number){
        return service.delProduct(number);
    }

}
