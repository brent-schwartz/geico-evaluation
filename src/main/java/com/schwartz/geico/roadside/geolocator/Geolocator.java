package com.schwartz.geico.roadside.geolocator;

import com.schwartz.geico.roadside.domain.Geolocation;

/**
 * Provides geolocation services.
 */
public interface Geolocator {
  /**
   * Gets the distance in miles between two locations.
   * @param firstLocation the first location
   * @param secondLocation the second location
   * @return the distance between the two locations in miles
   */
  double getDistance(Geolocation firstLocation, Geolocation secondLocation);

  /**
   * Gets the region ID for a location.
   * @param location the location
   * @return the region ID
   */
  int getRegionId(Geolocation location);
}
