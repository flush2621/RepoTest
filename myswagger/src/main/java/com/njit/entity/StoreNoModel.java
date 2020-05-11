package com.njit.entity;

import lombok.Data;

@Data
public class StoreNoModel {
  private Integer oldStoreNo;

  public Integer getOldStoreNo() {
    return oldStoreNo;
  }

  public void setOldStoreNo(Integer oldStoreNo) {
    this.oldStoreNo = oldStoreNo;
  }
}
