package com.dido.exercise.word.Initialization;

import com.dido.exercise.word.CommandImpl;
import com.dido.exercise.word.CommandInterface;

public class Initialization {

    public static CommandInterface buildCommandInterface(StringBuilder text) {
        return new CommandImpl(text);
    }
}
