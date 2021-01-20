package com.monkeybusiness.training.task.model.entity.state.impl;

import com.monkeybusiness.training.task.model.entity.LogisticBase;
import com.monkeybusiness.training.task.model.entity.Van;
import com.monkeybusiness.training.task.model.entity.state.ProductTerminalState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DisconnectState implements ProductTerminalState {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final DisconnectState INSTANCE = new DisconnectState();
  private LogisticBase logisticBase = LogisticBase.getInstance();

  private DisconnectState() {
  }

  public static DisconnectState getInstance() {
    return INSTANCE;
  }

  @Override
  public void disconnectVan(Van van) {
    logisticBase.disconnectVan(van);
    van.setState(null);
  }
}
