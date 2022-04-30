package me.study.catalogservice.repository;

import me.study.catalogservice.entity.CatalogEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CatalogRepositoryTest {

    @Autowired
    private CatalogRepository catalogRepository;

    @Test
    @DisplayName("findByProductId")
    public void findByProductId(){
        //given
        String productId = "CATALOG-0001";

        //when
        CatalogEntity catalogEntity = catalogRepository.findByProductId(productId);

        //then
        assertThat(catalogEntity.getProductId()).isEqualTo(productId);
    }

    @Test
    @DisplayName("findByAll")
    public void findByAll(){
        //given
        int page = 0;
        int size = 2;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "productId"));

        //when
        Page<CatalogEntity> userEntityPage = catalogRepository.findByAll(pageRequest);

        //then
        assertThat(userEntityPage.getContent()).extracting("productId")
                                                .containsExactly("CATALOG-0001"
                                                                ,"CATALOG-0002");
        assertAll(
                () -> assertNotNull(userEntityPage.getContent()),
                () -> assertEquals(userEntityPage.getContent().size(), 2),
                () -> assertEquals(userEntityPage.getTotalElements(), 3),
                () -> assertEquals(userEntityPage.getSize(), size),
                () -> assertEquals(userEntityPage.getNumber(), page)
        );
    }

    @Test
    @DisplayName("minusStock")
    public void minusStock(){
        //given
        int qty = 1;
        String productId = "CATALOG-0001";
        CatalogEntity catalogEntity = catalogRepository.findByProductId(productId);

        //when
        catalogRepository.updateQty(qty, productId);

        CatalogEntity updateCatalogEntity = catalogRepository.findByProductId(productId);

        //then
        assertAll(
                () -> assertEquals(catalogEntity.getProductId(), updateCatalogEntity.getProductId()),
                () -> assertNotEquals(catalogEntity.getStock(), updateCatalogEntity.getStock())
        );
    }
}