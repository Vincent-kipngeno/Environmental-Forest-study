package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {

    @Test
    public void getId_instantiatesCorrectlyWithId() {
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

    public Animal setNewAnimal(){
        return new Animal("Lion");
    }
}