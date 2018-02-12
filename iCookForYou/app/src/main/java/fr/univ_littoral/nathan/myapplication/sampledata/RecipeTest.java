package fr.univ_littoral.nathan.myapplication.sampledata;

/**
 * Created by Alex on 02/02/2018.
 */

import java.util.ArrayList;

public class RecipeTest {
    private String name, thumbnailUrl;
    private double rating;
    private ArrayList<String> steps;
    private ArrayList<Double> calories;

    public RecipeTest() {
    }

    public RecipeTest(String name, String thumbnailUrl, double rating,
                  ArrayList<String> Steps, ArrayList<Double> calories, ArrayList<Ingredients> ingredients) {
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
        this.rating = rating;
        this.steps = Steps;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public ArrayList<Double> getCalories() {
        return calories;
    }

    public void setCalories(ArrayList<Double> calories) {
        this.calories = calories;
    }
}