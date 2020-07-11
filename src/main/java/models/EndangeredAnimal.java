package models;

public class EndangeredAnimal extends Animal{
    private String health;
    private String age;
    public EndangeredAnimal(String name, String health, String age) {
        super(name);
        this.health = health;
        this.age = age;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }
}
