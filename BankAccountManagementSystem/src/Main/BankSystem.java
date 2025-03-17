package Main;

import java.util.Scanner;

public class BankSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n======= WELCOME TO BANK MANAGEMENT SYSTEM =======");

        System.out.print("Enter Account Number: ");
        String accNum = sc.nextLine();

        Account userAccount = TransactionManager.loadAccount(accNum);
        if (userAccount == null) {
            System.out.println("Account not found. Would you like to create a new account? (yes/no)");
            String response = sc.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                userAccount = TransactionManager.createAccount(sc);
            } else {
                System.out.println("Exiting...");
                return;
            }
        }

        while (true) {
            System.out.println("\n1. Deposit\n2. Withdraw\n3. View Account\n4. Transaction History\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double deposit = sc.nextDouble();
                    userAccount.deposit(deposit);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdraw = sc.nextDouble();
                    if (!userAccount.withdraw(withdraw)) {
                        System.out.println("Insufficient Balance!");
                    }
                    break;
                case 3:
                    userAccount.viewAccountDetails();
                    break;
                case 4:
                    userAccount.viewTransactionHistory();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid Choice! Please try again.");
            }
        }
    }
}
