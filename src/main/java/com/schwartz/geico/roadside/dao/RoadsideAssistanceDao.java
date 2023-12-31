package com.schwartz.geico.roadside.dao;

import com.schwartz.geico.roadside.domain.Assistant;

import java.util.List;

/**
 * Provides access to roadside assistance data.
 */
public interface RoadsideAssistanceDao {
  /**
   * Finds all active assistants by region ID who have no assigned customer.
   * @param regionId the region ID
   * @return the list of active assistants in the region who have no assigned customer
   */
  List<Assistant> findActiveAssistantsWithNoCustomerByRegionId(int regionId);

  /**
   * Updates an assistant.
   * @param assistant the assistant to update
   * @return the updated assistant
   */
  Assistant updateAssistant(Assistant assistant);

}
