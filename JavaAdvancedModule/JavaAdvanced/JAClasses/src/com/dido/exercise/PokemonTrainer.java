package com.dido.exercise;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PokemonTrainer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // receive the command "Tournament",
        // each line will carry information about a pokemon
        // and the trainer who caught it in the format
        // "<TrainerName> <PokemonName> <PokemonElement> <PokemonHealth>"
        // where TrainerName is the name of the
        // Trainer who caught the pokemon, names are unique there cannot be
        // 2 trainers with the same name.

        String input = sc.nextLine();

        List<Trainer> trainerList = new LinkedList<>();
        while(!"Tournament".equals(input)) {

            String[] pokeData = input.split("\\s+");
            String trainerName = pokeData[0];
            String pokeName = pokeData[1];
            String pokeElement = pokeData[2];
            Integer pokeHealth = Integer.parseInt(pokeData[3]);

            Trainer trainer = findOrCreateTrainer(trainerList, trainerName);

            Pokemon poke = new Pokemon(pokeName, pokeElement, pokeHealth);

            trainer.addPokemon(poke);

            input = sc.nextLine();
        }

        // After receiving the command "Tournament" an unknown number of lines containing
        // one of three elements "Fire", "Water", "Electricity"
        // will follow until the command "End" is received

        input = sc.nextLine();
        while(!"End".equals(input)) {
            String element = input;

            // For every command you must check if a trainer has
            findTrainerWithElement(trainerList, element);

            input = sc.nextLine();
        }



//        After the command "End" is received you should print all trainers sorted
//        by the amount of badges they have in descending order
//        (if two trainers have the same amount of badges they should be sorted by order
//        of appearance in the input) in the format "<TrainerName> <Badges> <NumberOfPokemon".

        trainerList.stream()
                .sorted((t1,t2) -> t2.getBadges().compareTo(t1.getBadges()))
                .forEach(System.out::println);

    }

    public static void findTrainerWithElement(List<Trainer> trainerList, String element) {
        // at least 1 pokemon with the given element,
        // if he does he receives 1 badge, otherwise all his pokemon lose 10 health,
        // if a pokemon falls to 0 or less health he dies and must be deleted from
        // the trainer’ collection.

        trainerList.stream()
                .filter(t -> {

                    List<Pokemon> pokList = t.getPokList();

                    Pokemon pok = pokList.stream().filter(p -> element.equals(p.getElement()))
                            .findAny().orElse(null);

                    if (pok == null) {
                        return false;
                    } else {
                        return true;
                    }
                }).forEach(t -> t.incrementBadges());


        trainerList.stream()
                .filter(t -> {

                    List<Pokemon> pokList = t.getPokList();

                    Pokemon pok = pokList.stream().filter(p -> element.equals(p.getElement()))
                            .findAny().orElse(null);

                    if (pok == null) {
                        return true;
                    } else {
                        return false;
                    }
                }).forEach(t -> {
                  t.getPokList().stream().forEach(p -> p.decreaseHealth());
                  t.getPokList().removeIf(pok -> pok.getHealth() <= 0);
                });

        // check poks list for dead poks
        // remove from trainer
//        trainerList.stream().forEach(getPokList().removeIf(pok -> pok.getHealth() <= 0));
    }

    public static Trainer findOrCreateTrainer(List<Trainer> trainerList, String name) {

        Trainer trainer = trainerList.stream().filter(t -> name.equals(t.getName()))
                .findFirst().orElse(null);

        if (trainer == null) {
            trainer = new Trainer(name);
            trainerList.add(trainer);
        }

        return trainer;
    }
    // Trainer has a name, number of badges and a collection of pokemon.

    static class Trainer {

        private String name;
        private Long badges;
        private List<Pokemon> pokList;

        public Trainer(String name) {
            this.name = name;
            this.badges = 0l;
            this.pokList = new LinkedList<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void incrementBadges() {
            badges++;
        }

        public Long getBadges() {
            return badges;
        }

        public void setBadges(Long badges) {
            this.badges = badges;
        }

        public void addPokemon(Pokemon pok) {
            pokList.add(pok);
        }

        public List<Pokemon> getPokList() {
            return pokList;
        }

        public void setPokList(List<Pokemon> pokList) {
            this.pokList = pokList;
        }

        @Override
        public String toString() {
            return String.format("%s %d %d",getName(), getBadges(), getPokList().size());
        }
    }

    static class Pokemon{

        private String name;
        private String element;
        private Integer health;

        public Pokemon(String name, String element, Integer health) {
            this.name = name;
            this.element = element;
            this.health = health;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getElement() {
            return element;
        }

        public void setElement(String element) {
            this.element = element;
        }

        public void decreaseHealth() {
            this.health -= 10;
        }

        public Integer getHealth() {
            return health;
        }

        public void setHealth(Integer health) {
            this.health = health;
        }
    }
    // Pokemon has a name, an element and health,

    // all values are mandatory. Every Trainer starts with 0 badges.
}
