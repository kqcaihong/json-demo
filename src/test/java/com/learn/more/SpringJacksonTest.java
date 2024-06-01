package com.learn.more;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.learn.more.entiry.BeanWithFilter;
import com.learn.more.entiry.Response;
import com.learn.more.entiry.TypeEnumWithValue;
import com.learn.more.entiry.User;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MoreApplication.class)
public class SpringJacksonTest {

  public static final List<User> users;
  @Autowired
  private ObjectMapper MAPPER;

  static {
    User tom = User.builder().id(1L).name("Tom").age(23).password("1234").createTime(new Date()).birthDate(LocalDate.of(1992, 11, 5)).build();
    User xiao = User.builder().id(2L).name("Xiao").age(35).password("45775").createTime(new Date()).build();
    users = Arrays.asList(tom, xiao);
  }

  @Test
  public void write() throws JsonProcessingException {
    System.out.println(MAPPER.writeValueAsString(users));
  }
}
