package com.schwartz.geico.roadside.service;

import com.schwartz.geico.roadside.dao.RoadsideAssistanceDao;
import com.schwartz.geico.roadside.domain.Assistant;
import com.schwartz.geico.roadside.domain.Customer;
import com.schwartz.geico.roadside.domain.Geolocation;
import com.schwartz.geico.roadside.geolocator.Geolocator;

import java.util.*;

/**
 * Implements the RoadsideAssistanceService interface.  Provides roadside assistance services to GEICO customers.
 */
public class RoadsideAssistanceServiceImpl implements RoadsideAssistanceService {
  private final Geolocator geolocator;

  private final RoadsideAssistanceDao roadsideAssistanceDao;

  /**
   * Creates a new instance of the RoadsideAssistanceServiceImpl class.
   * @param geolocator the geolocator to use
   * @param roadsideAssistanceDao the roadside assistance DAO to use
   */
  public RoadsideAssistanceServiceImpl(Geolocator geolocator, RoadsideAssistanceDao roadsideAssistanceDao) {
    this.geolocator = geolocator;
    this.roadsideAssistanceDao = roadsideAssistanceDao;
  }

  /**
   * @see RoadsideAssistanceService#updateAssistantLocation(Assistant, Geolocation)
   */
  @Override
  public void updateAssistantLocation(Assistant assistant, Geolocation assistantLocation) {
    // typically the assistant parameter would not be modified and instead an update method such as this would return a new instance of the assistant, with the new location
    // however, this exercise specified a void return type
    assistant.setCurrentLocation(assistantLocation);
    this.roadsideAssistanceDao.updateAssistant(assistant);
  }

  /**
   * @see RoadsideAssistanceService#findNearestAssistants(Geolocation, int)
   */
  @Override
  public SortedSet<Assistant> findNearestAssistants(Geolocation geolocation, int limit) {
    int regionId = this.geolocator.getRegionId(geolocation);
    List<Assistant> viableAssistants = this.roadsideAssistanceDao.findActiveAssistantsByRegionId(regionId);

    Comparator<Assistant> compareByDistance = (o1, o2) -> {
      double distance1 = this.geolocator.getDistance(geolocation, o1.getCurrentLocation());
      double distance2 = this.geolocator.getDistance(geolocation, o2.getCurrentLocation());
      return Double.compare(distance1, distance2);
    };

    List<Assistant> limitedViableAssistantsSortedByDistance = viableAssistants.stream().sorted(compareByDistance).limit(limit).toList();
    SortedSet<Assistant> sortedSet  = new TreeSet<>(compareByDistance);
    sortedSet.addAll(limitedViableAssistantsSortedByDistance);
    return sortedSet;
  }

  /**
   * @see RoadsideAssistanceService#reserveAssistant(Customer, Geolocation)
   */
  @Override
  public Optional<Assistant> reserveAssistant(Customer customer, Geolocation customerLocation) {
    return findNearestAssistants(customerLocation, 1).stream().findFirst().map(assistant -> {
      assistant.setCustomer(customer);
      return this.roadsideAssistanceDao.updateAssistant(assistant);
    });
  }

  /**
   * @see RoadsideAssistanceService#releaseAssistant(Customer, Assistant)
   */
  @Override
  public void releaseAssistant(Customer customer, Assistant assistant) {
    // typically the assistant parameter would not be modified and instead an update method such as this would return a new instance of the assistant, with the null customer
    // however, this exercise specified a void return type
    assistant.setCustomer(null);
    this.roadsideAssistanceDao.updateAssistant(assistant);
  }
}
