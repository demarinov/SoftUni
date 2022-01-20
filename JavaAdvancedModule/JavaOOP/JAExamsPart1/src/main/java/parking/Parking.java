package parking;

import java.util.ArrayList;
import java.util.List;

public class Parking {

    private String type;
    private int capacity;
    private List<Car> data;

    public Parking(String type, int capacity) {
        setType(type);
        setCapacity(capacity);
        data = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public void add(Car car) {
        if (data.size() < capacity) {
            data.add(car);
        }
    }

    public boolean remove(String manufacturer, String model) {

        boolean exists = data.stream()
                .anyMatch(c -> manufacturer.equals(c.getManufacturer()) && model.equals(c.getModel()));
        try {
            data.removeIf(c -> manufacturer.equals(c.getManufacturer()) && model.equals(c.getModel()));
        } catch(NullPointerException | UnsupportedOperationException e) {

        }

        return exists;
    }

    public Car getLatestCar() {

        return data.stream().sorted((c1, c2) -> Integer.valueOf(c2.getYear()).
                compareTo(Integer.valueOf(c1.getYear()))).findFirst().orElse(null);

    }

    public Car getCar(String manufacturer, String model) {

        return data.stream().
                filter(c -> manufacturer.equals(c.getManufacturer()) && model.equals(c.getModel()))
                .findFirst().orElse(null);
    }

    public int getCount() {
        return data.size();
    }

    public String getStatistics() {
        // o	"The cars are parked in {parking type}:
        //{Car1}
        //{Car2}
        String outputCar = data.stream().
                map(c -> String.format("%n%s",c.toString())).
                reduce("",String::concat);
        return String.format("The cars are parked in %s:%s",type,outputCar);
    }

}
