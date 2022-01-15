package com.bayzdelivery.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ozgurokka on 1/15/22 10:13 AM
 */
public class ResponseModelTopDelivery {


    public double totalCommision = 0.0;
    public double avarageCommision = 0.0;

    public List<Person> topDeliverMan = new ArrayList<>();

    public double getAvarageCommision() {
        return avarageCommision;
    }

    public void setAvarageCommision(double avarageCommision) {
        this.avarageCommision = avarageCommision;
    }

    public List<Person> getTopDeliverMan() {
        return topDeliverMan;
    }

    public void setTopDeliverMan(List<Person> topDeliverMan) {
        this.topDeliverMan = topDeliverMan;
    }

    public double getTotalCommision() {
        return totalCommision;
    }

    public void addTotalCommision(BigDecimal commission) {
        this.totalCommision += commission.doubleValue();
        setAvarageCommision(totalCommision / topDeliverMan.size());
    }

    public void setTotalCommision(double totalCommision) {
        this.totalCommision = totalCommision;
    }
}
