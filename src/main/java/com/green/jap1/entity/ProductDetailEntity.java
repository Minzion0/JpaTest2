package com.green.jap1.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product_detail")
@SuperBuilder
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class ProductDetailEntity{

    @Id
    @Column(name = "product_number",updatable = false,nullable = false,columnDefinition = "BIGINT UNSIGNED")
    private Long id;



    @Column(length = 500)
    private String description;

    @MapsId
    @OneToOne
    @JoinColumn(name = "product_number")
    private ProductEntity productEntity;

}
