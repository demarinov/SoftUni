package com.dido.exam;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiDetector {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();

        Pattern digitsPattern = Pattern.compile("([0-9]+)");
        Pattern emojiPattern = Pattern.compile("([:]{2}|[*]{2})([A-Z][a-z]{2,})\\1");

        Matcher matcher = digitsPattern.matcher(text);

        BigInteger emojiThreshold = BigInteger.ONE;
        while(matcher.find()) {

            //System.out.println(matcher.group(1));
            String numberString = matcher.group(1);
            emojiThreshold = emojiThreshold.multiply(multiplyDigits(numberString));
        }

        matcher = emojiPattern.matcher(text);

        List<String> emojis = new ArrayList<>();
        List<String> emojisSeparator = new ArrayList<>();

        while(matcher.find()) {
            emojis.add(matcher.group(2));
            emojisSeparator.add(matcher.group(1));
            //System.out.println(matcher.group(2));
        }

        List<String> coolEmojis = new ArrayList<>();

        for (int i = 0; i < emojis.size(); i++) {

            BigInteger coolness = BigInteger.ZERO;
            String emoji = emojis.get(i);

            coolness = getEmojiCoolness(emoji);

            int comparison = coolness.compareTo(emojiThreshold);

            if (comparison == 0 || comparison == 1) {
                coolEmojis.add(emojisSeparator.get(i)+emoji+emojisSeparator.get(i));
            }
        }

        System.out.println("Cool threshold: "+emojiThreshold);
        System.out.println(emojis.size()+" emojis found in the text. The cool ones are:");

        for (int i = 0; i < coolEmojis.size(); i++) {
            System.out.println(coolEmojis.get(i));
        }
    }

    public static BigInteger getEmojiCoolness(String emoji) {

        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < emoji.length(); i++) {

            char c = emoji.charAt(i);
            result = result.add(BigInteger.valueOf(c));
        }

        return result;
    }

    public static BigInteger multiplyDigits(String numStr) {

        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < numStr.length(); i++) {

            int num = Integer.parseInt(String.format("%c",numStr.charAt(i)));

            result = result.multiply(BigInteger.valueOf(num));
        }

        return result;
    }
}
