package ua.levelup.domain;

public class Item {
    private int id;
    private String name;
    private String description;
    private float price;
    public Item() {
    }
    public Item(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
