package com.example.seniordesign;

public class HospitalsModel {

     String HospitalEmail;
     String HospitalName;
    String HospitalLocation;

    public HospitalsModel(){}

    public HospitalsModel(String HospitalEmail, String HospitalName, String HospitalLocation){
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

