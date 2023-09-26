package com.schwartz.geico.roadside.service;

import com.schwartz.geico.roadside.dao.RoadsideAssistanceDao;
import com.schwartz.geico.roadside.domain.Assistant;
import com.schwartz.geico.roadside.domain.Customer;
import com.schwartz.geico.roadside.domain.Geolocation;
import com.schwartz.geico.roadside.geolocator.Geolocator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;

/**
 * Tests the RoadsideAssistanceServiceImpl class.
 */
public class RoadsideAssistanceServiceImplTest {
  @Mock
  private RoadsideAssistanceDao mockDao;

  @Mock
  private Geolocator mockGeolocator;

  @InjectMocks
  private RoadsideAssistanceServiceImpl roadsideAssistanceServiceImpl;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    roadsideAssistanceServiceImpl = new RoadsideAssistanceServiceImpl(mockGeolocator, mockDao);
  }

  @Nested
  public class UpdateAssistantLocationTests {
    @Test
    public void testUpdateAssistantLocation() {
      Assistant assistant = new Assistant();
      Geolocation geolocation = new Geolocation(40, 50);

      roadsideAssistanceServiceImpl.updateAssistantLocation(assistant, geolocation);

      assertEquals(geolocation, assistant.getCurrentLocation());

      verify(mockDao).updateAssistant(assistant);
    }
  }

  @Nested
  public class ReleaseAssistantTests {
    @Test
    public void testReleaseAssistant() {
      Customer customer = new Customer();
      Assistant assistant = new Assistant();
      assistant.setCustomer(customer);

      roadsideAssistanceServiceImpl.releaseAssistant(customer, assistant);

      assertFalse(assistant.getCustomer().isPresent());
    }
  }

  @Nested
  public class FindNearestAssistantsTests {
    private final Assistant assistant = new Assistant();
    private final List<Assistant> assistantList = new ArrayList<>();
    private final Geolocation geolocation = new Geolocation(101, 100);
    private final Geolocation geolocation2 = new Geolocation(31, 30);
    private final Geolocation geolocation3 = new Geolocation(41, 40);
    private final int regionId = 50;

    @BeforeEach
    public void setUpForFindNearestAssistantsTests() {
      assistantList.clear();
      assistant.setName("Super Man");
      assistant.setCurrentLocation(geolocation);
      when(mockGeolocator.getRegionId(any())).thenReturn(regionId);
      when(mockDao.findActiveAssistantsWithNoCustomerByRegionId(anyInt())).thenReturn(assistantList);
    }

    @Test
    public void testFindNearestAssistantsMoreThanLimit() {
      when(mockGeolocator.getDistance(geolocation, geolocation)).thenReturn(0.0);
      when(mockGeolocator.getDistance(geolocation, geolocation2)).thenReturn(1.2);
      when(mockGeolocator.getDistance(geolocation, geolocation3)).thenReturn(3.7);

      Assistant assistant2 = new Assistant();
      assistant2.setCurrentLocation(geolocation2);
      Assistant assistant3 = new Assistant();
      assistant3.setCurrentLocation(geolocation3);
      assistantList.add(assistant3);
      assistantList.add(assistant);
      assistantList.add(assistant2);

      SortedSet<Assistant> nearestAssistants = roadsideAssistanceServiceImpl.findNearestAssistants(geolocation, 2);
      assertEquals(2, nearestAssistants.size());
      assertEquals(assistant, nearestAssistants.first());
      assertEquals(assistant2, nearestAssistants.toArray()[1]);

      verify(mockGeolocator).getRegionId(geolocation);
      verify(mockDao).findActiveAssistantsWithNoCustomerByRegionId(regionId);
    }

    @Test
    public void testFindNearestAssistantsLessThanLimit() {
      when(mockGeolocator.getDistance(any(), any())).thenReturn(1.2);

      assistantList.add(assistant);

      SortedSet<Assistant> nearestAssistants = roadsideAssistanceServiceImpl.findNearestAssistants(geolocation, 2);
      assertEquals(1, nearestAssistants.size());
      assertEquals(assistant, nearestAssistants.first());

      verify(mockGeolocator).getRegionId(geolocation);
      verify(mockDao).findActiveAssistantsWithNoCustomerByRegionId(regionId);
    }

    @Test
    public void testFindNearestAssistantsAtLimit() {
      when(mockGeolocator.getDistance(geolocation, geolocation)).thenReturn(0.0);
      when(mockGeolocator.getDistance(geolocation, geolocation2)).thenReturn(1.2);

      Assistant assistant2 = new Assistant();
      assistant2.setCurrentLocation(geolocation2);
      assistantList.add(assistant);
      assistantList.add(assistant2);

      SortedSet<Assistant> nearestAssistants = roadsideAssistanceServiceImpl.findNearestAssistants(geolocation, 2);
      assertEquals(2, nearestAssistants.size());
      assertEquals(assistant, nearestAssistants.first());
      assertEquals(assistant2, nearestAssistants.toArray()[1]);

      verify(mockGeolocator).getRegionId(geolocation);
      verify(mockDao).findActiveAssistantsWithNoCustomerByRegionId(regionId);
    }

    @Test
    public void testFindNearestAssistantsNoneFound() {
      SortedSet<Assistant> nearestAssistants = roadsideAssistanceServiceImpl.findNearestAssistants(geolocation, 2);
      assertEquals(0, nearestAssistants.size());

      verify(mockGeolocator).getRegionId(geolocation);
      verify(mockDao).findActiveAssistantsWithNoCustomerByRegionId(regionId);
    }

  }

  @Nested
  public class ReserveAssistantTests {
    @Test
    public void testReserveAssistant() {
      Customer customer = new Customer();
      customer.setName("Charles Xavier");
      Geolocation geolocation = new Geolocation(100, 102);
      Assistant assistant = new Assistant();
      assistant.setName("Jean Grey");
      List<Assistant> assistantList = new ArrayList<>();
      assistantList.add(assistant);
      int regionId = 50;

      when(mockGeolocator.getRegionId(any())).thenReturn(regionId);
      when(mockGeolocator.getDistance(any(), any())).thenReturn(1.2);
      when(mockDao.findActiveAssistantsWithNoCustomerByRegionId(anyInt())).thenReturn(assistantList);
      when(mockDao.updateAssistant(any())).thenReturn(assistant);

      Optional<Assistant> reservedAssistant = roadsideAssistanceServiceImpl.reserveAssistant(customer, geolocation);
      assertTrue(reservedAssistant.isPresent());
      assertEquals(assistant.getName(), reservedAssistant.get().getName());
      assertTrue(reservedAssistant.get().getCustomer().isPresent());
      assertEquals(customer, reservedAssistant.get().getCustomer().get());

      verify(mockGeolocator).getRegionId(geolocation);
      verify(mockDao).findActiveAssistantsWithNoCustomerByRegionId(regionId);
      verify(mockDao).updateAssistant(assistant);
    }

    @Test
    public void testReserveAssistantNoneFound() {
      Customer customer = new Customer();
      Geolocation geolocation = new Geolocation(100, 102);
      List<Assistant> assistantList = new ArrayList<>();
      int regionId = 50;

      when(mockGeolocator.getRegionId(any())).thenReturn(regionId);
      when(mockDao.findActiveAssistantsWithNoCustomerByRegionId(anyInt())).thenReturn(assistantList);

      Optional<Assistant> reservedAssistant = roadsideAssistanceServiceImpl.reserveAssistant(customer, geolocation);
      assertFalse(reservedAssistant.isPresent());

      verify(mockGeolocator).getRegionId(geolocation);
      verify(mockDao).findActiveAssistantsWithNoCustomerByRegionId(regionId);
      verify(mockDao, never()).updateAssistant(any());
    }
  }
}
