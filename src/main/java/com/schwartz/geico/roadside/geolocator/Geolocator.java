package com.schwartz.geico.roadside.geolocator;

import com.schwartz.geico.roadside.domain.Geolocation;

public interface Geolocator {
  double getDistance(Geolocation firstLocation, Geolocation secondLocation);

  int getRegionId(Geolocation location);
}
