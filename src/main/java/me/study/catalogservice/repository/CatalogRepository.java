package me.study.catalogservice.repository;

import me.study.catalogservice.entity.CatalogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CatalogRepository extends JpaRepository<CatalogEntity, Long> {
    CatalogEntity findByProductId(String productId);

    @Query(value="select c from CatalogEntity c"
            , countQuery = "select count(c.id) from CatalogEntity c")
    Page<CatalogEntity> findByAll(Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update CatalogEntity c set c.stock = c.stock - :qty where c.productId = :productId")
    int updateQty(@Param("qty") int qty, @Param("productId") String productId);
}
