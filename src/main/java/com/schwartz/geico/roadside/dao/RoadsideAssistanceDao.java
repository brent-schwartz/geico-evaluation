package com.schwartz.geico.roadside.dao;

import com.schwartz.geico.roadside.domain.Assistant;

import java.util.List;

public interface RoadsideAssistanceDao {
  List<Assistant> findActiveAssistantsByRegionId(int regionId);

  Assistant updateAssistant(Assistant assistant);

}
