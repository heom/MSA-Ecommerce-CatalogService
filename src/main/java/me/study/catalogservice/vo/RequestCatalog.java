package me.study.catalogservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder @AllArgsConstructor
@NoArgsConstructor
public class RequestCatalog {
    @Min(value = 0, message = "qty not be less then 0")
    private Integer qty;
}
