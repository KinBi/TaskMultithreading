package com.monkeybusiness.training.task.util.creator;

import com.monkeybusiness.training.task.model.entity.Van;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class VanCreator {
  private static final Logger LOGGER = LogManager.getLogger();
  public static final String FIELD_SEPARATOR = " ";
  public static final int PRODUCT_COUNT_FIELD_INDEX = 0;
  public static final int PERISHABLE_FIELD_INDEX = 1;

  public List<Van> create(List<String> data) {
    List<Van> vanList = new ArrayList<>();
    for (String vanData : data) {
      String[] vanDataFields = vanData.split(FIELD_SEPARATOR);
      int productCount = Integer.valueOf(vanDataFields[PRODUCT_COUNT_FIELD_INDEX]);
      boolean perishable = Boolean.getBoolean(vanDataFields[PERISHABLE_FIELD_INDEX]);
      vanList.add(new Van(productCount, perishable));
    }
    return vanList;
  }
}
