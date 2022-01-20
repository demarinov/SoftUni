package com.dido.exercise.calculator;

import java.util.ArrayList;
import java.util.List;

public class MSOperation implements Operation {
    private List<Integer> operands;
    private int result;
    private boolean isCompleted;

    public MSOperation(){

        this.operands = new ArrayList<>();
        this.isCompleted = false;
    }

    @Override
    public void addOperand(int operand) {
        this.operands.add(operand);

        // put in memory cache
        this.isCompleted = true;
        CalculatorMemory.addToMemory(operand);
        this.result = operand;
    }

    @Override
    public boolean isCompleted() {
        return this.isCompleted;
    }

    @Override
    public int getResult() {
        return this.result;
    }
}
