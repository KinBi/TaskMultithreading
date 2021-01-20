package com.monkeybusiness.training.task.model.entity.state.impl;

import com.monkeybusiness.training.task.model.entity.LogisticBase;
import com.monkeybusiness.training.task.model.entity.Van;
import com.monkeybusiness.training.task.model.entity.state.ProductTerminalState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadState implements ProductTerminalState {

  private static final Logger LOGGER = LogManager.getLogger();
  private static final LoadState INSTANCE = new LoadState();
  private LogisticBase logisticBase = LogisticBase.getInstance();

  private LoadState() {
  }

  public static LoadState getInstance() {
    return INSTANCE;
  }

  @Override
  public void loadVan(Van van) {

  }
}
