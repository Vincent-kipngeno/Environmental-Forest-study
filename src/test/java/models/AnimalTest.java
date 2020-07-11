package models;

import dbRule.DatabaseRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void newAnimal_instantiatesCorrectly() {
        Animal newAnimal = setNewAnimal();
        assertEquals(true, newAnimal instanceof Animal);
    }

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
    public void equal_returnsTrueIfNamesAreEqual() {
        Animal newAnimal = setNewAnimal();
        Animal otherAnimal = new Animal("Lion");
        assertTrue(newAnimal.equals(otherAnimal));
    }

    @Test
    public void save_animalsAreSavedCorrectlyInDatabase() {
        Animal newAnimal = new Animal("Lion");
        newAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(newAnimal));
    }

    @Test
    public void all_returnsAllInstancesOfAnimal() {
        Animal firstAnimal = setNewAnimal();
        firstAnimal.save();
        Animal secondAnimal = new Animal("Leopard");
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }

    @Test
    public void findById_correctInstanceOfAnimalIsReturnedById() {
        Animal newAnimal = new Animal("Lion");
        newAnimal.save();
        Animal savedAnimal = newAnimal.findById();
        assertEquals(newAnimal, savedAnimal);
    }

    private Animal setNewAnimal(){
        return new Animal("Lion");
    }
}