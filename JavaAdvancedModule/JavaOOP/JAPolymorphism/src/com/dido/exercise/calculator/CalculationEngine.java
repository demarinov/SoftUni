package com.dido.exercise.calculator;

public class CalculationEngine {
    private int result;
    private Operation currentOperation;
    private Operation previousOperation;

    public CalculationEngine(){
        this.result = 0;
        this.currentOperation = null;
        this.previousOperation = null;
    }

   public void pushNumber(int number) {
        if (this.currentOperation != null) {
            currentOperation.addOperand(number);

            if (currentOperation.isCompleted()) {
                this.result = currentOperation.getResult();
                this.currentOperation = null;

                if (previousOperation != null) {
                    this.currentOperation = previousOperation;
                    previousOperation = null;
                    this.pushNumber(this.result);
                }
            }
        } else {
            this.result = number;
        }
    }

    void pushOperation(Operation operation) {
//        if(operation == null){
//            return;
//        }
        if (operation.isCompleted()) {
            this.pushNumber(operation.getResult());
        } else {
            if (operation instanceof MROperation) {
                previousOperation = currentOperation;
            }
            this.currentOperation = operation;
            this.pushNumber(this.result);
        }
    }

    int getCurrentResult() {
        return this.result;
    }
}
