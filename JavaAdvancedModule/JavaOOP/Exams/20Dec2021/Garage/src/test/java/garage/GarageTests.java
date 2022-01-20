package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GarageTests {
    //TODO: Test Garage class
    private List<Car> cars;
    private Garage garage;

    @Before
    public void init() {
        cars = getCarsStub();
        garage = new Garage();
    }

    private List<Car> getCarsStub() {

        List<Car> cars = new ArrayList<>();

        cars.add(new Car("Porsh1", 300, 205.00));
        cars.add(new Car("Porsh2", 310, 210.00));
        cars.add(new Car("Porsh2", 310, 210.00));
        cars.add(new Car("Porsh3", 320, 220.00));

        return cars;
    }

    @Test
    public void testGetCars() {
        assertEquals(0, garage.getCars().size());
        garage.addCar(cars.get(0));
        assertEquals(1, garage.getCars().size());
    }

    @Test
    public void testGetCount() {
        assertEquals(0, garage.getCount());
        garage.addCar(cars.get(0));
        assertEquals(1, garage.getCount());
    }

    @Test
    public void testFindCarsWithMaxSpeedAbove() {
        garage.addCar(cars.get(0));
        garage.addCar(cars.get(1));
        garage.addCar(cars.get(2));
        assertEquals(2, garage.findAllCarsWithMaxSpeedAbove(300).size());
    }

    @Test
    public void testAddCar() {
        garage.addCar(cars.get(0));
        assertEquals(1, garage.getCount());
        assertEquals("Porsh1", garage.getCars().get(0).getBrand());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCarFails() {
        garage.addCar(null);
    }

    @Test
    public void testGetTheMostExpensiveCar() {
        garage.addCar(cars.get(0));
        garage.addCar(cars.get(1));
        garage.addCar(cars.get(2));
        garage.addCar(cars.get(3));

        assertEquals(220.00, garage.getTheMostExpensiveCar().getPrice(),2);
    }

    @Test
    public void testGetTheMostExpensiveCarNull() {

        assertEquals(null, garage.getTheMostExpensiveCar());
    }
    
    @Test
    public void testFindAllCarsByBrand() {
        garage.addCar(cars.get(0));
        garage.addCar(cars.get(1));
        garage.addCar(cars.get(2));
        garage.addCar(cars.get(3));
        assertEquals(2, garage.findAllCarsByBrand("Porsh2").size());
    }


}