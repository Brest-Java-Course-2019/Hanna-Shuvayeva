package com.epam.courses.hr.model;

public class Crew {


    private Integer crewId;

    private String functionName;

    private String fullName;

    private Integer crewNum;

    public Crew(Integer crewId, String functionName, String fullName, Integer crewNum) {
        this.crewId=crewId;
        this.functionName = functionName;
        this.fullName = fullName;
        this.crewNum = crewNum;
    }

    public Crew() {
    }

    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getCrewNum() {
        return crewNum;
    }

    public void setCrewNum(Integer crewNum) {
        this.crewNum = crewNum;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "crewId=" + crewId +
                ", functionName=" + functionName +'\'' +
                ", fullName='" + fullName + '\'' +
                ", crewNum=" + crewNum +
                '}';
    }

}