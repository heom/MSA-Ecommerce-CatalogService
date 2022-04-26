package me.study.catalogservice.exception;

public class CatalogNotFoundException extends RuntimeException {
    public CatalogNotFoundException(String productId) {
        super(String.format("ProductId [%s] not found", productId));
    }
}
