package models;

import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Animal {
    public int id;
    public String name;
    public String type;


    private static final String  DATABASE_TYPE = "Animal";

    public Animal(String name) {
        this.name = name;
        this.type = DATABASE_TYPE;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public  String getType() {
        return this.type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id &&
                Objects.equals(name, animal.name) &&
                Objects.equals(type, animal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }

    public void save () {
        String sql = "INSERT INTO animals (type, name) VALUES (:type, :name);";
        try(Connection con = DB.sql2o.open()){
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("type", this.type)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM animals;";
        try(Connection con = DB.sql2o.open()) {
           return con.createQuery(sql)
                  .executeAndFetch(Animal.class);
        }
    }

    public Animal findById () {
        String sql = "SELECT * FROM animals WHERE id = :id;";
        try (Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetchFirst(Animal.class);
        }
    }
}
