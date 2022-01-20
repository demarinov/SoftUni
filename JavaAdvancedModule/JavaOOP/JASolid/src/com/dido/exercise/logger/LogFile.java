package com.dido.exercise.logger;

public class LogFile implements File {

    private StringBuilder output;

    public LogFile() {
        this.output = new StringBuilder();
    }

    public int getSize() {
        // to_do sum of all ascii chars
        int size = 0;
        for (int i = 0; i < output.length(); i++) {
            int ascii = output.charAt(i);
            if (Character.isAlphabetic(ascii)) {
                size += ascii;
            }
        }
        return size;
    }

    @Override
    public void write(String message) {
        output.append(message);
    }
}
