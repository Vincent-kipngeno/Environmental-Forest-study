package models;

import dbRule.DatabaseRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void getName_instantiatesCorrectlyWithId() {
        Animal newAnimal = setNewAnimal();
        assertEquals("Lion", newAnimal.getName());
    }

    @Test
    public void getType() {
        Animal newAnimal = setNewAnimal();
        assertEquals("Animal", newAnimal.getType());
    }

    @Test
    public void save_animalsAreSavedCorrectlyInDatabase() {
        Animal newAnimal = new Animal("Lion");
        newAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(newAnimal));
    }

    private Animal setNewAnimal(){
        return new Animal("Lion");
    }
}