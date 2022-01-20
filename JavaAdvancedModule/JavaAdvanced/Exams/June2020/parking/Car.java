package parking;

public class Car {

    // •	manufacturer: String
    //•	model: String
    //•	year: int
    //The class constructor should receive manufacturer, model and year. You need to create the appropriate getters and setters. Override the toString() method in the following format:
    //"{manufacturer} {model} ({year})"

    private String manufacturer;
    private String model;
    private int year;

    public Car(String manufacturer, String model, int year) {
        setManufacturer(manufacturer);
        setModel(model);
        setYear(year);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    private void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    private void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        // {manufacturer} {model} ({year})"
        return String.format("%s %s (%s)",manufacturer, model, year);
    }
}
