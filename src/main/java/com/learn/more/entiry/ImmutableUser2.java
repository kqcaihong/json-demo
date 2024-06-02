package com.learn.more.entiry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// 与ImmutableUser的区别：使用@JsonProperty、@JsonCreator修饰全参构造器
public class ImmutableUser2 {

  private final String name;
  private final int age;
  private final String identityCard;

  @JsonCreator
  public ImmutableUser2(@JsonProperty("name") String name, @JsonProperty("age") int age, @JsonProperty("identityCard") String identityCard) {
    this.name = name;
    this.age = age;
    this.identityCard = identityCard;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getIdentityCard() {
    return identityCard;
  }
}
