import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        SingletonDataContainer instance = SingletonDataContainer.getInstance();
        instance.addPopulation("Sofia", 120000);
        System.out.println(instance.getPopulation("Sofia"));

        SingletonDataContainer instance1 = SingletonDataContainer.getInstance();
        instance1.addPopulation("Varna",90000);
        System.out.println(instance1.getPopulation("Varna"));
    }
}


