package com.monkeybusiness.training.task.util;

public class IdGenerator {
  private static long vanId = 0;
  private static long productTerminalId = 0;

  private IdGenerator() {
  }

  public static long getVanId() {
    return vanId++;
  }

  public static long getProductTerminalId() {
    return productTerminalId++;
  }
}
