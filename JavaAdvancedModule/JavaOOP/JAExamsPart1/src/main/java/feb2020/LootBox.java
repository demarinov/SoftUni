package feb2020;

import java.util.*;

public class LootBox {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer[] boxOne = Arrays.stream(sc.nextLine().split(" "))
                .map(s -> Integer.parseInt(s))
                .toArray(Integer[]::new);
        Integer[] boxTwo = Arrays.stream(sc.nextLine().split(" "))
                .map(s -> Integer.parseInt(s))
                .toArray(Integer[]::new);

        Deque<Integer> boxOneQueue = new ArrayDeque<>();
        boxOneQueue.addAll(Arrays.asList(boxOne));

        Deque<Integer> boxTwoQueue = new ArrayDeque<>();

        Collections.addAll(boxTwoQueue, boxTwo);

        List<Integer> claimedItems = new ArrayList<>();
        while(!boxOneQueue.isEmpty() && !boxTwoQueue.isEmpty()) {

            Integer itemOne = boxOneQueue.getFirst();
            Integer itemTwo = boxTwoQueue.getLast();

            int sum = itemOne + itemTwo;

            if (sum % 2 == 0) {
                claimedItems.add(sum);
                boxOneQueue.removeFirst();
                boxTwoQueue.removeLast();
            } else {
                boxTwoQueue.removeLast();
                boxOneQueue.addLast(itemTwo);
            }

        }

        if (boxOneQueue.isEmpty()) {
            System.out.println("First lootbox is empty");
        } else if (boxTwoQueue.isEmpty()) {
            System.out.println("Second lootbox is empty");
        }

        if (!claimedItems.isEmpty()) {
            int sumOfItems = claimedItems.stream().mapToInt(s -> s).sum();

            if (sumOfItems >= 100) {
                System.out.printf("Your loot was epic! Value: %d%n", sumOfItems);
            } else {
                System.out.printf("Your loot was poor... Value: %d%n", sumOfItems);
            }
        }
    }
}
