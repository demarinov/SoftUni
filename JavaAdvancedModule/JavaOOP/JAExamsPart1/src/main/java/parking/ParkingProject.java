package parking;

public class ParkingProject {

    public static void main(String[] args) {
        // Initialize the repository
        Parking parking = new Parking("Underground parking garage", 5);

// Initialize entity
        Car volvo = new Car("Volvo", "XC70", 2010);

// Print Car
        System.out.println(volvo); // Volvo XC70 (2010)

// Add Car
        parking.add(volvo);

// Remove Car
        System.out.println(parking.remove("Volvo", "XC90")); // false
        System.out.println(parking.remove("Volvo", "XC70")); // true

        Car peugeot = new Car("Peugeot", "307", 2011);
        Car audi = new Car("Audi", "S4", 2005);
        Car audi2 = new Car("Audi", "S5", 2006);
        Car audi3 = new Car("Audi", "S6", 2007);
        Car audi4 = new Car("Audi", "S7", 2008);
        Car audi5 = new Car("Audi", "S8", 2009);

        parking.add(peugeot);
        parking.add(audi);
        parking.add(audi2);
        parking.add(audi3);
        parking.add(audi4);
        parking.add(audi5);

// Get Latest Car
        Car latestCar = parking.getLatestCar();
        System.out.println(latestCar); // Peugeot 307 (2011)

// Get Car
        Car audiS4 = parking.getCar("Audi", "S4");
        Car audiS5 = parking.getCar("Audi", "S5");
        Car audiS6 = parking.getCar("Audi", "S6");
        System.out.println(audiS4); // Audi S4 (2005)

// Count
        System.out.println(parking.getCount()); // 2

// Get Statistics
        System.out.println(parking.getStatistics());
// The cars are parked in Underground parking garage:
// Peugeot 307 (2011)
// Audi S4 (2005)

    }
}
