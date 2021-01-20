package com.monkeybusiness.training.task.model.entity.state;

import com.monkeybusiness.training.task.model.entity.Van;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ProductTerminalState {
  Logger LOGGER = LogManager.getLogger();

  default void connectVan(Van van) {
    LOGGER.log(Level.WARN, "Sore no");
  }

  default void loadVan(Van van) {
    LOGGER.log(Level.WARN, "Sore no");
  }

  default void unloadVan(Van van) {
    LOGGER.log(Level.WARN, "Sore no");
  }

  default void disconnectVan(Van van) {
    LOGGER.log(Level.WARN, "Sore no");
  }
}
