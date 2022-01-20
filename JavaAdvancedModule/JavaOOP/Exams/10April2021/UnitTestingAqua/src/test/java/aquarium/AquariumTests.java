package aquarium;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class AquariumTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Aquarium

    private Aquarium aquarium;
    private List<Fish> fishList;
    private static final String DEFAULT_NAME = "Bulb";
    private static final int DEFAULT_CAPACITY = 5;

    @Before
    public void init() {
        aquarium = new Aquarium(DEFAULT_NAME, DEFAULT_CAPACITY);
        fishList = stubFish();
    }

    private List<Fish> stubFish() {

        List<Fish> result = new ArrayList<>();

        result.add(new Fish("Barny"));
        result.add(new Fish("Fred"));
        result.add(new Fish("Rado"));
        result.add(new Fish("Stella"));

        return result;
    }

    @Test
    public void testSetName() {

        assertEquals(DEFAULT_NAME, aquarium.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldFailIfNameIsEmpty() {

        Aquarium aqua = new Aquarium(null,2);
    }

    @Test
    public void testGetCountOfFish() {

        assertEquals(0, aquarium.getCount());

        aquarium.add(fishList.get(0));

        assertEquals(1, aquarium.getCount());
    }

    @Test
    public void testAddFish() {

        assertEquals(0, aquarium.getCount());

        aquarium.add(fishList.get(0));

        String expectedName = fishList.get(0).getName();
        assertEquals(expectedName, aquarium.sellFish(expectedName).getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFishShouldFailIfAquariumOverEqualsCapacity() {

        aquarium.add(fishList.get(0));
        aquarium.add(fishList.get(0));
        aquarium.add(fishList.get(0));
        aquarium.add(fishList.get(0));
        aquarium.add(fishList.get(0));
        aquarium.add(new Fish("Rocko"));
    }

    @Test
    public void testSellFishShouldReturnSoldFish() {

        stubAquarium();
        aquarium.sellFish(fishList.get(0).getName());

        assertFalse(fishList.get(0).isAvailable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellFishShouldFailIfFishIsAbsent() {

        aquarium.sellFish("Rondo");

        assertFalse(fishList.get(0).isAvailable());
    }

    @Test
    public void testRemoveFishShouldReturnRemovedFish() throws NoSuchFieldException, IllegalAccessException {
        stubAquarium();
        Field field = Aquarium.class.getDeclaredField("fish");
        field.setAccessible(true);
        Collection<Fish> fish = (Collection<Fish>) field.get(aquarium);
        aquarium.remove(fishList.get(0).getName());

        assertFalse(fish.contains(fishList.get(0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFishShouldFailIfFishIsAbsent() {

        aquarium.remove("Rondo");

        assertFalse(fishList.get(0).isAvailable());
    }

    @Test
    public void testSetCapacity() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Aquarium.class.getDeclaredMethod("setCapacity", int.class);
        method.setAccessible(true);
        method.invoke(aquarium, 10);

        assertEquals(10, aquarium.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityShouldFailIfCapacityNegative() {
        Aquarium aqua = new Aquarium("Bud",-2);
    }

    @Test
    public void testReport() {

        stubAquarium();
        String expected = String.format("Fish available at %s: %s, %s, %s, %s",
                aquarium.getName(), fishList.get(0).getName(),
                fishList.get(1).getName(), fishList.get(2).getName()
        ,fishList.get(3).getName());

        assertEquals(expected, aquarium.report());
    }

    private void stubAquarium() {
        aquarium.add(fishList.get(0));
        aquarium.add(fishList.get(1));
        aquarium.add(fishList.get(2));
        aquarium.add(fishList.get(3));
    }
}

