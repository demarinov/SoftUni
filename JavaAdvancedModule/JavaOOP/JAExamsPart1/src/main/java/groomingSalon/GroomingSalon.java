package groomingSalon;

import java.util.ArrayList;
import java.util.List;

public class GroomingSalon {

    private int capacity;
    private List<Pet> data;

    public GroomingSalon(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public int getCount() {
        return this.data.size();
    }

    public void add(Pet pet) {

        if (this.data.size() < capacity) {
            this.data.add(pet);
        }
    }

    public boolean remove(String name) {

//        Pet petToRemove = this.data.stream().filter(p -> p.getName().equals(name))
//                .findFirst().orElse(null);
//
//        if (petToRemove != null) {
//
//            this.data.remove(petToRemove);
//            return true;
//        }

        return this.data.removeIf(p -> p.getName().equals(name));
    }

    public Pet getPet(String name, String owner) {

        Pet petToReturn = this.data.stream().
                filter(p -> p.getName().equals(name) && p.getOwner().equals(owner))
                .findFirst().orElse(null);

        return petToReturn;
    }

    public String getStatistics() {

        String output = data.stream().
                map(c -> String.format("%n%s %s",c.getName(), c.getOwner())).
                reduce("",String::concat);
        return String.format(" The grooming salon has the following clients:%s",output);

    }
}
