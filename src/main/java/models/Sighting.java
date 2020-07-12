package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.sql.Timestamp;
import java.util.Objects;

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

    public String getRangerName() { return rangerName; }

    public int getAnimalId() {
        return animalId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return id == sighting.id &&
                animalId == sighting.animalId &&
                Objects.equals(location, sighting.location) &&
                Objects.equals(rangerName, sighting.rangerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, rangerName, animalId);
    }

    public void save() {
        String sql = "INSERT INTO sightings (location, ranger_name, animal_id) VALUES (:location, :ranger_name, :animal_id);";
        try(Connection con = DB.sql2o.open()) {
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("location", this.getLocation())
                    .addParameter("ranger_name", this.getRangerName())
                    .addParameter("animal_id", this.getAnimalId())
                    .executeUpdate()
                    .getKey();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
