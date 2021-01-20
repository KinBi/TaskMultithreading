package com.monkeybusiness.training.task.model.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
  private static final Logger LOGGER = LogManager.getLogger();
  public static int TERMINAL_AMOUNT = 3;
  private static final LogisticBase instance = new LogisticBase();
  private ArrayList<ProductTerminal> productTerminalList = new ArrayList<>();
  private Deque<ProductTerminal> freeTerminalDeque = new ArrayDeque<>();
  private Lock lock = new ReentrantLock();
  private Condition isFree = lock.newCondition();

  private LogisticBase() {
    for (int i = 0; i < TERMINAL_AMOUNT; i++) {
      freeTerminalDeque.add(new ProductTerminal());
    }
  }

  public static LogisticBase getInstance() {
    return instance;
  }

  public boolean isTerminalAvailable() {
    boolean isAvailable = ((productTerminalList.size() < TERMINAL_AMOUNT) && freeTerminalDeque.isEmpty());
    return isAvailable;
  }

  public void connectVan(Van van) {
    try {
      lock.tryLock();
      if (isTerminalAvailable()) {
        ProductTerminal terminal = freeTerminalDeque.poll();
        productTerminalList.add(terminal);
        terminal.setVan(van);
      }
      lock.lock();
    } finally {
      lock.unlock();
    }
  }

//  public void loadVan(Van van) {
//  }

  public void unloadVan(Van van) {
    try {
      lock.tryLock();
      van.setProductCount(0);
    } finally {
      lock.unlock();
    }
  }

  public void disconnectVan(Van van) {
    try {
      lock.tryLock();
      Optional<ProductTerminal> terminalOptional = getTerminalWithCurrentVan(van);
      if (terminalOptional.isPresent()) {
        ProductTerminal terminal = terminalOptional.get();
        terminal.setVan(null);
        productTerminalList.remove(terminal);
        freeTerminalDeque.add(terminal);
      }
      isFree.signal();
    } finally {
      lock.unlock();
    }
  }

  public Optional<ProductTerminal> getTerminalWithCurrentVan(Van van) {
    Optional<ProductTerminal> result = Optional.empty();
    int index = 0;
    while (index < productTerminalList.size()) {
      if (productTerminalList.get(index).getVan().equals(van)) {
        result = Optional.of(productTerminalList.get(index));
        break;
      }
    }
    return result;
  }

  @Override
  public String toString() {
    return productTerminalList.toString();
  }
}
