package com.learn.more;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.learn.more.entiry.BeanWithFilter;
import com.learn.more.entiry.Response;
import com.learn.more.entiry.TypeEnumWithValue;
import com.learn.more.entiry.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

public class JacksonTest {

  public static final List<User> users;
  public static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    User tom = User.builder().id(1L).name("Tom").age(23).password("1234").createTime(new Date()).birthDate(LocalDate.of(1992, 11, 5)).build();
    User xiao = User.builder().id(2L).name("Xiao").age(35).password("45775").createTime(new Date()).build();
    users = Arrays.asList(tom, xiao);

    //    MAPPER.findAndRegisterModules();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    MAPPER.registerModule(javaTimeModule);

    MAPPER.setSerializationInclusion(Include.NON_NULL);
    MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
    MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
  }

  @Test
  public void writeDefault() {
    System.out.println(users);
  }

  @Test
  public void write() throws JsonProcessingException {
    System.out.println(MAPPER.writeValueAsString(users));
  }

  @Test
  public void read() throws JsonProcessingException {
    String userJson = "{\"id\":1,\"name\":\"Tom\",\"nick\":\"gudy\",\"age\":23,\"password\":\"1234\",\"createTime\":\"2024-05-02 09:09:03\"}";
    User user = MAPPER.readValue(userJson, User.class);
    System.out.println(MAPPER.writeValueAsString(user));
  }

  @Test
  public void readList() throws JsonProcessingException {
    String userJson = "[{\"id\":1,\"name\":\"Tom\",\"age\":23,\"password\":\"1234\",\"createTime\":\"2024-05-02 09:24:28\"}, {\"id\":2,"
        + "\"name\":\"Xiao\",\"age\":35,\"password\":\"45775\",\"createTime\":\"2024-05-02 09:24:28\"}]\n";
    ObjectMapper mapper = new ObjectMapper();
    List<User> users = mapper.readValue(userJson, new TypeReference<List<User>>() {
    });
    System.out.println(users);
  }

  // 报错
  @Test
  public void readResponse() throws JsonProcessingException {
    Response<List<User>> response = new Response<>(true, "", users);
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(response);
    System.out.println(json);

    Response resp = mapper.readValue(json, Response.class);
    List<User> data = (List<User>) resp.getData();
    for (User user : data) {
      System.out.println(user);
    }
  }

  @Test
  public void readResponse1() throws JsonProcessingException {
    Response<List<User>> response = new Response<>(true, "", users);
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(response);
    System.out.println(json);

    Response<List<User>> resp = mapper.readValue(json, new TypeReference<Response<List<User>>>() {
    });
    List<User> data = resp.getData();
    System.out.println(data);
  }

  @Test
  public void writeEnum() throws JsonProcessingException {
    String value = MAPPER.writeValueAsString(TypeEnumWithValue.TYPE1);
    System.out.println(value);
  }

  @Test
  public void whenSerializingUsingJsonFilter_thenCorrect() throws JsonProcessingException {
    BeanWithFilter bean = new BeanWithFilter(1, "My bean");

    FilterProvider filters
        = new SimpleFilterProvider().addFilter(
        "myFilter",
        SimpleBeanPropertyFilter.filterOutAllExcept("name"));

    String result = new ObjectMapper()
        .writer(filters)
        .writeValueAsString(bean);
    System.out.println(result);
  }
}
