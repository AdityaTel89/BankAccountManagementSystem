package Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionManager {
    private static final String TRANSACTION_FILE = "transactions.txt";
    private static final String ACCOUNT_FILE = "accounts.txt";

    public static void saveTransaction(String accountNumber, String type, double amount, double balance) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE, true))) {
            writer.write("Account Number: " + accountNumber + " | Type: " + type +
                    " | Amount: " + amount + " | Balance: " + balance + "\n");
        } catch (IOException e) {
            System.out.println("Error saving transaction.");
        }
    }

    public static List<String> loadTransactionHistory(String accountNumber) {
        List<String> history = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Account Number: " + accountNumber)) {
                    history.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions.");
        }
        return history;
    }

    public static Account loadAccount(String accountNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length < 12) {
                    System.out.println("Error: Malformed account data. Expected 12 fields but found " + parts.length);
                    continue;
                }

                if (parts[0].trim().equals(accountNumber)) {
                    return new Account(
                            parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(),
                            parts[4].trim(), parts[5].trim(), parts[6].trim(), parts[7].trim(),
                            parts[8].trim(), parts[9].trim(), parts[10].trim(),
                            Double.parseDouble(parts[11].trim())
                    );
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading account details: " + e.getMessage());
        }
        return null;
    }

    public static Account createAccount(Scanner sc) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE, true))) {
            System.out.println("\n======= Create New Account =======");
            System.out.print("Enter Account Number: ");
            String accountNumber = sc.nextLine();
            System.out.print("Enter Bank Name: ");
            String bankName = sc.nextLine();
            System.out.print("Enter Branch: ");
            String branch = sc.nextLine();
            System.out.print("Enter IFSC Code: ");
            String IFSC = sc.nextLine();
            System.out.print("Enter Account Holder Name: ");
            String holderName = sc.nextLine();
            System.out.print("Enter Parent Name: ");
            String parentName = sc.nextLine();
            System.out.print("Enter Address: ");
            String address = sc.nextLine();
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Phone Number: ");
            String phone = sc.nextLine();
            System.out.print("Enter Date of Birth (dd/mm/yyyy): ");
            String dob = sc.nextLine();
            System.out.print("Enter PAN Number: ");
            String pan = sc.nextLine();
            System.out.print("Enter Initial Balance: ");
            double balance = sc.nextDouble();
            sc.nextLine();

            String formattedAccount = String.format(
                    "Account Number: %s | Bank Name: %s | Branch: %s | IFSC: %s | Holder Name: %s | Parent Name: %s | " +
                            "Address: %s | Email: %s | Phone: %s | DOB: %s | PAN: %s | Balance: %.2f%n",
                    accountNumber, bankName, branch, IFSC, holderName, parentName, address, email, phone, dob, pan, balance
            );
            writer.write(formattedAccount);

            System.out.println("Account created successfully!");

            return new Account(accountNumber, bankName, branch, IFSC, holderName, parentName,
                    address, email, phone, dob, pan, balance);
        } catch (IOException e) {
            System.out.println("Error creating account.");
            return null;
        }
    }
}
