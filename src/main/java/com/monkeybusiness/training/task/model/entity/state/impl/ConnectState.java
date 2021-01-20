package com.monkeybusiness.training.task.model.entity.state.impl;

import com.monkeybusiness.training.task.model.entity.LogisticBase;
import com.monkeybusiness.training.task.model.entity.Van;
import com.monkeybusiness.training.task.model.entity.state.ProductTerminalState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectState implements ProductTerminalState {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final ConnectState INSTANCE = new ConnectState();
  private LogisticBase logisticBase = LogisticBase.getInstance();

  private ConnectState() {
  }

  public static ConnectState getInstance() {
    return INSTANCE;
  }

  @Override
  public void connectVan(Van van) {
    logisticBase.connectVan(van);
    van.setState(UnloadState.getInstance());
  }
}
