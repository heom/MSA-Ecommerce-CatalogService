package me.study.catalogservice.exception;

public class StockException extends RuntimeException {
    public StockException(Integer stock) {
        super(String.format("Qty must be equal or less than %s", stock));
    }
}
