package com.example.seniordesign;

public class HospitalsModel {

    private String HospitalEmail;
    private String HospitalName;
    private String HospitalLocation;

    private HospitalsModel(){}

    private HospitalsModel(String HospitalEmail, String HospitalName, String HospitalLocation){
        this.HospitalEmail=HospitalEmail;
        this.HospitalName=HospitalName;
        this.HospitalLocation=HospitalLocation;

    }

    public String getHospitalEmail() {
        return HospitalEmail;
    }

    public void setHospitalEmail(String hospitalEmail) {
        this.HospitalEmail = hospitalEmail;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.HospitalName = hospitalName;
    }

    public String getHospitalLocation() {
        return HospitalLocation;
    }

    public void setHospitalLocation(String hospitalLocation) {
        this.HospitalLocation = hospitalLocation;
    }
}

