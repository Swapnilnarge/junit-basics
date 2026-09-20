package com.swapnil.bank;


public class BankAccount {
    private double balance;

    public BankAccount(double intialAmount){
        if (intialAmount < 0) {
            throw new IllegalArgumentException("the initial amount cannot be less than zero.");
        }
        this.balance = intialAmount;
    }

    public void transfer(BankAccount targetAccount , double amount){
        if(targetAccount == null){
            throw new IllegalArgumentException("Account is missing.");
        }

        if(this == targetAccount){
            throw new IllegalArgumentException("Cannot transfer amount from an account to the same account.");
        }
        this.withdraw(amount);
        targetAccount.deposit(amount);
    }

    public void deposit(double amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("deposit amount must be greater than zero.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount){

        if(amount <= 0) {
            throw new IllegalArgumentException("withdrawal amount must be greater than zero.");
        }
        if (this.balance < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance -= amount;
    }

    
    public double getBalance() {
        return balance;
    }

}
