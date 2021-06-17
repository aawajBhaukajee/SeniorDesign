package com.example.seniordesign;

public class UsersModel {

    private String EmailAddress, FullName, MaximumBP, MinimumBP, BloodType, Age, Weight;

    private UsersModel(){}

    private UsersModel(String EmailAddress, String FullName, String MaximumBP, String MinimumBP, String BloodType, String Age, String Weight){

        this.EmailAddress=EmailAddress;
        this.FullName=FullName;
        this.MaximumBP=MaximumBP;
        this.MinimumBP=MinimumBP;
        this.BloodType=BloodType;
        this.Age=Age;
        this.Weight=Weight;

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

    public String getBloodType()
    {
        return BloodType;
    }

    public void setBloodType(String BloodType) {
        this.BloodType = BloodType;
    }

    public String getAge()
    {
        return Age;
    }

    public void setAge(String Age) { this.Age = Age;}

    public String getWeight()
    {
        return Weight;
    }

    public void setWeight(String Weight) {
        this.Weight = Weight;
    }
    }

