package models;

import dbRule.DatabaseRule;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void newEndangeredAnimal_newEndangeredAnimalInstantiatesCorrectly() {
        EndangeredAnimal  firstEndangeredAnimal = setNewEndangeredAnimal();
        assertEquals(true, firstEndangeredAnimal instanceof EndangeredAnimal);
    }

    @Test
    public void getHealth() {
        EndangeredAnimal  firstEndangeredAnimal = setNewEndangeredAnimal();
        assertEquals("okay", firstEndangeredAnimal.getHealth());
    }

    @Test
    public void getAge() {
        EndangeredAnimal  firstEndangeredAnimal = setNewEndangeredAnimal();
        assertEquals("old", firstEndangeredAnimal.getAge());
    }

    private EndangeredAnimal setNewEndangeredAnimal() {
        return new EndangeredAnimal("Rhino", "okay", "old");
    }
}