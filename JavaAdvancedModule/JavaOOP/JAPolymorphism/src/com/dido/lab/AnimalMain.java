package com.dido.lab;

public class AnimalMain {

    public static void main(String[] args) {

        Animal cat = new Cat("Oscar", "Whiskas");
        Animal dog = new Dog("Rocky", "Meat");
        System.out.println(cat.explainSelf());
        System.out.println(dog.explainSelf());


    }

    static class Cat extends Animal {

        protected Cat(String name, String favouriteFood) {
            super(name, favouriteFood);
        }

        @Override
        public String explainSelf() {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("I am %s and my favourite food is %s%n",
                    getName(), getFavouriteFood()));
            builder.append("MEEOW");
            return builder.toString();
        }
    }

    static class Dog extends Animal {

        protected Dog(String name, String favouriteFood) {
            super(name, favouriteFood);
        }

        @Override
        public String explainSelf() {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("I am %s and my favourite food is %s%n",
                    getName(), getFavouriteFood()));
            builder.append("DJAAF");
            return builder.toString();
        }
    }

    static abstract class Animal {

       private String name;
       private String favouriteFood;

       protected Animal(String name, String favouriteFood) {
           this.name = name;
           this.favouriteFood = favouriteFood;
       }

       public String getName() {
           return name;
       }

       private void setName(String name) {
           this.name = name;
       }

       public String getFavouriteFood() {
           return favouriteFood;
       }

       private void setFavouriteFood(String favouriteFood) {
           this.favouriteFood = favouriteFood;
       }

       public abstract String explainSelf();
   }
}
