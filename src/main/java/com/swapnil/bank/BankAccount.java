package com.swapnil.bank;

public class BankAccount {
    private double balance;
    static double transactionCost = 2.50;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        this.balance -= amount;
    }

    public void transfer(BankAccount targetAccount, double amount) {
        if (targetAccount == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
        if (targetAccount == this){
            throw new IllegalArgumentException("Cannot transfer money to the same account.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero");
        }
        if ((amount + transactionCost) > this.balance) {
            throw new IllegalArgumentException("Insufficient funds for transfer and fee");
        }
        this.balance -= (amount + transactionCost);
        targetAccount.deposit(amount);
    }

    public double getBalance() {
        return this.balance;
    }
}
