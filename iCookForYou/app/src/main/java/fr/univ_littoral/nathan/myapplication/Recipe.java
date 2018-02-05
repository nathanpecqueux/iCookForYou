package fr.univ_littoral.nathan.myapplication;

/**
 * Created by Alex on 02/02/2018.
 */

import java.util.ArrayList;

        import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String title, thumbnailUrl;
    private int year;
    private double rating;
    private ArrayList<String> genre;

    public Recipe() {
    }

    public Recipe(String name, String thumbnailUrl, double rating,
                 ArrayList<String> Steps, List<double> calories) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.rating = rating;
    }
}