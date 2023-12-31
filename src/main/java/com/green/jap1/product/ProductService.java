package com.green.jap1.product;

import com.green.jap1.entity.ProductDetailEntity;
import com.green.jap1.entity.ProductEntity;
import com.green.jap1.entity.ProviderEntity;
import com.green.jap1.product.model.ProductRegDto;
import com.green.jap1.product.model.ProductUpDto;
import com.green.jap1.product.model.ProductVo;
import com.green.jap1.repository.ProductDetailRepository;
import com.green.jap1.repository.ProductRepository;
import com.green.jap1.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    private final ProviderRepository PROV_RPS;

    private final ProductDetailRepository PROD_RPS;


    public ProductVo insProduct(ProductRegDto dto){

        //1차 인서트
           ProviderEntity referenceById = PROV_RPS.getReferenceById(dto.getProviderId());
//        ProviderEntity providerEntity = new ProviderEntity();
//        providerEntity.setId(dto.getProviderId());

        ProductEntity entity = new ProductEntity();
        entity.setName(dto.getName());
        entity.setStock(dto.getStock());
        entity.setPrice(dto.getPrice());
        entity.setProviderEntity(referenceById);

         repository.save(entity);

        ProductDetailEntity detail = new ProductDetailEntity();
        detail.setProductEntity(entity);
        detail.setDescription(dto.getDescription());

        ProductDetailEntity save1 = PROD_RPS.save(detail);

      return   ProductVo.builder().name(entity.getName())
                .description(save1.getDescription())
                .providerName(referenceById.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .number(entity.getNumber())
                .build();



    }


    public List<ProductVo>getProductAll(String name){
        List<ProductEntity> list;
        if (name==null){
            list = repository.findAll(Sort.by(Sort.Direction.DESC,"number"));
        }else {
            list= repository.findAllByNameStartingWith(name,Sort.by(Sort.Direction.DESC,"number"));
           // list= repository.findAllByName(name,Sort.by(Sort.Direction.DESC,"number"));
        }

        List<ProductVo> result = list.stream().map(item -> ProductVo.builder()
                .number(item.getNumber())
                .price(item.getPrice())
                .stock(item.getStock())
                .name(item.getName())
                        .providerName(item.getProviderEntity().getName())
                        .description(item.getProductDetailEntity().getDescription())
                        .build())

                .toList();

        return result;

    }


    public ProductVo getProduct(long number){
        Optional<ProductEntity> result= repository.findById(number);
        if (result.isEmpty()) return null;



  //      ProductEntity entity = repository.getReferenceById(number);
//      return   ProductVo.builder()
//                .name(entity.getName())
//                .stock(entity.getStock())
//                .price(entity.getPrice())
//                .number(entity.getNumber())
//                .build();

      return   ProductVo.builder()
                .name(result.get().getName())
                .stock(result.get().getStock())
                .price(result.get().getPrice())
                .number(result.get().getNumber())
                .build();


    }

    public ProductEntity updProduct(ProductUpDto dto){
        ProductEntity productEntity = repository.getReferenceById(dto.getNumber());
        productEntity.setPrice(dto.getPrice());
        productEntity.setStock(dto.getStock());
        productEntity.setName(dto.getName());

        return repository.save(productEntity);
    }


    public int delProduct(long number){
        repository.deleteById(number);
        return 1;
    }

    public List<ProductVo> getProductAllSearch(String name){
        List<ProductEntity> entities = repository.findAllByNameStartingWith(name, Sort.by(Sort.Direction.DESC, "number"));

        List<ProductVo> result = entities.stream().map(item -> ProductVo.builder()
                        .number(item.getNumber())
                        .price(item.getPrice())
                        .stock(item.getStock())
                        .name(item.getName()).build())
                .toList();
        return result;

    }



}
