package me.study.catalogservice.messageque;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("my-kafka")
@Data
public class MyKafkaProperties {
    private String servers;
    private String groupId;
}
