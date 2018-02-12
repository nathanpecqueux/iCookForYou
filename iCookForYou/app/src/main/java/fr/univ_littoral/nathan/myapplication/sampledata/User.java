package fr.univ_littoral.nathan.myapplication.sampledata;

import java.util.EnumSet;

/**
 * Created by alexd on 05/02/2018.
 */

public class User {
    private int userId;
    private String lastName;
    private String firstName;
    private String mail;
    private String password;
    private double weight;
    private int size;
    private int age;
    private enum sex{man,woman}
    private enum physicalActivity{sedentary, active, athlete}
    private EnumSet<sex> enumSetSex = EnumSet.allOf(sex.class);
    private EnumSet<physicalActivity> enumSetPhysical = EnumSet.allOf(physicalActivity.class);

    public EnumSet<physicalActivity> getEnumSetPhysical() {
        return enumSetPhysical;
    }

    public void setEnumSetPhysical(EnumSet<physicalActivity> enumSetSPhysical) {
        this.enumSetPhysical = enumSetSPhysical;
    }

    public EnumSet<sex> getEnumSetSex() {
        return enumSetSex;
    }

    public void setEnumSetSex(EnumSet<sex> enumSetSex) {
        this.enumSetSex = enumSetSex;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
