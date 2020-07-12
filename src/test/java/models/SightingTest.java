package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {

    @Test
    public void newSighting_newSightingInstantiatesCorrectly() {
        Sighting newSighting = setNewSighting();
        assertEquals(true, newSighting instanceof Sighting);
    }

    @Test
    public void getLocation_newSightingInstantiatesCorrectlyWithLocation() {
        Sighting newSighting = setNewSighting();
        assertEquals("Zone A", newSighting.getLocation());
    }

    @Test
    public void getRangerName_newSightingInstantiatesCorrectlyWithRangerName() {
        Sighting newSighting = setNewSighting();
        assertEquals("Kevin", newSighting.getRangerName());
    }

    @Test
    public void getAnimalId_newSightingInstantiatesCorrectlyWithAnimalId() {
        Sighting newSighting = setNewSighting();
        assertEquals("1", newSighting.getAnimalId());
    }

    private Sighting setNewSighting(){
        return new Sighting("Zone A", "Kevin", 1);
    }
}