package com.monkeybusiness.training.task.model.entity.state.impl;

import com.monkeybusiness.training.task.model.entity.LogisticBase;
import com.monkeybusiness.training.task.model.entity.Van;
import com.monkeybusiness.training.task.model.entity.state.ProductTerminalState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnloadState implements ProductTerminalState {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final UnloadState INSTANCE = new UnloadState();
  private LogisticBase logisticBase = LogisticBase.getInstance();

  private UnloadState() {
  }

  public static UnloadState getInstance() {
    return INSTANCE;
  }

  @Override
  public void unloadVan(Van van) {
    logisticBase.unloadVan(van);
    van.setState(DisconnectState.getInstance());
  }
}
