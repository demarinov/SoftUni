package jun2020;

import java.util.*;

public class Bombs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<Integer, String> bombTypes = new LinkedHashMap<>();
        bombTypes.put(40,"Datura Bombs");
        bombTypes.put(60,"Cherry Bombs");
        bombTypes.put(120,"Smoke Decoy Bombs");

        Map<String, Integer> bombPouchCount = new HashMap<>();
        bombPouchCount.put("Datura Bombs",0);
        bombPouchCount.put("Cherry Bombs",0);
        bombPouchCount.put("Smoke Decoy Bombs",0);

        Integer[] bombEffect = Arrays.stream(sc.nextLine().split(",\\s"))
                .map(s -> Integer.parseInt(s))
                .toArray(Integer[]::new);

        Integer[] bombCasing = Arrays.stream(sc.nextLine().split(",\\s"))
                .map(s -> Integer.parseInt(s))
                .toArray(Integer[]::new);

        Deque<Integer> bombEffectQueue = new ArrayDeque<>();
        Collections.addAll(bombEffectQueue, bombEffect);

        Deque<Integer> bombCasingQueue = new ArrayDeque<>();
        Collections.addAll(bombCasingQueue, bombCasing);

        boolean pouchFilled = false;

        while(!bombEffectQueue.isEmpty() && !bombCasingQueue.isEmpty()) {

            if (filledPouch(bombPouchCount)) {
                pouchFilled = true;
                break;
            }

            int sum = 0;
            int bombNumberOne = bombEffectQueue.getFirst();
            int bombNumberTwo = bombCasingQueue.getLast();

            sum = bombNumberOne + bombNumberTwo;

            if (bombTypes.containsKey(sum)) {
                bombPouchCount.put(bombTypes.get(sum),bombPouchCount.get(bombTypes.get(sum))+1);
                bombEffectQueue.removeFirst();
                bombCasingQueue.removeLast();
            } else {
                bombNumberTwo -= 5;
                bombCasingQueue.removeLast();
                bombCasingQueue.addLast(bombNumberTwo);
            }

        }

        if (pouchFilled) {
            System.out.println("Bene! You have successfully filled the bomb pouch!");
        } else {
            System.out.println("You don't have enough materials to fill the bomb pouch.");
        }

        if (bombEffectQueue.isEmpty()) {
            System.out.println("Bomb Effects: empty");
        } else {
            System.out.print("Bomb Effects: ");
            String output = bombEffectQueue.stream().
                    map(s -> String.format("%s, ",s)).reduce("",String::concat);

            System.out.println(output.substring(0, output.length()-2));
        }

        if (bombCasingQueue.isEmpty()) {
            System.out.println("Bomb Casings: empty");
        } else {
            System.out.print("Bomb Casings: ");
            String output = bombCasingQueue.stream().
                    map(s -> String.format("%s, ",s)).reduce("",String::concat);
            System.out.println(output.substring(0, output.length()-2));
        }

        System.out.printf("Cherry Bombs: %d%n", bombPouchCount.get("Cherry Bombs"));
        System.out.printf("Datura Bombs: %d%n", bombPouchCount.get("Datura Bombs"));
        System.out.printf("Smoke Decoy Bombs: %d%n", bombPouchCount.get("Smoke Decoy Bombs"));

    }

    private static boolean filledPouch(Map<String, Integer> bombPouch) {

        if (bombPouch.get("Datura Bombs") >= 3 && bombPouch.get("Cherry Bombs") >= 3
            && bombPouch.get("Smoke Decoy Bombs") >= 3) {
            return true;
        }

        return false;
    }
}
