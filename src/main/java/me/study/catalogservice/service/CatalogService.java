package me.study.catalogservice.service;

import me.study.catalogservice.vo.ResponseCatalogPage;
import org.springframework.data.domain.Pageable;

public interface CatalogService {
    ResponseCatalogPage getCatalogs(Pageable pageable);
    void updateQty(String productId, Integer qty);
}
