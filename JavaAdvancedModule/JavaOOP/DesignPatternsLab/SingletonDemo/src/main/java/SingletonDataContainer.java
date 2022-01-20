import java.util.HashMap;
import java.util.Map;

public class SingletonDataContainer implements SingletonContainer {
    private static SingletonDataContainer instance;
    private Map<String,Integer> capitals;


    private SingletonDataContainer() {
        this.capitals = new HashMap<>();
        System.out.println("Initializing singleton object");
    }


    public int getPopulation(String name) {
        return capitals.get(name);
    }

    @Override
    public void addPopulation(String capital, Integer population) {

        capitals.putIfAbsent(capital, 0);
        capitals.put(capital,capitals.get(capital)+population);
    }

    @Override
    public void setPopulation(String capital, Integer population) {

    }

    public static SingletonDataContainer getInstance() {
        if (instance != null){
            return instance;
        }
        instance = new SingletonDataContainer();
        return instance;
    }
}

