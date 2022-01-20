package rabbits;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cage {

    private String name;
    private int capacity;
    private List<Rabbit> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(Rabbit rabbit) {

        if (data.size() < capacity) {
            data.add(rabbit);
        }
    }

    public boolean removeRabbit(String name) {

        return data.removeIf(r -> name.equals(r.getName()));
    }

    public void removeSpecies(String species) {

        data.removeIf(r -> species.equals(r.getSpecies()));

    }

    public Rabbit sellRabbit(String name) {
        Rabbit rabbitReturned = data.stream().filter(r -> r.isAvailable() && r.getName().equals(name))
                .findFirst().orElse(null);
        if (rabbitReturned != null) {
            rabbitReturned.setAvailable();
        }
        return rabbitReturned;
    }

    public List<Rabbit> sellRabbitBySpecies(String species) {

        List<Rabbit> resultList = data.stream().
                filter(r -> r.isAvailable() && r.getSpecies().equals(species))
                .collect(Collectors.toList());
        resultList.stream().forEach(r -> r.setAvailable());


        return resultList;
    }

    public int count() {
        return data.size();
    }

    public String report() {
        StringBuilder output = new StringBuilder();
                output.append(String.format("Rabbits available at %s:%n",name));
        output.append(data.stream().
                filter(r -> r.isAvailable()).
                map(r -> String.format("%s%n",r)).reduce("",String::concat));
        // o	"Rabbits available at {cageName}:
        //{Rabbit 1}
        //{Rabbit 2}
        //(…)"
        // only not sold rabbits

        return output.toString();
    }

}
