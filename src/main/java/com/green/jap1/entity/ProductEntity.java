package com.green.jap1.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@Table(name = "product")
@Data
@SuperBuilder
@ToString(callSuper = true)//상속한것도 보이게 해라
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ProductEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_number",updatable = false,nullable = false,columnDefinition = "BIGINT UNSIGNED")
    private Long number;

    @Column(nullable = false,length = 10)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne(optional = false)
    @JoinColumn(name = "provider_id")
    @ToString.Exclude
    private ProviderEntity providerEntity;

    @OneToOne(mappedBy = "productEntity")//mappedBy 덕분에 컬럼이 안생기고 서로 연결상태이다
    private ProductDetailEntity productDetailEntity;


}
