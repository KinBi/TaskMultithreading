package com.monkeybusiness.training.task.model.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final int TERMINAL_AMOUNT = 3;
  private static AtomicBoolean instanceCreated = new AtomicBoolean();
  private static Lock lock = new ReentrantLock();
  private static LogisticBase instance;
  private static Deque<ProductTerminal> productTerminalList = new ArrayDeque<>();

  private LogisticBase() {
    for (int i = 0; i < TERMINAL_AMOUNT; i++) {
      productTerminalList.add(new ProductTerminal());
    }
  }

  public static LogisticBase getInstance() {
    if (!instanceCreated.get()) {
      lock.lock();
      try {
        if (instance == null) {
          instance = new LogisticBase();
        }
        instanceCreated.set(true);
      } finally {
        lock.unlock();
      }
    }
    return instance;
  }

  public void connectVan(Van van) {
    try {
      LOGGER.info("{} id ", van.getVanId());
      lock.lock();
      Optional<ProductTerminal> terminalOptional = findFreeTerminal();
      while (!terminalOptional.isPresent()) {
        try {
          TimeUnit.SECONDS.sleep(1);
          terminalOptional = findFreeTerminal();
        } catch (InterruptedException e) {
          LOGGER.log(Level.ERROR, e);
        }
      }
      ProductTerminal terminal = terminalOptional.get();
      terminal.setVan(van);
    } finally {
      lock.unlock();
    }
  }

  public void unloadVan(Van van) {
    van.setProductCount(0);
  }

  public void disconnectVan(Van van) {
    Optional<ProductTerminal> terminalOptional = findTerminalWithCurrentVan(van);

    ProductTerminal terminal = terminalOptional.get();
    terminal.setVan(null);
  }

  public Optional<ProductTerminal> findTerminalWithCurrentVan(Van van) {
    return productTerminalList.stream().filter(productTerminal -> productTerminal.getVan() == van).findAny();
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
