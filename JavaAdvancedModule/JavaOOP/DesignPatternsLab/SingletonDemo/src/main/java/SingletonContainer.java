import java.io.Serializable;
import java.util.Map;

public interface SingletonContainer extends Serializable {

    int getPopulation( String name);

    void addPopulation(String capital, Integer population);
    void setPopulation(String capital, Integer population);
}


