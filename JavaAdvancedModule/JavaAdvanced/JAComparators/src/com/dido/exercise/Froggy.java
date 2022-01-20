package com.dido.exercise;

import java.util.*;
import java.util.stream.Collectors;

public class Froggy {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        String input = sc.nextLine();

        List<Integer> frogsAll = new ArrayList<>();
        while (!"END".equals(input)) {
            List<Integer> frogs = Arrays.stream(input.split(",\\s+"))
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());

            frogsAll.addAll(frogs);
            input = sc.nextLine();
        }

        Lake lake = new Lake(frogsAll.toArray(new Integer[0]));

        StringBuilder result = new StringBuilder();
        for (Integer froggy : lake) {
            result.append(froggy);
            result.append(", ");
        }

        System.out.println(result.substring(0,result.length()-2));
    }
}

class Lake implements Iterable<Integer> {

    private Integer[] dataArray;

    public Lake(Integer... data) {
        if (data.length == 0) {
            this.dataArray = new Integer[4];
        } else {
            this.dataArray = data;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Frog();
    }

    private final class Frog implements Iterator<Integer> {

        private Integer indexEven = 0;
        private Integer indexOdd = 1;
        private Boolean evenIterated = false;
        private Integer index = 0;

        @Override
        public boolean hasNext() {

            if (indexEven.intValue() <= dataArray.length - 1) {
                return true;
            } else if (indexOdd.intValue() <= dataArray.length - 1) {
                return true;
            }
            return false;
        }

        @Override
        public Integer next() {

            Integer element = null;


            if (!evenIterated && indexEven.intValue() <= dataArray.length-1) {
                element = dataArray[indexEven];
                index++;
                indexEven = 2 * index;
                return element;
            } else {
                evenIterated = true;
            }

            if (indexOdd == 1) {
                index = 0;
            }


            if (indexOdd.intValue() <= dataArray.length-1){
                element = dataArray[indexOdd];
                index++;
                indexOdd = 2 * index + 1;
                return element;
            }

            return element;
        }
    }
}