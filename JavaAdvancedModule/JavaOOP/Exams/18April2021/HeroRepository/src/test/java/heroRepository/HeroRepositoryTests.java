package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HeroRepositoryTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS HeroRepository

    private HeroRepository heroRepository;
    private List<Hero> heroes;

    @Before
    public void init() {
        heroRepository = new HeroRepository();
        heroes = stubHeroes();
    }

    private List<Hero> stubHeroes() {
        List<Hero> heroesList = new ArrayList<>();

        heroesList.add(new Hero("Barny",1));
        heroesList.add(new Hero("Fred",1));
        heroesList.add(new Hero("Wilma",2));

        return heroesList;
    }

    @Test
    public void testGetCount() {
        assertEquals(0, heroRepository.getCount());
        heroRepository.create(heroes.get(0));
        assertEquals(1, heroRepository.getCount());
    }

    @Test
    public void testCreateHeroShouldReturnTheHero() {

        assertEquals("Successfully added hero Barny with level 1", heroRepository.create(heroes.get(0)));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHeroShouldFailWithHeroIsNull() {
        heroRepository.create(null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHeroShouldFailSinceDuplicateName() {
        heroRepository.create(heroes.get(0));
        heroRepository.create(heroes.get(0));

    }

    @Test
    public void testRemoveHero() {
        heroRepository.create(heroes.get(0));
        assertTrue(heroRepository.remove(heroes.get(0).getName()));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveHeroShouldFailSinceNameCannotBeNull() {
        assertTrue(heroRepository.remove(null));
    }

    @Test
    public void testGetHeroWithHighestLevel() {
        heroRepository.create(heroes.get(0));
        heroRepository.create(heroes.get(2));

        assertEquals(2, heroRepository.getHeroWithHighestLevel().getLevel());

    }

    @Test
    public void testGetHeroWithHighestLevelShouldReturnNull() {

        assertEquals(null, heroRepository.getHeroWithHighestLevel());

    }

    @Test
    public void testGetHeroByName() {
        heroRepository.create(heroes.get(0));

        assertEquals(heroes.get(0), heroRepository.getHero(heroes.get(0).getName()));

    }

    @Test
    public void testGetHeroByNameShouldReturnNull() {

        assertEquals(null, heroRepository.getHero(heroes.get(0).getName()));
    }

    @Test
    public void testGetHeroes() {
        heroRepository.create(heroes.get(0));
        heroRepository.create(heroes.get(1));

        assertEquals(2, heroRepository.getHeroes().size());
    }
}
