package me.study.catalogservice.controller;

import me.study.catalogservice.vo.ResponseCatalogPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    private String getApiUrl(String url){
        StringBuffer sbf = new StringBuffer();
        return sbf.append("http://localhost:")
                .append(this.port)
                .append(url).toString();
    }

    @Test
    @DisplayName("getCatalogs")
    public void getCatalogs(){
        //given
        int page = 0;
        int size = 2;
        String url = getApiUrl("/catalogs?page="+page+"&size="+size);

        //when
        ResponseEntity<ResponseCatalogPage> responseEntity = this.restTemplate.getForEntity(url, ResponseCatalogPage.class);

        //then
        assertAll(
                () -> assertEquals(responseEntity.getStatusCode(), HttpStatus.OK),
                () -> assertEquals(3, responseEntity.getBody().getTotalCount()),
                () -> assertEquals(size, responseEntity.getBody().getList().size())
        );
    }
}