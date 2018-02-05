package fr.univ_littoral.nathan.myapplication.sampledata;

/**
 * Created by alexd on 05/02/2018.
 */

public class Ingredients {
    private String id;
    private String unit;
    private String name;
    private int quantity;
    private int EANcode;
    private int categoryId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getEANcode() {
        return EANcode;
    }

    public void setEANcode(int EANcode) {
        this.EANcode = EANcode;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
