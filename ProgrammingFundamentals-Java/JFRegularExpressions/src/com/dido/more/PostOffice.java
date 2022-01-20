package com.dido.more;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostOffice {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split("\\|");

        String firstRegex = "([*#&$%])([A-Z]+)\\1";
        String secondRegex = "(\\d+):(\\d{2})";
        String thirdRegex = "(\\b[A-Z][^ ]*)";

        Pattern patternFirst = Pattern.compile(firstRegex);
        Pattern patternSecond = Pattern.compile(secondRegex);
        Pattern patternThird = Pattern.compile(thirdRegex);

        Matcher matcher = patternFirst.matcher(input[0]);

        Map<Character, List<Integer>> lettersLengthMap = new LinkedHashMap<>();
        String letters = "";
        while (matcher.find()) {

            letters = matcher.group(2);

            for (int i = 0; i < letters.length(); i++) {

                char c = letters.charAt(i);

                lettersLengthMap.putIfAbsent(c, new ArrayList<>());
            }
        }


        matcher = patternSecond.matcher(input[1]);


        while (matcher.find()) {

            char c = (char) Integer.parseInt(matcher.group(1));
            int len = Integer.parseInt(matcher.group(2))+1;

            if (lettersLengthMap.containsKey(c)) {

                List<Integer> lengths = lettersLengthMap.get(c);

                lengths.add(len);
            }

        }

        matcher = patternThird.matcher(input[2]);

        List<String> words = new LinkedList<>();
        while (matcher.find()) {

            String word = matcher.group(1);

            words.add(word);
        }

        for (Map.Entry<Character, List<Integer>> lettersEntry : lettersLengthMap.entrySet()) {

            Character letter = lettersEntry.getKey();
            List<Integer> lengths = lettersLengthMap.get(letter);
            boolean nextLetter = false;

            for (int i = 0; i < words.size(); i++) {

                String word = words.get(i);

                if (letter.equals(word.charAt(0))) {

                    for (int j = 0; j < lengths.size(); j++) {
                        int wordLen = lengths.get(j);

                        if (word.length() == wordLen) {

                            System.out.println(word);
                            words.remove(i);
                            nextLetter = true;
                            break;
                        }
                    }
                }

                if (nextLetter) {
                    break;
                }
            }
        }
    }
}
