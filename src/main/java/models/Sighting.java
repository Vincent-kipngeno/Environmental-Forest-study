package models;

import java.sql.Timestamp;

public class Sighting {
    private int id;
    private String location;
    private String rangerName;
    private int animalId;
    private Timestamp createdAt;

    public Sighting( String location, String rangerName, int animalId) {
        this.location = location;
        this.rangerName = rangerName;
        this.animalId = animalId;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public int getAnimalId() {
        return animalId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
