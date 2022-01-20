package restaurant.entities.tables;

import restaurant.common.ExceptionMessages;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseTable implements Table {

    // •	healthyFood - Collection<HealthyFood> accessible only by the base class
    private Collection<HealthyFood> healthyFood;
    //•	beverages – Collection<Beverages> accessible only by the base class
    private Collection<Beverages> beverages;
    //•	number – int the table number
    private int number;
    //•	size - int the table size
    private int size;
    //o	It can’t be less than zero. In these cases, throw an IllegalArgumentException with message "Size has to be greater than 0!".
    //•	numberOfPeople - int the counter of people who want a table
    private int numberOfPeople;
    //o	It can’t be less than or equal to 0. In these cases, throw an IllegalArgumentException with message "Cannot place zero or less people!".
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

    protected void setHealthyFood(Collection<HealthyFood> healthyFood) {
        this.healthyFood = healthyFood;
    }

    protected void setBeverages(Collection<Beverages> beverages) {
        this.beverages = beverages;
    }

    protected void setNumber(int number) {
        this.number = number;
    }

    protected void setSize(int size) {

        if (size < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_SIZE);
        }

        this.size = size;
    }

    protected void setNumberOfPeople(int numberOfPeople) {

        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }

        this.numberOfPeople = numberOfPeople;
    }

    protected void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    protected void setReservedTable(boolean reservedTable) {
        isReservedTable = reservedTable;
    }

    protected void setAllPeople(double allPeople) {
        this.allPeople = allPeople;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return this.isReservedTable;
    }

    @Override
    public double allPeople() {
        return this.pricePerPerson * this.numberOfPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.isReservedTable = true;
        setNumberOfPeople(numberOfPeople);
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {

        double totalFoodBill = this.healthyFood.stream().mapToDouble(f -> f.getPrice() * f.getPortion())
                .sum();
        double totalBeverageBill = this.beverages.stream().mapToDouble(b -> b.getPrice() * b.getCounter())
                .sum();


        return totalBeverageBill + totalFoodBill + allPeople();
    }

    @Override
    public void clear() {
        this.beverages.clear();
        this.healthyFood.clear();
        this.isReservedTable = false;
//        this.pricePerPerson = 0;
        this.numberOfPeople = 0;
    }

    @Override
    public String tableInformation() {
        //"Table - {table number}"
        //"Size - {table size}"
        //"Type - {table type}"
        //"All price - {price per person for the current table}"

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Table - %d",getTableNumber())).append(System.lineSeparator());
        sb.append(String.format("Size - %d", this.size)).append(System.lineSeparator());
        sb.append(String.format("Type - %s",
                this.getClass().getSimpleName())).append(System.lineSeparator());

        sb.append(String.format("All price - %f", this.pricePerPerson));

        return sb.toString();
    }
}
