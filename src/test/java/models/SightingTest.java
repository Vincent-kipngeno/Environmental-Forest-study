package models;

import dbRule.DatabaseRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

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
        assertEquals(1, newSighting.getAnimalId());
    }

    @Test
    public void equal_sightingsWithSameLocationRangerNameAndAnimalIdAreEqual() {
        Sighting  firstSighting = setNewSighting();
        Sighting  secondSighting = setNewSighting();
        assertTrue( firstSighting.equals(secondSighting));
    }

    @Test
    public void save_sightingIsSavedCorrectlyOnEntry() {
        Sighting  firstSighting = setNewSighting();
        firstSighting.save();
        assertTrue(Sighting.all().get(0).equals(firstSighting));
    }

    @Test
    public void save_assignsIdToSighting() {
        Sighting  firstSighting = setNewSighting();
        firstSighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(savedSighting.getId(), firstSighting.getId());
    }

    @Test
    public void all_allSavedSightingsReturnedCorrectly() {
        Sighting  firstSighting = setNewSighting();
        firstSighting.save();
        Sighting  secondSighting = new Sighting("Zone B", "Dan", 1);
        secondSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(firstSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
    }

    @Test
    public void update_sightingInstanceIsUpdatedCorrectly() {
        Sighting newSighting = setNewSighting();
        newSighting.save();
        newSighting.update( newSighting.getId(), "Zone B", "Dan", 1);
        Sighting updatedSighting = Sighting.findById(newSighting.getId());
        assertEquals(false, newSighting.equals(updatedSighting));
    }

    @Test
    public void findById_savedSightingCanBeFoundById() {
        Sighting firstSighting = setNewSighting();
        firstSighting.save();
        Sighting savedSighting = Sighting.findById(firstSighting.getId());
        assertEquals(true, firstSighting.equals(savedSighting));
    }

    private Sighting setNewSighting(){
        return new Sighting("Zone A", "Kevin", 1);
    }
}