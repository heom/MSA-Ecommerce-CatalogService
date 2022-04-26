package me.study.catalogservice.service;

import me.study.catalogservice.vo.ResponseCatalog;
import me.study.catalogservice.vo.ResponseCatalogPage;
import org.springframework.data.domain.Pageable;

public interface CatalogService {
    ResponseCatalogPage getCatalogs(Pageable pageable);
    ResponseCatalog minusStock(String productId, Integer qty);
}
