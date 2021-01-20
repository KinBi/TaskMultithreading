package com.monkeybusiness.training.task.model.entity;

import com.monkeybusiness.training.task.util.IdGenerator;

public class ProductTerminal {
  private long productTerminalId = IdGenerator.getProductTerminalId();
  private Van van;

  public long getProductTerminalId() {
    return productTerminalId;
  }

  public Van getVan() {
    return van;
  }

  public void setVan(Van van) {
    this.van = van;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ProductTerminal that = (ProductTerminal) o;

    if (productTerminalId != that.productTerminalId) return false;
    return van != null ? van.equals(that.van) : that.van == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (productTerminalId ^ (productTerminalId >>> 32));
    result = 31 * result + (van != null ? van.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ProductTerminal{");
    sb.append("productTerminalId=").append(productTerminalId);
    sb.append(", van=").append(van);
    sb.append('}');
    return sb.toString();
  }
}
