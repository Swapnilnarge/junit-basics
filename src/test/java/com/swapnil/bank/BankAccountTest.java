package com.swapnil.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;

    // Runs before every single test method to guarantee isolated state
    @BeforeEach
    void setUp() {
        account = new BankAccount(100.0);
    }

    @Test
    void deposit_ValidAmount_IncreasesBalance() {
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), "Balance should reflect the deposited amount");
    }

    @Test
    void deposit_InvalidAmount_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-20.0));
    }

    @Test
    void withdraw_ValidAmount_DecreasesBalance() {
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance(), "Balance should decrease after withdrawal");
    }

    @Test
    void withdraw_AmountExceedingBalance_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(150.0));
    }

    @Test
    void constructor_NegativeInitialBalance_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-50.0));
    }
    @Test
    void transfer_ValidAmountWithFee_UpdatesBothBalances() {
        BankAccount recipient = new BankAccount(50.0);

        // Act: transfer $20 from account ($100 balance) to recipient ($50 balance)
        account.transfer(recipient, 20.0);

        // Assert: 100 - 20 - 2.50 = 77.50 for sender; 50 + 20 = 70.0 for recipient
        assertEquals(77.50, account.getBalance());
        assertEquals(70.0, recipient.getBalance());
    }

    @Test
    void transfer_SenderCannotAffordFee_ThrowsExceptionAndLeavesBalancesUnchanged() {
        BankAccount recipient = new BankAccount(50.0);

        // Act & Assert: $99 transfer requires $101.50 total, which exceeds the $100 balance
        assertThrows(IllegalArgumentException.class, () -> account.transfer(recipient, 99.0));

        // Verify neither balance mutated when the transaction failed
        assertEquals(100.0, account.getBalance());
        assertEquals(50.0, recipient.getBalance());

    }
    @Test
    void transfer_SameAccount_ThrowsExceptionAndLeavesBalanceUnchanged() {
        assertThrows(IllegalArgumentException.class, () -> account.transfer(account, 20.0));
        assertEquals(100.0, account.getBalance());
    }
}