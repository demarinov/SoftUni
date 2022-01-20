package com.dido.lab;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BankAccount {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        String input = sc.nextLine();

        List<BankAccount> bankAccounts = new LinkedList<>();
        while(!"End".equals(input)) {
            String[] commandData = input.split("\\s");

            String command = commandData[0];

            // •	Create
            //•	Deposit {Id} {Amount}
            //•	SetInterest {Interest}
            //•	GetInterest {ID} {Years}
            //•	End
            switch (command) {

                case "Create":
                    BankAccount bankAccount = new BankAccount();
                    System.out.printf("Account ID%d created%n",bankAccount.getId());
                    bankAccounts.add(bankAccount);
                    break;
                case "Deposit":
                    Integer id = Integer.parseInt(commandData[1]);
                    Double amount = Double.parseDouble(commandData[2]);

                    // check id
                    if (bankAccounts.size() < id) {
                        System.out.printf("Account does not exist%n");
                        break;
                    }
                    bankAccounts.get(id-1).deposit(amount);
                    DecimalFormat formatter  = new DecimalFormat("#.###");
                    System.out.printf("Deposited %s to ID%d%n", formatter.format(amount),id);
                    break;
                case "SetInterest":
                    Double interestRate = Double.parseDouble(commandData[1]);
                    BankAccount.setInterestRate(interestRate);
                    break;
                case "GetInterest":
                    id = Integer.parseInt(commandData[1]);
                    Integer years = Integer.parseInt(commandData[2]);

                    // check id
                    if (bankAccounts.size() < id) {
                        System.out.printf("Account does not exist%n");
                        break;
                    }
                    System.out.printf("%.2f%n",bankAccounts.get(id-1).getInterest(years));
                    break;
                default:
                    break;
            }

            input = sc.nextLine();
        }
    }

    private static final Double DEFAULT_INTEREST_RATE = 0.02d;
    private static Double globalInterestRate = DEFAULT_INTEREST_RATE;
    private static Integer bankAccountCount = 1;
    private Integer id;
    private Double balance;

    public BankAccount() {
        this.id = bankAccountCount++;
        this.balance = 0.0d;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void deposit(Double amount) {

        this.balance += amount;
    }

    public Double getInterest(int years) {

        return BankAccount.globalInterestRate * getBalance() * years;
    }

    public static Double getInterestRate() {
        return globalInterestRate;
    }

    public static void setInterestRate(Double globalInterestRate) {
        BankAccount.globalInterestRate = globalInterestRate;
    }
}
