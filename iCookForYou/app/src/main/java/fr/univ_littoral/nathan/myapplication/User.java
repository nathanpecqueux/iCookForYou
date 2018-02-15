package fr.univ_littoral.nathan.myapplication;

/**
 * Created by nathan on 07/02/18.
 */

public class User {
    private String lastName;
    private String firstName;
    private String mail;
    private String password;

    public User(String lastName, String firstName, String mail, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.mail = mail;
        this.password = password;
    }

    public User(String mail, String password) {
        this.lastName = null;
        this.firstName = null;
        this.mail = mail;
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
