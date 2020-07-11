package models;

public class EndangeredAnimals  extends Animal{
    private String health;
    private String age;
    public EndangeredAnimals(String name, String health, String age) {
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
