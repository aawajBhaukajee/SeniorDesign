package com.example.seniordesign;

public class HospitalsModel {

    private String HospitalEmail;
    private String HospitalName;

    private HospitalsModel(){}

    private HospitalsModel(String HospitalEmail, String HospitalName){
        this.HospitalEmail=HospitalEmail;
        this.HospitalName=HospitalName;

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
}

