package com.monkeybusiness.training.task.model.entity;

import com.monkeybusiness.training.task.model.entity.state.ProductTerminalState;
import com.monkeybusiness.training.task.model.entity.state.impl.ConnectState;
import com.monkeybusiness.training.task.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Van extends Thread {
  private static final Logger LOGGER = LogManager.getLogger();
  private long vanId = IdGenerator.getVanId();
  private int productCount;
  private boolean perishable;
  private ProductTerminalState state;

  public Van() {
  }

  public Van(int productCount, boolean perishable) {
    this.productCount = productCount;
    this.perishable = perishable;
  }

  @Override
  public void run() {
    if (perishable) {
      setPriority(Thread.MAX_PRIORITY);
    }
    this.setState(ConnectState.getInstance());
    state.connectVan(this);
    LOGGER.log(Level.INFO, "Van {} connected and has {} products", vanId, productCount);
    state.unloadVan(this);
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    LOGGER.log(Level.INFO, "Van {} unloaded", vanId);
    state.disconnectVan(this);
    LOGGER.log(Level.INFO, "Van {} disconnected", vanId);
    LOGGER.log(Level.INFO, "Van {} has {} products now", vanId, productCount);
  }

  public long getVanId() {
    return vanId;
  }

  public int getProductCount() {
    return productCount;
  }

  public void setProductCount(int productCount) {
    this.productCount = productCount;
  }

  public boolean isPerishable() {
    return perishable;
  }

  public void setPerishable(boolean perishable) {
    this.perishable = perishable;
  }

  public void setState(ProductTerminalState state) {
    this.state = state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Van van = (Van) o;

    if (productCount != van.productCount) return false;
    return perishable == van.perishable;
  }

  @Override
  public int hashCode() {
    int result = productCount;
    result = 31 * result + (perishable ? 1 : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Van{");
    sb.append("productCount=").append(productCount);
    sb.append(", perishable=").append(perishable);
    sb.append('}');
    return sb.toString();
  }
}
