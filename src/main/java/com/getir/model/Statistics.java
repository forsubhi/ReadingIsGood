package com.getir.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "statistics")
@Immutable
public class Statistics {
    @Id
    private String month;
    Integer totalPurchasedAmount;
    Integer totalBookCount;
    Integer totalOrderCount;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getTotalPurchasedAmount() {
        return totalPurchasedAmount;
    }

    public void setTotalPurchasedAmount(Integer totalPurchasedAmount) {
        this.totalPurchasedAmount = totalPurchasedAmount;
    }

    public Integer getTotalBookCount() {
        return totalBookCount;
    }

    public void setTotalBookCount(Integer totalBookCount) {
        this.totalBookCount = totalBookCount;
    }

    public Integer getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(Integer totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }
}
