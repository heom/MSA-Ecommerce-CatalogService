package me.study.catalogservice.messageque;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.study.catalogservice.service.CatalogService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final CatalogService catalogService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order-topic")
    public void updateQty(String kafkaMessage){
        log.info("Kafka Consumer Message -> "+ kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        try{
            map = objectMapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        catalogService.updateQty((String)map.get("productId"), (Integer) map.get("qty"));
    }

}
