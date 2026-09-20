package com.swapnil.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class BankAccountTest {

    @Test
    void constructor_negativeInitialBalance_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount(-10.0);
        });
    }

    @Test
    void constructor_validInitialBalance_setsInitialBalance() {
        BankAccount account = new BankAccount(100);
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    void transfer_accountExistenceCheck_throwsException(){
        BankAccount account = new BankAccount(10);
        BankAccount account1 = null;
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            account.transfer(account1,9.0);
        });
    }

    @Test
    void transfer_sameAccount_throwsException(){
        BankAccount account = new BankAccount(10.0 );
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            account.transfer(account,20);
        });
    }

    @Test
    void transfer_validTransfer_amountTransfered(){
        BankAccount account = new BankAccount(100.0);
        BankAccount account1 = new BankAccount(100.0);
        account.transfer(account1,50.0);
        Assertions.assertEquals(50.0,account.getBalance());
        Assertions.assertEquals(150.0 ,account1.getBalance());
    }

    @Test
    void deposit_negativeAmount_throwsException() {
        BankAccount account = new BankAccount(12.0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-5.0);
        });
    }

    @Test
    void deposit_validAmount_increasedBalance() {
        BankAccount account = new BankAccount(20.0);
        account.deposit(20.0);
        Assertions.assertEquals(40.0 , account.getBalance());
    }

    @Test
    void withdraw_negativeAmount_throwsException(){
        BankAccount account = new BankAccount(10.0);
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            account.withdraw(-10.0);
        });
    }

    @Test
    void withdraw_lowBalance_throwsException(){
        BankAccount account = new BankAccount(10.0);
        Assertions.assertThrows(IllegalArgumentException.class,()->{
           account.withdraw(20.0);
        });
    }

    @Test
    void withdraw_validAmount_withdrawalSuccess(){
        BankAccount account = new BankAccount(10.0);
        account.withdraw(6.0);
        Assertions.assertEquals(4.0,account.getBalance());
    }
    @Test
    void getter_balanceCheck_validBalance(){
        BankAccount account = new BankAccount(10.0);
        Assertions.assertEquals(10.0,account.getBalance());
    }
}


