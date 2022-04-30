package me.study.catalogservice.service;

import me.study.catalogservice.exception.CatalogNotFoundException;
import me.study.catalogservice.exception.StockException;
import me.study.catalogservice.vo.ResponseCatalog;
import me.study.catalogservice.vo.ResponseCatalogPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CatalogServiceImplTest {

    @Autowired
    CatalogService catalogService;

    @Test
    @DisplayName("getCatalogs")
    public void getCatalogs(){
        int page = 0;
        int size = 2;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "productId"));

        //when
        ResponseCatalogPage responseCatalogPage = catalogService.getCatalogs(pageRequest);
        Long resultTotalCount = responseCatalogPage.getTotalCount();
        List<ResponseCatalog> list = responseCatalogPage.getList();


        //then
        assertThat(list).extracting("productId")
                        .containsExactly("CATALOG-0001"
                                        ,"CATALOG-0002");
        assertAll(
                () -> assertEquals(resultTotalCount, 3),
                () -> assertEquals(list.size(), size)
        );
    }

    @Test
    @DisplayName("updateQty")
    public void updateQty(){
        //given
        String productId = "CATALOG-0001";
        int qty = 0;

        //when & then
        assertDoesNotThrow(()-> catalogService.updateQty(productId, qty));
    }

    @Test
    @DisplayName("updateQty_not_found_catalog")
    public void updateQty_not_found_catalog(){
        //given
        String productId = "CATALOG-0005";
        int qty = 0;

        //when & then
        assertThrows(CatalogNotFoundException.class, ()-> catalogService.updateQty(productId, qty));
    }

    @Test
    @DisplayName("updateQty_error_qty")
    public void updateQty_error_qty(){
        //given
        String productId = "CATALOG-0001";
        int qty = 1000;

        //when & then
        assertThrows(StockException.class, ()-> catalogService.updateQty(productId, qty));
    }
}