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
    public void getName_instantiatesCorrectlyWithId() {
        Animal newEndangeredAnimal = setNewEndangeredAnimal();
        assertEquals("Rhino", newEndangeredAnimal.getName());
    }
    @Test
    public void getHealth_newEndangeredAnimalInstantiatesCorrectlyWithAge() {
        EndangeredAnimal  firstEndangeredAnimal = setNewEndangeredAnimal();
        assertEquals("okay", firstEndangeredAnimal.getHealth());
    }

    @Test
    public void getAge_newEndangeredAnimalInstantiatesCorrectlyWithAge() {
        EndangeredAnimal  firstEndangeredAnimal = setNewEndangeredAnimal();
        assertEquals("old", firstEndangeredAnimal.getAge());
    }

    @Test
    public void getType_newEndangeredAnimalInstantiatesCorrectlyWithType() {
        EndangeredAnimal  firstEndangeredAnimal = setNewEndangeredAnimal();
        assertEquals("Endangered Animals", firstEndangeredAnimal.getType());
    }

    @Test
    public void equal_endangeredAnimalsWithSameNameHealthAndAge() {
        EndangeredAnimal  firstEndangeredAnimal = setNewEndangeredAnimal();
        EndangeredAnimal  secondEndangeredAnimal = setNewEndangeredAnimal();
        assertTrue( firstEndangeredAnimal.equals(secondEndangeredAnimal));
    }

    @Test
    public void save_endangeredAnimalIsSavedCorrectlyOnEntry() {
        EndangeredAnimal  firstEndangeredAnimal = setNewEndangeredAnimal();
        firstEndangeredAnimal.save();
        assertEquals(true, EndangeredAnimal.allEndangered().get(0).equals(firstEndangeredAnimal));
    }

    @Test
    public void save_assignsIdToEndangeredAnimal() {
        EndangeredAnimal  firstEndangeredAnimal = setNewEndangeredAnimal();
        firstEndangeredAnimal.save();
        EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.allEndangered().get(0);
        assertEquals(savedEndangeredAnimal.getId(), firstEndangeredAnimal.getId());
    }

    @Test
    public void allEndangered_allSavedEndangeredAnimalsReturnedCorrectly() {
        EndangeredAnimal  firstEndangeredAnimal = setNewEndangeredAnimal();
        firstEndangeredAnimal.save();
        EndangeredAnimal  secondEndangeredAnimal = new EndangeredAnimal("elephant", "sick", "old");
        secondEndangeredAnimal.save();
        assertEquals(true, EndangeredAnimal.allEndangered().get(0).equals(firstEndangeredAnimal));
        assertEquals(true, EndangeredAnimal.allEndangered().get(1).equals(secondEndangeredAnimal));
    }

    @Test
    public void findById_savedEndangeredCanBeFoundById() {
        EndangeredAnimal  firstEndangeredAnimal = setNewEndangeredAnimal();
        firstEndangeredAnimal.save();
        EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.findById(firstEndangeredAnimal.getId());
        assertEquals(true, firstEndangeredAnimal.equals(savedEndangeredAnimal));
    }

    @Test
    public void clearAll_nothingReturnsFromClearedTable() {
        EndangeredAnimal newEndangeredAnimal = setNewEndangeredAnimal();
        newEndangeredAnimal.save();
        EndangeredAnimal.clearAll();
        assertEquals(0, EndangeredAnimal.allEndangered().size());
    }

    @Test
    public void deleteById_animalInstanceDeletedCorrectlyById() {
        EndangeredAnimal firstEndangeredAnimal = setNewEndangeredAnimal();
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("leopard", "okay", "young");
        secondEndangeredAnimal.save();
        EndangeredAnimal.deleteById(firstEndangeredAnimal.getId());
        assertEquals(1, EndangeredAnimal.allEndangered().size());
        assertTrue(EndangeredAnimal.allEndangered().contains(secondEndangeredAnimal));
    }

    private EndangeredAnimal setNewEndangeredAnimal() {
        return new EndangeredAnimal("Rhino", "okay", "old");
    }
}