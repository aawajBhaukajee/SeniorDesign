package com.example.seniordesign;

public class UsersModel {

    private String EmailAddress, FullName, MaximumBP, MinimumBP;

    private UsersModel(){}

    private UsersModel(String EmailAddress, String FullName, String MaximumBP, String MinimumBP){

        this.EmailAddress=EmailAddress;
        this.FullName=FullName;
        this.MaximumBP=MaximumBP;
        this.MinimumBP=MinimumBP;

    }

    public String getEmailAddress()
    {
        return EmailAddress;
    }

    public void setEmailAddress(String EmailAddress) {
        this.EmailAddress = EmailAddress;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getMaximumBP() {
        return MaximumBP;
    }

    public void setMaximumBP(String MaximumBP) {
        this.MaximumBP = MaximumBP;
    }

    public String getMinimumBP() {
        return MinimumBP;
    }

    public void setMinimumBP(String MinimumBP) {
        this.MinimumBP = MinimumBP;
    }
}
