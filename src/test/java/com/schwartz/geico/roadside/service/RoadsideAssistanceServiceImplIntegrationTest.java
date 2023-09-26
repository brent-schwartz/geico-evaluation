package com.schwartz.geico.roadside.service;

import com.schwartz.geico.roadside.dao.SampleRoadsideAssistanceDaoImpl;
import com.schwartz.geico.roadside.domain.Assistant;
import com.schwartz.geico.roadside.domain.Customer;
import com.schwartz.geico.roadside.domain.Geolocation;
import com.schwartz.geico.roadside.geolocator.SampleGeolocatorImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;

/**
 * Integration tests for the RoadsideAssistanceServiceImpl class, using sample DAO and sample Geolocator.
 * This provides a simulation of how the application can run using its current implementation.
 */
public class RoadsideAssistanceServiceImplIntegrationTest {
  @Test
  public void testRoadsideAssistanceServiceImpl() {
    SampleRoadsideAssistanceDaoImpl dao = new SampleRoadsideAssistanceDaoImpl();
    RoadsideAssistanceServiceImpl service = new RoadsideAssistanceServiceImpl(new SampleGeolocatorImpl(), dao);

    List<Assistant> assistants = dao.findActiveAssistantsWithNoCustomerByRegionId(15);
    assertNotNull(assistants);
    assertFalse(assistants.isEmpty());
    Assistant assistant = assistants.get(0);

    Geolocation newLocation = new Geolocation(1, 2);
    service.updateAssistantLocation(assistant, newLocation);
    assertEquals(newLocation, assistant.getCurrentLocation());

    SortedSet<Assistant> sortedAssistants = service.findNearestAssistants(newLocation, 1);
    assertNotNull(sortedAssistants);
    assertEquals(1, sortedAssistants.size());
    assertEquals(assistant, sortedAssistants.first());

    Customer customer = new Customer();
    customer.setName("Scarlet Witch");
    customer.setPolicyNumber("123456789");
    customer.setCurrentLocation(new Geolocation(1, 3));

    Optional<Assistant> reservedAssistant = service.reserveAssistant(customer, customer.getCurrentLocation());
    assertTrue(reservedAssistant.isPresent());
    assertEquals(assistant, reservedAssistant.get());
    assertTrue(assistant.getCustomer().isPresent());
    assertEquals(customer, assistant.getCustomer().get());

    service.releaseAssistant(customer, reservedAssistant.get());
    assertFalse(assistant.getCustomer().isPresent());
  }
}
