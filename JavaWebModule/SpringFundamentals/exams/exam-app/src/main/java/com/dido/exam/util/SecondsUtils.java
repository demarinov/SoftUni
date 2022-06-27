package com.dido.exam.util;

public class SecondsUtils {

    public static String convertToMinutes(Long secondsInput) {

        long minutes = secondsInput / 60;
        long seconds = secondsInput % 60;

        return String.format("%d:%02d", minutes, seconds);
    }
}
