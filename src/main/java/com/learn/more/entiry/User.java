package com.learn.more.entiry;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.learn.more.config.CustomDateSerializer;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
public class User {

  private Long id;
  private String name;
  private int age;
  @JsonIgnore
  private String password;
  //  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @JsonSerialize(using = CustomDateSerializer.class)
  private Date createTime;

  @JsonProperty("birthDay")
  //  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private LocalDate birthDate;

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public User(Long id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    try {
      return new ObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      return "";
    }
  }

  @JsonGetter("nick")
  public String getName() {
    return name;
  }
}
