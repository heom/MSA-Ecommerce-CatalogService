package me.study.catalogservice.service;

import lombok.RequiredArgsConstructor;
import me.study.catalogservice.entity.CatalogEntity;
import me.study.catalogservice.exception.CatalogNotFoundException;
import me.study.catalogservice.exception.StockException;
import me.study.catalogservice.repository.CatalogRepository;
import me.study.catalogservice.vo.ResponseCatalogPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    @Override
    public ResponseCatalogPage getCatalogs(Pageable pageable) {
        Page<CatalogEntity> catalogEntityPage = catalogRepository.findByAll(pageable);
        return new ResponseCatalogPage(catalogEntityPage);
    }

    @Transactional
    @Override
    public void updateQty(String productId, Integer qty) {
        CatalogEntity catalogEntity = catalogRepository.findByProductId(productId);

        if (catalogEntity == null)
            throw new CatalogNotFoundException(productId);

        if (catalogEntity.getStock() < qty)
            throw new StockException(catalogEntity.getStock());

        if(catalogRepository.updateQty(qty, productId) == 0)
            throw new CatalogNotFoundException(productId);
    }
}