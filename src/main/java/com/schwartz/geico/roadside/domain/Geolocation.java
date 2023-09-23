package com.schwartz.geico.roadside.domain;

/**
 * Represents a geographic location.
 */
public class Geolocation {
  private double latitude;

  private double longitude;

  /**
   * Gets the latitude.
   * @return the latitude
   */
  public double getLatitude() {
    return this.latitude;
  }

  /**
   * Gets the longitude.
   * @return the longitude
   */
  public double getLongitude() {
    return this.longitude;
  }

  /**
   * Sets the latitude.
   * @param latitude the latitude
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * Sets the longitude.
   * @param longitude the longitude
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  /**
   * Equality is determined by the latitude and longitude.
   * @param o the object to compare
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Geolocation that = (Geolocation) o;

    if (Double.compare(latitude, that.latitude) != 0) return false;
    return Double.compare(longitude, that.longitude) == 0;
  }

  /**
   * Hash code is determined by the latitude and longitude.
   * @return the hash code
   */
  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(latitude);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(longitude);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
