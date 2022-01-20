package com.dido.more;

import java.util.*;
import java.util.stream.Collectors;

public class MorseCodeTranslator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder decodedSentence = new StringBuilder();

        // prepare the Morse code hash table with all codes and corresponding chars
        Map<String, String> morseTable = new LinkedHashMap<>();
        initMorseTable(morseTable);

        // parse based on | to get the encodedWords
        List<String> encodedWords = Arrays.stream(sc.nextLine().split("\\|"))
                .collect(Collectors.toList());
        // parse based on space to get the encodedChar for each word
        for (int i = 0; i < encodedWords.size(); i++) {

            String encodedWord = encodedWords.get(i);

            // decode each encodedChar by using the morse code hash table
            // store the decodedChar into decodedWord
            String decodedWord = decodeWord(encodedWord,morseTable);

            // store the decodedWord into decodedWords list or stringbuilder with space
            decodedSentence.append(decodedWord);

            if (i < encodedWords.size()-1) {
                decodedSentence.append(" ");
            }
        }


        System.out.println(decodedSentence.toString());

    }

    // decode each encodedChar by using the morse code hash table
    public static String decodeWord(String encodedWord, Map<String, String> morseTable) {

        StringBuilder decodedWord = new StringBuilder();
        String[] encodedChars = encodedWord.split("\\s");
        for (int i = 0; i < encodedChars.length; i++) {

            String c = encodedChars[i];

            if (morseTable.containsKey(c)) {
                decodedWord.append(morseTable.get(c));
            }

        }

        return decodedWord.toString();
    }

    public static void initMorseTable(Map<String, String> morseTable) {

        morseTable.putIfAbsent(".-","A");
        morseTable.putIfAbsent("-...","B");
        morseTable.putIfAbsent("-.-.","C");
        morseTable.putIfAbsent("-..","D");
        morseTable.putIfAbsent(".","E");
        morseTable.putIfAbsent("..-.","F");
        morseTable.putIfAbsent("--.","G");
        morseTable.putIfAbsent("....","H");
        morseTable.putIfAbsent("..","I");
        morseTable.putIfAbsent(".---","J");
        morseTable.putIfAbsent("-.-","K");
        morseTable.putIfAbsent(".-..","L");
        morseTable.putIfAbsent("--","M");
        morseTable.putIfAbsent("-.","N");
        morseTable.putIfAbsent("---","O");
        morseTable.putIfAbsent(".--.","P");
        morseTable.putIfAbsent("--.-","Q");
        morseTable.putIfAbsent(".-.","R");
        morseTable.putIfAbsent("...","S");
        morseTable.putIfAbsent("-","T");
        morseTable.putIfAbsent("..-","U");
        morseTable.putIfAbsent("...-","V");
        morseTable.putIfAbsent(".--","W");
        morseTable.putIfAbsent("-..-","X");
        morseTable.putIfAbsent("-.--","Y");
        morseTable.putIfAbsent("--..","Z");
        morseTable.putIfAbsent(".----","1");
        morseTable.putIfAbsent("..---","2");
        morseTable.putIfAbsent("...--","3");
        morseTable.putIfAbsent("....-","4");
        morseTable.putIfAbsent(".....","5");
        morseTable.putIfAbsent("-....","6");
        morseTable.putIfAbsent("--...","7");
        morseTable.putIfAbsent("---..","8");
        morseTable.putIfAbsent("----.","9");
        morseTable.putIfAbsent("-----","0");
    }
 }
