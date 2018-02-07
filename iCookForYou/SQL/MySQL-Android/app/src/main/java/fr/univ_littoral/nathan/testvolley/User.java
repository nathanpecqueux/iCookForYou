package fr.univ_littoral.nathan.testvolley;

/**
 * Created by nathan on 07/02/18.
 */

public class User {
    private int idUser;
    private String lastName;
    private String firstName;

    public User(int idUser, String lastName, String firstName) {
        this.idUser = idUser;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
