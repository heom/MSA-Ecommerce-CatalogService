package me.study.catalogservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.study.catalogservice.entity.CatalogEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {
    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public ResponseCatalog(CatalogEntity catalogEntity){
        this.productId = catalogEntity.getProductId();
        this.productName = catalogEntity.getProductName();
        this.stock = catalogEntity.getStock();
        this.unitPrice = catalogEntity.getUnitPrice();
        this.createdDate = catalogEntity.getCreatedDate();
        this.lastModifiedDate = catalogEntity.getLastModifiedDate();
    }
}
