package com.learn.more.entiry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum TypeEnumWithValue {
  TYPE1(1, "Type 1"), TYPE2(2, "Type 2");

  private Integer id;
  private String name;

  TypeEnumWithValue(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  //  @JsonValue
  public String getName() {
    return name;
  }

  public Integer getId() {
    return id;
  }
}
