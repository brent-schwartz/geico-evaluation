package com.schwartz.geico.roadside.domain;

import java.util.Optional;
import java.util.UUID;

public class Assistant {
  private final UUID id = UUID.randomUUID();

  private boolean active;

  private Geolocation currentLocation;

  private Customer customer;

  private String name;

  public Geolocation getCurrentLocation() {
    return this.currentLocation;
  }

  public Optional<Customer> getCustomer() {
    return Optional.ofNullable(this.customer);
  }

  public String getName() {
    return this.name;
  }

  public boolean isActive() {
    return this.active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setCurrentLocation(Geolocation currentLocation) {
    this.currentLocation = currentLocation;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Assistant assistant = (Assistant) o;

    return id.equals(assistant.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
