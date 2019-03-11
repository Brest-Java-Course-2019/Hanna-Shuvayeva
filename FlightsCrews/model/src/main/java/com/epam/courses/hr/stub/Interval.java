package com.epam.courses.hr.stub;

import java.sql.Date;


public class Interval {

    private Date dateFrom;

    private Date dateTo;

    public Interval(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Interval() {
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +'\'' +
                '}';
    }

}
