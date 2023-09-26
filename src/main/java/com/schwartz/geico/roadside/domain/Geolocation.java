package com.schwartz.geico.roadside.domain;

/**
 * Represents a geographic location.
 */
public class Geolocation {
  private final double latitude;

  private final double longitude;

  /**
   * Creates a new instance of the Geolocation class.
   *
   * @param latitude the latitude
   * @param longitude the longitude
   */
  public Geolocation(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

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
