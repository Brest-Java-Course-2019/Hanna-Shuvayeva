package com.epam.courses.hr.model;

import java.sql.Date;


public class Flight {


    private Integer flightId;

    private String flightName;

    private Integer crewNum;

    private Date departureDate;

    public Flight(Integer flightId, String flightName, Integer crewNum, Date departureDate) {
        this.flightId=flightId;
        this.flightName = flightName;
        this.crewNum = crewNum;
        this.departureDate = departureDate;
    }

    public Flight() {
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public Integer getCrewNum () {
        return crewNum;
    }

    public void setCrewNum(Integer crewNum) {
        this.crewNum = crewNum;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightName='" + flightName + '\'' +
                ", crewNum='" + crewNum + '\'' +
                ", departureDate='" + departureDate + '\'' +
                '}';
    }
}