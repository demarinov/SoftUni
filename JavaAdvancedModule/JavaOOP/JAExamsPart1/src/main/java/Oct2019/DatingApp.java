package Oct2019;

import java.util.*;

public class DatingApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer[] male = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s))
                .toArray(Integer[]::new);
        Integer[] female = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s))
                .toArray(Integer[]::new);

        Deque<Integer> maleCollection = new ArrayDeque<>();
        Deque<Integer> femaleCollection = new ArrayDeque<>();

        maleCollection.addAll(Arrays.asList(male));
        femaleCollection.addAll(Arrays.asList(female));

        int countMatches = 0;

        while (!maleCollection.isEmpty() && !femaleCollection.isEmpty()) {

            Integer maleValue = maleCollection.getLast();
            Integer femaleValue = femaleCollection.getFirst();

            if (maleValue.intValue() > 0 && femaleValue.intValue() > 0) {
                if (maleValue.intValue() % 25 == 0) {

                    maleCollection.removeLast();
                    if (!maleCollection.isEmpty()) {
                        maleCollection.removeLast();
                    }
                    continue;
                }

                if (femaleValue.intValue() % 25 == 0) {

                    femaleCollection.removeFirst();
                    if (!femaleCollection.isEmpty()) {
                        femaleCollection.removeFirst();
                    }
                    continue;

                }
            }

            if (maleValue.intValue() <= 0) {
                maleCollection.removeLast();
                continue;
            }

            if (femaleValue.intValue() <= 0) {
                femaleCollection.removeFirst();
                continue;
            }
//

            // match
            if (maleValue.equals(femaleValue)) {
                countMatches++;
                maleCollection.removeLast();

                femaleCollection.removeFirst();

            } else {

                femaleCollection.removeFirst();

                maleCollection.removeLast();
                maleValue -= 2;
                maleCollection.addLast(maleValue);

            }


        }

        System.out.printf("Matches: %d%n", countMatches);

        if (maleCollection.isEmpty()) {
            System.out.println("Males left: none");
        } else {
            StringBuilder outputMales = new StringBuilder();
            for (int i = 0; i < maleCollection.size(); i++) {

                if (i == maleCollection.size() - 1) {
                    outputMales.append(maleCollection.removeLast());
                } else {

                    outputMales.append(maleCollection.removeLast());
                    outputMales.append(", ");
                    i--;
                }
            }
            System.out.printf("Males left: %s%n", outputMales);

        }

        if (femaleCollection.isEmpty()) {
            System.out.println("Females left: none");
        } else {
            String outputFemales = femaleCollection.stream().map(d -> String.format("%d, ", d)).
                    reduce("", String::concat);
            System.out.printf("Females left: %s%n", outputFemales.
                    substring(0, outputFemales.length() - 2));

        }

    }
}
