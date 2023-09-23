package com.schwartz.geico.roadside.domain;

import java.util.UUID;

/**
 * Represents a customer who requires roadside assistance.
 */
public class Customer {
  private final UUID id = UUID.randomUUID();

  private Geolocation currentLocation;

  private String name;

  private String policyNumber;

  /**
   * Gets the current location.
   * @return the current location
   */
  public Geolocation getCurrentLocation() {
    return this.currentLocation;
  }

  /**
   * Gets the name.
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the policy number.
   * @return the policy number
   */
  public String getPolicyNumber() {
    return this.policyNumber;
  }

  /**
   * Sets the current location.
   * @param currentLocation the current location
   */
  public void setCurrentLocation(Geolocation currentLocation) {
    this.currentLocation = currentLocation;
  }

  /**
   * Sets the name.
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the policy number.
   * @param policyNumber the policy number
   */
  public void setPolicyNumber(String policyNumber) {
    this.policyNumber = policyNumber;
  }

  /**
   * Equality is determined by the unique ID of the customer.
   * @param o the object to compare
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Customer customer = (Customer) o;

    return id.equals(customer.id);
  }

  /**
   * Hash code is determined by the unique ID of the customer.
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
