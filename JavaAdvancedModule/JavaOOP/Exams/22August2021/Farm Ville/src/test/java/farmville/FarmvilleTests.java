package farmville;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FarmvilleTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Farm

    private Farm farm;
    private List<Animal> animals;

    @Before
    public void init() {
        farm = new Farm("Jersey",5);
        animals = getAnimalsStub();
    }

    private List<Animal> getAnimalsStub() {

        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Cat",10));
        animals.add(new Animal("Tiger",20));
        animals.add(new Animal("Lion",30));

        return animals;
    }

    @Test
    public void testGetCount() {

        assertEquals(0, farm.getCount());
        farm.add(animals.get(0));
        assertEquals(1, farm.getCount());
    }

    @Test
    public void testSetName() {

        Farm newFarm = new Farm("Jersey2",2);

        assertEquals("Jersey2", newFarm.getName());
    }

    @Test
    public void testGetName() {
        assertEquals("Jersey",farm.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidNameNull() {

        new Farm(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidNameEmpty() {

        new Farm("", 3);
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidNameSpaces() {

        new Farm("   ", 3);
    }

    @Test
    public void testGetCapacity() {

        assertEquals(5, farm.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCapacity() {

        new Farm("Jersey", -2);
    }

    @Test
    public void testAddAnimal() {

        Animal animal = new Animal("Fox",15);
        farm.add(animal);

        assertEquals(1, farm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalFarmFull() {

        Animal animal = new Animal("Fox",15);
        Animal animal2 = new Animal("Fox2",15);
        Farm newFarm = new Farm("Jersey2", 1);
        newFarm.add(animal);
        newFarm.add(animal2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalExists() {

        Animal animal = new Animal("Fox",15);
        Animal animal2 = new Animal("Fox",15);
        Farm newFarm = new Farm("Jersey2", 2);
        newFarm.add(animal);
        newFarm.add(animal2);
    }

    @Test
    public void testRemoveAnimal() {

        farm.add(animals.get(0));

        assertTrue(farm.remove("Cat"));
    }

    @Test
    public void testRemoveAnimalFalse() {

        farm.add(animals.get(0));

        assertFalse(farm.remove("Cat2"));
    }

    @Test
    public void testGetAnimalEnergy() {

        assertEquals(10, animals.get(0).getEnergy(), 0.01);
    }
}
