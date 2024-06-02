package com.learn.more.entiry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ImmutableUserMixin {

  @JsonCreator
  public ImmutableUserMixin(@JsonProperty("name") String name, @JsonProperty("age") int age, @JsonProperty("identityCard") String identityCard) {

  }
}
