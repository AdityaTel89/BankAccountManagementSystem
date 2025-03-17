package Main;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber, bankName, branch, IFSC, holderName, parentName, address, email, phone, dob, pan;
    private double balance;
    private List<String> transactionHistory = new ArrayList<>();

    public Account(String accountNumber, String bankName, String branch, String IFSC, String holderName, String parentName,
                   String address, String email, String phone, String dob, String pan, double balance) {
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.branch = branch;
        this.IFSC = IFSC;
        this.holderName = holderName;
        this.parentName = parentName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.pan = pan;
        this.balance = balance;
        loadTransactionHistory();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount + " | Balance: " + balance);
            TransactionManager.saveTransaction(accountNumber, "Deposit", amount, balance);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount + " | Balance: " + balance);
            TransactionManager.saveTransaction(accountNumber, "Withdraw", amount, balance);
            System.out.println("Withdrawal successful!");
            return true;
        }
        return false;
    }

    public void viewAccountDetails() {
        System.out.println("\n=========== ACCOUNT DETAILS ===========");
        System.out.printf("Bank: %s | Branch: %s | IFSC: %s%n", bankName, branch, IFSC);
        System.out.printf("Holder: %s | Parent: %s%n", holderName, parentName);
        System.out.printf("Address: %s | Email: %s | Phone: %s%n", address, email, phone);
        System.out.printf("DOB: %s | PAN: %s%n", dob, pan);
        System.out.printf("Balance: %.2f%n", balance);
        System.out.println("=======================================\n");
    }

    public void viewTransactionHistory() {
        System.out.println("\n========== TRANSACTION HISTORY ==========");
        System.out.println("Total Balance: " + balance);
        System.out.println("-----------------------------------------");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
        System.out.println("=========================================\n");
    }

    private void loadTransactionHistory() {
        transactionHistory = TransactionManager.loadTransactionHistory(accountNumber);
    }
}
