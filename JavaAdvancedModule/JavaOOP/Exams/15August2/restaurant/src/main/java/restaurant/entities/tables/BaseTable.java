package restaurant.entities.tables;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseTable implements Table {

    //• healthyFood - Collection<HealthyFood> accessible only by the base class
    private Collection<HealthyFood> healthyFood;
    //•	beverages – Collection<Beverages> accessible only by the base class
    private Collection<Beverages> beverages;
    //•	number – int the table number
    private int number;
    //•	size - int the table size
    private int size;

    //•	numberOfPeople - int the counter of people who want a table
    private int numberOfPeople;

    //•	pricePerPerson - double the price per person for the table
    private double pricePerPerson;
    //•	isReservedTable - boolean returns true if the table is reserved, otherwise false
    private boolean isReservedTable;
    //•	allPeople - double calculates the price for all people
    private double allPeople;

    protected BaseTable(int number, int size, double pricePerPerson) {
        setNumber(number);
        setSize(size);
        setPricePerPerson(pricePerPerson);
        setHealthyFood(new ArrayList<>());
        setBeverages(new ArrayList<>());

    }

    private void setHealthyFood(Collection<HealthyFood> healthyFood) {
        this.healthyFood = healthyFood;
    }

    private void setBeverages(Collection<Beverages> beverages) {
        this.beverages = beverages;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    protected void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    protected void setSize(int size) {
        //o	It can’t be less than zero. In these cases,
        // throw an IllegalArgumentException with message "Size has to be greater than 0!".
        if (size < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
    }

    protected void setNumberOfPeople(int numberOfPeople) {
        //o	It can’t be less than or equal to 0. In these cases,
        // throw an IllegalArgumentException with message "Cannot place zero or less people!".
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    protected void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return this.isReservedTable;
    }

    protected void setReservedTable(boolean reservedTable) {
        isReservedTable = reservedTable;
    }

    @Override
    public double allPeople() {
        return this.allPeople;
    }

    protected void setAllPeople(double allPeople) {
        this.allPeople = allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.setReservedTable(true);
        this.setNumberOfPeople(numberOfPeople);
        double reservationBill = this.numberOfPeople() * this.pricePerPerson();
        this.setAllPeople(reservationBill);
    }

    @Override
    public void orderHealthy(HealthyFood food) {

        if (food != null) {
            this.healthyFood.add(food);
        }
    }

    @Override
    public void orderBeverages(Beverages beverages) {

        if (beverages != null) {
            this.beverages.add(beverages);
        }
    }

    @Override
    public double bill() {
        double foodBill = healthyFood.stream().
                mapToDouble(f -> f.getPortion() * f.getPrice())
                .sum();
        double beverageBill = beverages.stream().mapToDouble(b -> b.getCounter() * b.getPrice())
                .sum();

        return foodBill + beverageBill + this.allPeople();
    }

    @Override
    public void clear() {
        healthyFood.clear();
        beverages.clear();
        this.setReservedTable(false);
        this.numberOfPeople = 0;
        this.setAllPeople(0);

    }

    @Override
    public String tableInformation() {
        // "Table - {table number}"
        //"Size - {table size}"
        //"Type - {table type}"
        //"All price - {price per person for the current table}"
        StringBuilder output = new StringBuilder();
        output.append(String.format("Table - %d",this.number));
        output.append(System.lineSeparator());
        output.append(String.format("Size - %d",this.getSize()));
        output.append(System.lineSeparator());
        output.append(String.format("Type - %s", this.getClass().getSimpleName()));
        output.append(System.lineSeparator());
        output.append(String.format("All price - %f",this.allPeople()));
        return output.toString();
    }
}
