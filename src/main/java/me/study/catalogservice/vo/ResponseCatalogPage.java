package me.study.catalogservice.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.study.catalogservice.entity.CatalogEntity;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ResponseCatalogPage {
    private Long totalCount;
    private List<ResponseCatalog> list;

    public ResponseCatalogPage(Page<CatalogEntity> catalogEntityPage){
        this.totalCount = catalogEntityPage.getTotalElements();
        List<ResponseCatalog> responseCatalogList = new ArrayList<>();
        catalogEntityPage.getContent().forEach(catalogEntity ->{
            responseCatalogList.add(new ResponseCatalog(catalogEntity));
        });
        this.list = responseCatalogList;
    }
}
