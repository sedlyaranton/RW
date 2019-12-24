package com.sedlyaranton.wc.model;

import java.util.Date;

public class ReportCard {

    private int id;
    private long dateOfWork;
    private String hours;
    private String reportCardText;

    public ReportCard() { }

    public ReportCard(int id, long dateOfWork, String hours, String reportCardText){
        this.id = id;
        this.dateOfWork = dateOfWork;
        this.hours = hours;
        this.reportCardText = reportCardText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDateOfWork() {
        return dateOfWork;
    }

    public void setDateOfWork(long dateOfWork) {
        this.dateOfWork = dateOfWork;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getReportCardText() {
        return reportCardText;
    }

    public void setReportCardText(String reportCardText) {
        this.reportCardText = reportCardText;
    }
}
