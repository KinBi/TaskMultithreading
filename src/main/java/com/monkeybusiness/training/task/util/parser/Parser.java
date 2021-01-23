package com.monkeybusiness.training.task.util.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class Parser {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final String DELIMITER = ";";

  public List<String> parseData(String data) {
    List<String> list = Arrays.asList(data.split(DELIMITER).clone());
    return list;
  }
}
