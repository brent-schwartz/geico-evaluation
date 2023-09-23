package com.schwartz.geico.roadside.dao;

import com.schwartz.geico.roadside.domain.Assistant;
import com.schwartz.geico.roadside.domain.Geolocation;

import java.util.*;

/**
 * Sample implementation of RoadsideAssistanceDao.  This implementation uses in-memory data and includes sammple data.
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
    assistant1.setName("John Smith");
    assistant1.setActive(true);
    this.allAssistants.put(assistant1.getId(), assistant1);

    Assistant assistant2 = new Assistant();
    assistant2.setCurrentLocation(new Geolocation(42, 52));
    assistant2.setName("Chris Jones");
    assistant2.setActive(false);
    this.allAssistants.put(assistant2.getId(), assistant2);

    Assistant assistant3 = new Assistant();
    assistant3.setCurrentLocation(new Geolocation(40, 54));
    assistant3.setName("Jane Doe");
    assistant3.setActive(true);
    this.allAssistants.put(assistant3.getId(), assistant3);

    this.assistantsByRegionId.put(15, List.of(assistant1.getId(), assistant2.getId(), assistant3.getId()));

    Assistant assistant4 = new Assistant();
    assistant4.setCurrentLocation(new Geolocation(20, 21));
    assistant4.setName("Sally Jones");
    assistant4.setActive(true);
    this.allAssistants.put(assistant4.getId(), assistant4);

    this.assistantsByRegionId.put(16, List.of(assistant4.getId()));

  }

  /**
   * @see RoadsideAssistanceDao#findActiveAssistantsByRegionId(int)
   */
  @Override
  public List<Assistant> findActiveAssistantsByRegionId(int regionId) {
    List<UUID> assistantIds = this.assistantsByRegionId.get(regionId);
    List<Assistant> assistants = new ArrayList<>();
    if (assistantIds != null) {
      assistants = assistantIds.stream().map(this.allAssistants::get)
          .filter(Objects::nonNull)
          .filter(Assistant::isActive)
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
