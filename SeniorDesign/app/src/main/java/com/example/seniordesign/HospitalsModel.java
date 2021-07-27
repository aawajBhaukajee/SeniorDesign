package com.example.seniordesign;

import java.util.Comparator;

public class HospitalsModel {

     String HospitalEmail;
     String HospitalName;
    String HospitalLocation;
    String userId;

    public HospitalsModel(){}

    public HospitalsModel(String HospitalEmail, String HospitalName, String HospitalLocation,String userId){
        this.HospitalEmail=HospitalEmail;
        this.HospitalName=HospitalName;
        this.HospitalLocation=HospitalLocation;
        this.userId = userId;

    }

    public static Comparator<HospitalsModel> hospitalSort = new Comparator<HospitalsModel>() {
        @Override
        public int compare(HospitalsModel o1, HospitalsModel o2) {
            return o1.getHospitalName().compareTo(o2.getHospitalName());
        }
    };


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
    public String getuserId() {
        return userId;
    }
    public void setuserId(String userId) {
        this.userId = userId;

    }
}

