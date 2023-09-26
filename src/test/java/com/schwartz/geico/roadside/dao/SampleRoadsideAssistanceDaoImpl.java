package com.schwartz.geico.roadside.dao;

import com.schwartz.geico.roadside.domain.Assistant;
import com.schwartz.geico.roadside.domain.Customer;
import com.schwartz.geico.roadside.domain.Geolocation;

import java.util.*;

/**
 * Sample implementation of RoadsideAssistanceDao.  This implementation uses in-memory data and includes sample data.
 */
public class SampleRoadsideAssistanceDaoImpl implements RoadsideAssistanceDao {
  private final Map<Integer, List<UUID>> assistantsByRegionId = new HashMap<>();
  private final Map<UUID, Assistant> allAssistants = new HashMap<>();

  /**
   * Constructs a new SampleRoadsideAssistanceDao.
   */
  public SampleRoadsideAssistanceDaoImpl() {
    addSampleData();
  }

  /**
   * Adds sample data to the data store.
   */
  private void addSampleData() {
    Assistant assistant1 = new Assistant();
    assistant1.setCurrentLocation(new Geolocation(40, 50));
    assistant1.setName("Iron Man");
    assistant1.setActive(true);
    this.allAssistants.put(assistant1.getId(), assistant1);

    Assistant assistant2 = new Assistant();
    assistant2.setCurrentLocation(new Geolocation(42, 52));
    assistant2.setName("Wonder Woman");
    assistant2.setActive(false);
    this.allAssistants.put(assistant2.getId(), assistant2);

    Assistant assistant3 = new Assistant();
    assistant3.setCurrentLocation(new Geolocation(40, 54));
    assistant3.setName("The Flash");
    assistant3.setActive(true);
    this.allAssistants.put(assistant3.getId(), assistant3);

    this.assistantsByRegionId.put(15, List.of(assistant1.getId(), assistant2.getId(), assistant3.getId()));

    Assistant assistant4 = new Assistant();
    assistant4.setCurrentLocation(new Geolocation(20, 21));
    assistant4.setName("Incredible Hulk");
    assistant4.setActive(true);
    this.allAssistants.put(assistant4.getId(), assistant4);

    Assistant assistant5 = new Assistant();
    assistant5.setCurrentLocation(new Geolocation(22, 23));
    assistant5.setName("Miles Morales");
    assistant5.setActive(true);
    assistant5.setCustomer(new Customer());
    this.allAssistants.put(assistant5.getId(), assistant4);

    this.assistantsByRegionId.put(16, List.of(assistant4.getId(), assistant5.getId()));

  }

  /**
   * @see RoadsideAssistanceDao#findActiveAssistantsWithNoCustomerByRegionId(int)
   */
  @Override
  public List<Assistant> findActiveAssistantsWithNoCustomerByRegionId(int regionId) {
    List<UUID> assistantIds = this.assistantsByRegionId.get(regionId);
    List<Assistant> assistants = new ArrayList<>();
    if (assistantIds != null) {
      assistants = assistantIds.stream().map(this.allAssistants::get)
          .filter(Objects::nonNull)
          .filter(Assistant::isActive)
          .filter(assistant -> assistant.getCustomer().isEmpty())
          .toList();
    }
    return assistants;
  }

  /**
   * @see RoadsideAssistanceDao#updateAssistant(Assistant)
   */
  @Override
  public Assistant updateAssistant(Assistant assistant) {
    allAssistants.put(assistant.getId(), assistant);
    return assistant;
  }
}
