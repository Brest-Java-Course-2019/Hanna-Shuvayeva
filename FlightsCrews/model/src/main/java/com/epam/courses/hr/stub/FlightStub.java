package com.epam.courses.hr.stub;

import java.sql.Date;

public class FlightStub {

    private Integer flightStubId;

    private String flightStubName;

    private Integer flightStubNum;

    private Date flightStubDate;

    private Integer flightStubPeople;


    public Integer getFlightStubId() {
        return flightStubId;
    }

    public void setFlightStubId(Integer flightStubId) {
        this.flightStubId = flightStubId;
    }

    public FlightStub flightStubId(Integer flightStubId) {
        this.flightStubId = flightStubId;
        return this;
    }

    public String getFlightStubName() {
        return flightStubName;
    }

    public void setFlightStubName(String flightStubName) {
        this.flightStubName = flightStubName;
    }

    public FlightStub flightStubName(String flightStubName) {
        this.flightStubName = flightStubName;
        return this;
    }

    public Integer getFlightStubNum() {
        return flightStubNum;
    }

    public void setFlightStubNum(Integer flightStubNum) {
        this.flightStubNum = flightStubNum;
    }

    public FlightStub flightStubNum(Integer flightStubNum) {
        this.flightStubNum = flightStubNum;
        return this;
    }

    public Date getFlightStubDate() { return flightStubDate; }

    public void setFlightStubDate(Date flightStubDate) { this.flightStubDate = flightStubDate; }

    public FlightStub flightStubDate(Date flightStubDate) {
        this.flightStubDate = flightStubDate;
        return this;
    }

    public Integer getFlightStubPeople() { return flightStubPeople; }

    public void setFlightStubPeople(Integer flightStubPeople) { this.flightStubPeople = flightStubPeople; }

    public FlightStub flightStubPeople(Integer flightStubPeople) {
        this.flightStubPeople = flightStubPeople;
        return this;
    }


    @Override
    public String toString() {
        return "FlightStub{" +
                "flightStubId=" + flightStubId +
                ", flightStubName='" + flightStubName + '\'' +
                ", flightStubNum=" + flightStubNum + '\'' +
                ", flightStubDate=" + flightStubDate + '\'' +
                ", flightStubPeople=" + flightStubPeople + '\'' +
                '}';
    }

}
