package com.learn.more.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

/**
 * 对SpringMVC中ObjectMapper自定义配置
 * 1 设置java.util.Date时间类的序列化以及反序列化的格式；
 * 2 JSR 310日期时间处理；
 * 3 全局转化Long类型为String，解决序列化后传入前端Long类型缺失精度问题。
 */
@Component
public class SpringMVCJacksonConfig implements Jackson2ObjectMapperBuilderCustomizer, Ordered {

  private final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
  private final String dateFormat = "yyyy-MM-dd";
  private final String timeFormat = "HH:mm:ss";

  @Override
  public void customize(Jackson2ObjectMapperBuilder builder) {
    builder.simpleDateFormat(dateTimeFormat);

    JavaTimeModule javaTimeModule = new JavaTimeModule();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
    javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));

    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timeFormat);
    javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
    javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));

    builder.modules(javaTimeModule);

    // 全局转化Long类型为String，避免前端Long类型精度丢失问题
    builder.serializerByType(BigInteger.class, ToStringSerializer.instance);
    builder.serializerByType(Long.class, ToStringSerializer.instance);
  }

  @Override
  public int getOrder() {
    return 1;
  }
}
