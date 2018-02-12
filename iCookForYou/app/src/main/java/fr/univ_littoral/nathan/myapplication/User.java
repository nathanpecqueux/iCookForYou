package fr.univ_littoral.nathan.myapplication;

/**
 * Created by nathan on 07/02/18.
 */

public class User {
    private String mail;
    private String password;

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
