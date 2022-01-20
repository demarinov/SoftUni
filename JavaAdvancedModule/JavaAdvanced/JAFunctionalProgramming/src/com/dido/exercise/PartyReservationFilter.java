package com.dido.exercise;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class PartyReservationFilter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] valueArray = sc.nextLine().split("\\s");

        BiPredicate<String,String> predicateStart = (s1, s2) -> s1.startsWith(s2);
        BiPredicate<String,String> predicateEnd = (s1,s2) -> s1.endsWith(s2);
        BiPredicate<String,String> predicateContains = (s1,s2) -> s1.contains(s2);
        BiPredicate<String,Integer> predicateLength = (s1,s2) -> s1.length() == s2;

        String input = sc.nextLine();

        PartyReservationFilter partyReservationFilter = new PartyReservationFilter();

        List<CustomFilter> filterList = new ArrayList<>();
        while(!"Print".equals(input)) {

            String[] filterData = input.split(";");

            String command = filterData[0];

            switch(command) {

                case "Add filter":
                    String type = filterData[1];
                    String value = filterData[2];
                    CustomFilter filter = partyReservationFilter.createFilter(type,value);
                    filterList.add(filter);
                    break;
                case "Remove filter":
                    type = filterData[1];
                    value = filterData[2];

                    // May need to remove just first equal object...
                    filterList.removeIf(s -> type.equals(s.getType()) && value.equals(s.getValue()));
                    break;
                default:
                    break;
            }

            input = sc.nextLine();
        }


        List<String> valueList = Arrays.asList(valueArray);
        for (CustomFilter filter : filterList) {

            String type = filter.getType();
            String value = filter.getValue();

            switch(type) {

                case "Starts with":
                    valueList = valueList.stream().filter(v -> !predicateStart.test(v,value))
                    .collect(Collectors.toList());
                    break;
                case "Ends with":
                    valueList = valueList.stream().filter(v -> !predicateEnd.test(v,value))
                            .collect(Collectors.toList());
                    break;
                case "Length":
                    Integer num = Integer.parseInt(value);
                    valueList = valueList.stream().filter(v -> !predicateLength.test(v,num))
                            .collect(Collectors.toList());
                    break;
                case "Contains":
                    valueList = valueList.stream().filter(v -> !predicateContains.test(v,value))
                            .collect(Collectors.toList());
                    break;

            }
        }

        valueList.forEach(s -> System.out.printf("%s ",s));
    }

    public CustomFilter createFilter(String type, String value) {

        return new CustomFilter(type, value);
    }

    private final class CustomFilter {

        private String type;
        private String value;

        public CustomFilter(String type, String value) {
            this.type = type;
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CustomFilter that = (CustomFilter) o;
            return Objects.equals(type, that.type) && Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, value) + 61;
        }
    }
}
