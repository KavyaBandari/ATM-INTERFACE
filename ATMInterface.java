import java.util.ArrayList;
import java.util.Scanner;

// Class representing a bank account
class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private ArrayList<String> transactionHistory;

    // Constructor
    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(String.format("Deposited %.2f", amount));
            System.out.printf("Deposited %.2f successfully.%n", amount);
            displayBalance();
        } else {
            System.out.println("Invalid amount for deposit.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(String.format("Withdrawn %.2f", amount));
            System.out.printf("Withdrawn %.2f successfully.%n", amount);
            displayBalance();
        } else {
            System.out.println("Insufficient funds or invalid amount for withdrawal.");
        }
    }

    // Method to transfer money to another account (not fully implemented here)
    public void transfer(double amount, BankAccount receiver) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            receiver.deposit(amount);
            transactionHistory.add(String.format("Transferred %.2f to %s", amount, receiver.getAccountHolderName()));
            System.out.printf("Transferred %.2f successfully to %s.%n", amount, receiver.getAccountHolderName());
            displayBalance();
        } else {
            System.out.println("Insufficient funds or invalid amount for transfer.");
        }
    }

    // Method to display balance
    public void displayBalance() {
        System.out.printf("Current Balance: %.2f%n", balance);
    }

    // Method to display transaction history
    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    // Getter for account holder name (for transfer purposes)
    public String getAccountHolderName() {
        return accountHolderName;
    }
}

// Main class for ATM interface
public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating a bank account for demonstration
        BankAccount account = new BankAccount("123456789", "John Doe");

        int choice = 0;
        while (choice != 6) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. View Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    System.out.print("Enter receiver's account holder name: ");
                    String receiverName = scanner.next(); // Simplified, should be account number or ID in real implementation
                    // Assuming receiver account exists and is known
                    BankAccount receiverAccount = new BankAccount("987654321", receiverName);
                    account.transfer(transferAmount, receiverAccount);
                    break;
                case 4:
                    account.displayBalance();
                    break;
                case 5:
                    account.displayTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }
        scanner.close();
    }
}
