package com.android.banuu.banuubasketballdata.entities;

public final class Player {

  private String firstName;
  private String lastName;
  private String streetName;
  private String email;

  public Player() {
    firstName = "";
    lastName = "";
    streetName = "";
    email = "";
  }

  public final String getFirstName() {
    return firstName;
  }

  public final void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public final String getLastName() {
    return lastName;
  }

  public final void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public final String getStreetName() {
    return streetName;
  }

  public final void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public final String getEmail() {
    return email;
  }

  public final void setEmail(String email) {
    this.email = email;
  }

}
