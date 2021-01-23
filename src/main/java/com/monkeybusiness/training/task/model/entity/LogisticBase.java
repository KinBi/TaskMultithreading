package com.monkeybusiness.training.task.model.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
  private static final Logger LOGGER = LogManager.getLogger();
  public static final int TERMINAL_AMOUNT = 3;
  private static AtomicBoolean instanceCreated = new AtomicBoolean();
  private static Lock lock = new ReentrantLock(true);
  private static LogisticBase instance;
  private List<ProductTerminal> productTerminalList = new ArrayList<>();

  private LogisticBase() {
    for (int i = 0; i < TERMINAL_AMOUNT; i++) {
      productTerminalList.add(new ProductTerminal());
    }
    instanceCreated.set(true);
  }

  public static LogisticBase getInstance() {
    if (!instanceCreated.get()) {
      lock.lock();
      try {
        if (instance == null) {
          instance = new LogisticBase();
        }
      } finally {
        lock.unlock();
      }
    }
    return instance;
  }

  public void connectVan(Van van) {
    try {
      LOGGER.info("{} id ",van.getVanId());
      lock.lock();
      Optional<ProductTerminal> terminalOptional = findFreeTerminal();
      if (terminalOptional.isPresent()) {
        ProductTerminal terminal = terminalOptional.get();
        terminal.setVan(van);
      }
      lock.lock();
    } finally {
      lock.unlock();
    }
  }

  public void unloadVan(Van van) {
    try {
      lock.lock();
      van.setProductCount(0);
    } finally {
      lock.unlock();
    }
  }

  public void disconnectVan(Van van) {
    try {
      lock.lock();
      Optional<ProductTerminal> terminalOptional = findTerminalWithCurrentVan(van);
      if (terminalOptional.isPresent()) {
        ProductTerminal terminal = terminalOptional.get();
        terminal.setVan(null);
      }
    } finally {
      lock.unlock();
    }
  }

  public Optional<ProductTerminal> findTerminalWithCurrentVan(Van van) {
    Optional<ProductTerminal> terminalOptional = Optional.empty();
    int index = 0;
    while (index < productTerminalList.size()) {
      ProductTerminal terminal = productTerminalList.get(index);
      if (terminal.getVan() == van || terminal.getVan().equals(van)) {
        terminalOptional = Optional.of(terminal);
        break;
      }
    }
    return terminalOptional;
  }

  public Optional<ProductTerminal> findFreeTerminal() {
    Optional<ProductTerminal> terminalOptional = findTerminalWithCurrentVan(null);
    return terminalOptional;
  }

  @Override
  public String toString() {
    return productTerminalList.toString();
  }
}
