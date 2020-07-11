package models;

public class Animal {
    public int id;
    public String name;
    private final String  type = "Animal";

    public Animal(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
