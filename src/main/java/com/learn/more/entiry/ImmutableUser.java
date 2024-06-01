package com.learn.more.entiry;

public class ImmutableUser {

  private final String name;
  private final int age;
  private final String identityCard;

  public ImmutableUser(String name, int age, String identityCard) {
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
