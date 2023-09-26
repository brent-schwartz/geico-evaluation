package com.schwartz.geico.roadside.geolocator;

import com.schwartz.geico.roadside.domain.Geolocation;

/**
 * Sample implementation of the Geolocator interface.
 */
public class SampleGeolocatorImpl implements Geolocator {
  /**
   * @see Geolocator#getDistance(Geolocation, Geolocation)
   */
  @Override
  public double getDistance(Geolocation firstLocation, Geolocation secondLocation) {
    return Math.abs(firstLocation.getLatitude() - secondLocation.getLatitude()) +
        Math.abs(firstLocation.getLongitude() - secondLocation.getLongitude());
  }

  /**
   * @see Geolocator#getRegionId(Geolocation)
   */
  @Override
  public int getRegionId(Geolocation location) {
    return 15;
  }
}
