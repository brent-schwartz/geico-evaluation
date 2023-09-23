package com.schwartz.geico.roadside.domain;

import java.util.UUID;

public class Customer {
  private final UUID id = UUID.randomUUID();

  private Geolocation currentLocation;

  private String insuranceCompany;

  private String name;

  private String policyNumber;

  public Geolocation getCurrentLocation() {
    return this.currentLocation;
  }

  public String getInsuranceCompany() {
    return this.insuranceCompany;
  }

  public String getName() {
    return this.name;
  }

  public String getPolicyNumber() {
    return this.policyNumber;
  }

  public void setCurrentLocation(Geolocation currentLocation) {
    this.currentLocation = currentLocation;
  }

  public void setInsuranceCompany(String insuranceCompany) {
    this.insuranceCompany = insuranceCompany;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPolicyNumber(String policyNumber) {
    this.policyNumber = policyNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Customer customer = (Customer) o;

    return id.equals(customer.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
