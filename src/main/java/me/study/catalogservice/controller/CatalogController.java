package me.study.catalogservice.controller;

import lombok.RequiredArgsConstructor;
import me.study.catalogservice.service.CatalogService;
import me.study.catalogservice.vo.RequestCatalog;
import me.study.catalogservice.vo.ResponseCatalog;
import me.study.catalogservice.vo.ResponseCatalogPage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/health-check")
    public String status(HttpServletRequest request){
        return String.format("Catalog service on PORT : %s", request.getServerPort());
    }

    @GetMapping("/catalogs")
    public ResponseEntity<ResponseCatalogPage> getCatalogs(@PageableDefault(size = 100, sort = "productName"
                                                                            , direction = Sort.Direction.ASC) Pageable pageable){
        ResponseCatalogPage responseCatalogPage = catalogService.getCatalogs(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(responseCatalogPage);
    }

    @PostMapping("/catalogs/{productId}")
    public ResponseEntity<ResponseCatalog> minusStock(@PathVariable("productId") String productId
                                                        , @Valid @RequestBody RequestCatalog requestCatalog){
        ResponseCatalog responseCatalog = catalogService.minusStock(productId, requestCatalog.getQty());
        return ResponseEntity.status(HttpStatus.OK).body(responseCatalog);
    }
}
