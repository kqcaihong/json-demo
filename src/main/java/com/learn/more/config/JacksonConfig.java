package com.learn.more.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

  @Bean
  public ObjectMapper objectMapper() {
    CustomDateSerializer dateSerializer = new CustomDateSerializer();
    ObjectMapper mapper = new Jackson2ObjectMapperBuilder()
        .createXmlMapper(false)
        .serializers(dateSerializer)
        .failOnEmptyBeans(false)
        .failOnUnknownProperties(false)
        .build();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return mapper;
  }
}
