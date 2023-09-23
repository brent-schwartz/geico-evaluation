package com.schwartz.geico.roadside.domain;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents a roadside assistance assistant.
 */
public class Assistant {
  private final UUID id = UUID.randomUUID();

  private boolean active;

  private Geolocation currentLocation;

  private Customer customer;

  private String name;

  /**
   * Gets the current location.
   * @return the current location
   */
  public Geolocation getCurrentLocation() {
    return this.currentLocation;
  }

  /**
   * Gets the customer, if any.
   * @return the customer
   */
  public Optional<Customer> getCustomer() {
    return Optional.ofNullable(this.customer);
  }

  /**
   * Gets the name.
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the active indicator.
   * @return the active indicator
   */
  public boolean isActive() {
    return this.active;
  }

  /**
   * Sets the active indicator.
   * @param active the active indicator
   */
  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * Sets the current location.
   * @param currentLocation the current location
   */
  public void setCurrentLocation(Geolocation currentLocation) {
    this.currentLocation = currentLocation;
  }

  /**
   * Sets the customer.
   * @param customer the customer
   */
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  /**
   * Sets the name.
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Equality is determined by the unique ID of the assistant.
   * @param o the object to compare
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Assistant assistant = (Assistant) o;

    return id.equals(assistant.id);
  }

  /**
   * Hash code is determined by the unique ID of the assistant.
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
