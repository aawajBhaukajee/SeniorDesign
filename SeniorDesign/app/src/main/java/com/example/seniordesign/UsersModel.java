package com.example.seniordesign;

import java.util.Comparator;

public class UsersModel {

    String EmailAddress, FullName, MaximumBP, MinimumBP, BloodType, Age, Weight;

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
    public static Comparator<UsersModel>ageSort = new Comparator<UsersModel>() {
        @Override
        public int compare(UsersModel o1, UsersModel o2) {
            return o1.getAge().compareTo(o2.getAge());
        }
    };

    public static Comparator<UsersModel>sortName = new Comparator<UsersModel>() {
        @Override
        public int compare(UsersModel o1, UsersModel o2) {
            return o1.getFullName().compareTo(o2.getFullName());
        }
    };

    public static Comparator<UsersModel>sortLBP = new Comparator<UsersModel>() {
        @Override
        public int compare(UsersModel o1, UsersModel o2) {
            return o1.getMinimumBP().compareTo(o2.getMinimumBP());
        }
    };

    public static Comparator<UsersModel>sortHBP = new Comparator<UsersModel>() {
        @Override
        public int compare(UsersModel o1, UsersModel o2) {
            return o1.getMaximumBP().compareTo(o2.getMaximumBP());
        }
    };

    public static Comparator<UsersModel>sortBloodType = new Comparator<UsersModel>() {
        @Override
        public int compare(UsersModel o1, UsersModel o2) {
            return o1.getBloodType().compareTo(o2.getBloodType());
        }
    };

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

