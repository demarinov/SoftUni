package restaurant.core;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

public class ControllerImpl implements Controller {

    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private TableRepository<Table> tableRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private double totalMoney = 0;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.beverageRepository = beverageRepository;
        this.healthFoodRepository = healthFoodRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood foodToAdd = createHealthyFood(type, price, name);
        HealthyFood food = healthFoodRepository.foodByName(name);

        if (food != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST, name));
        }


        healthFoodRepository.add(foodToAdd);
        return String.format(OutputMessages.FOOD_ADDED, name);
    }

    private HealthyFood createHealthyFood(String type, double price, String name) {

        switch(type) {
            case "Salad":
                return new Salad(name, price);
            case "VeganBiscuits":
                return new VeganBiscuits(name, price);
            default:
                return null;
        }
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name){

        Beverages beveragesToAdd = createBeverage(type, counter, brand, name);
        Beverages beverages = beverageRepository.beverageByName(name, brand);

        if (beverages != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST, name));
        }

        beverageRepository.add(beveragesToAdd);
        return String.format(OutputMessages.BEVERAGE_ADDED, type, brand);
    }

    private Beverages createBeverage(String type, int counter , String brand, String name) {

        switch(type) {
            case "Fresh":
                return new Fresh(name, counter, brand);
            case "Smoothie":
                return new Smoothie(name, counter, brand);
            default:
                return null;
        }
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {

        Table tableToAdd = createTable(type, tableNumber, capacity);
        Table table = tableRepository.byNumber(tableNumber);

        if (table != null) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED, tableNumber));
        }

        tableRepository.add(tableToAdd);
        return String.format(OutputMessages.TABLE_ADDED, tableNumber);
    }

    private Table createTable(String type, int tableNumber, int capacity) {

        switch(type) {
            case "Indoors":
                return new Indoors(tableNumber, capacity);
            case "InGarden":
                return new InGarden(tableNumber, capacity);
            default:
                return null;
        }
    }

    @Override
    public String reserve(int numberOfPeople) {

        Table freeTable = tableRepository.getAllEntities().
                stream().filter(t -> !t.isReservedTable() && t.getSize() >= numberOfPeople)
                .findFirst().orElse(null);

        if (freeTable == null) {
            return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        freeTable.reserve(numberOfPeople);
        return String.format(OutputMessages.TABLE_RESERVED, freeTable.getTableNumber(),
                numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = tableRepository.byNumber(tableNumber);

        if (table == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }

        HealthyFood healthyFood = healthFoodRepository.foodByName(healthyFoodName);

        if (healthyFood == null) {
            return String.format(OutputMessages.NONE_EXISTENT_FOOD, healthyFoodName);
        }

        table.orderHealthy(healthyFood);
        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {

        Table table = tableRepository.byNumber(tableNumber);

        if (table == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }

        Beverages beverages = beverageRepository.beverageByName(name, brand);

        if (beverages == null) {
            return String.format(OutputMessages.NON_EXISTENT_DRINK,name, brand);
        }

        table.orderBeverages(beverages);
        return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = tableRepository.byNumber(tableNumber);

        double bill = table.bill();
        table.clear();
        totalMoney += bill;
        return String.format(OutputMessages.BILL, tableNumber, bill);
    }


    @Override
    public String totalMoney() {
        return String.format(OutputMessages.TOTAL_MONEY,totalMoney);
    }
}
