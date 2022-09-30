import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

//Enum to select account type
enum AccountType {
    SAVINGS,
    CURRENT
}//Enum to select transaction Status

enum TransectionStatus {
    SUCCESSFUL,
    FAILED
}

class Bank {
    double rateOfInterest = 8.0;
    Scanner sc = new Scanner(System.in);

    // Method to select bank form sbi, icici or boi
    static int selectBank() {
        Scanner sc = new Scanner(System.in);
        System.out.println("<-------------Welcome To Bank----------->");
        System.out.println("Select your bank: 1. SBI  2. ICICI  3.BOI");
        int option = sc.nextInt(); // taking input for selected bank
        //if else condition to verify that selected bank is correct or not
        if (option == 1 || option == 2 || option == 3)
            return option;
        else {
            System.out.println("OOPS!......Incorrect Input.");
            System.exit(0);
            return 0;
        }
    }

    // Method to select bank account type form saving or current
    static AccountType selectAccountType() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select your Account Type: 1. Savings  2. Current");
        int option = sc.nextInt();
        //verifying selected input is correct or not and return enum AccountType else null
        if (option == 1) {
            return AccountType.SAVINGS;
        } else if (option == 2) {
            return AccountType.CURRENT;
        } else {
            System.out.println("OOPS!......Incorrect Input.");
            System.exit(0);
            return null;
        }
    }

    // Method  to withdraw money form bank and log the transaction
    void amountWithdraw(float amount, int account_no) throws InsufficientAmountException, IOException {
        float withdraw_amount; // to store withdraw amount
        try {
            FileWriter file = new FileWriter("log.txt");
            BufferedWriter writer = new BufferedWriter(file);
            Date date = new Date();
            writer.write("Time: " + date.toString());
            writer.newLine();
            writer.write("Account Number: " + account_no);
            writer.newLine();
            System.out.println("Enter Amount to withdraw:");
            withdraw_amount = sc.nextFloat();
            writer.write("Withdraw Amount: " + withdraw_amount);
            writer.newLine();
            // condition to verify withdraw amount is  correct or not
            if (amount < withdraw_amount) {
                writer.write("Available Balance: " + amount);
                writer.newLine();
                writer.write("Transection Status: " + TransectionStatus.FAILED);
                writer.newLine();
                writer.close();
                throw new InsufficientAmountException("OOPS! Insufficient fund.");
            } else {
                writer.write("Amount before deduction: " + amount);
                writer.newLine();
                amount = amount - withdraw_amount;
                writer.write("Amount After deduction: " + amount);
                writer.newLine();
                System.out.println("Amount Debited: " + withdraw_amount);
                writer.newLine();
                System.out.println("Clear balance: " + amount);
                writer.write("Transection Status: " + TransectionStatus.SUCCESSFUL);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

class SBI extends Bank {
    double rateOfInterest = 7.14;

    //float amount=5000;
    // Method to print the details of a bank account selected
    void getDetails(float amount, AccountType account_type, int account_no) {
        System.out.println("Bank Name: SBI");
        System.out.println("Rate of interest: " + rateOfInterest + "%");
        System.out.println("Account Type: " + account_type);
        System.out.println("Account Number: " + account_no);
        System.out.println("Current  Balance: " + amount);
    }
}

class ICICI extends Bank {
    double rateOfInterest = 12.5;

    //float amount=7000;
    // Method to print the details of a bank account selected
    void getDetails(float amount, AccountType account_type, int account_no) {
        System.out.println("Bank Name: ICICI");
        System.out.println("Rate of interest: " + rateOfInterest + "%");
        System.out.println("Account Type: " + account_type);
        System.out.println("Account Number: " + account_no);
        System.out.println("Current Balance:" + amount);
    }
}

class BOI extends Bank {
    double rateOfInterest = 10.6;

    //float amount=4000;
    // Method to print the details of a bank account selected
    void getDetails(float amount, AccountType account_type, int account_no) {
        System.out.println("Bank Name: SBI");
        System.out.println("Rate of interest: " + rateOfInterest + "%");
        System.out.println("Account Type: " + account_type);
        System.out.println("Account Number: " + account_no);
        System.out.println("Current Balance: " + amount);
    }
}

public class Main {
    //Static method to select menu to withdraw amount or show  details
    static int selectMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select menu: 1. Withdraw Amount  2. Show Account details");
        return sc.nextInt();
    }

    public static void main(String args[]) throws InsufficientAmountException {
        float amount = 10000; // common amount for banks
        Scanner sc = new Scanner(System.in);
        int bank = Bank.selectBank(); //  getting selected bank
        AccountType account_type = Bank.selectAccountType(); //getting selected account type
        int option = selectMenu();  //getting selected menu between withdraw or  show  details
        System.out.println("Enter Account Number: ");
        int account_no = sc.nextInt();
        //  switching between banks which one is  selected and catch error if any
        switch (bank) {
            case 1:
                SBI sbi = new SBI();
                if (option == 1) {
                    try {
                        sbi.amountWithdraw(amount, account_no);
                    } catch (InsufficientAmountException e) {
                        System.out.println("Insufficient Fund.....");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (option == 2) {
                    sbi.getDetails(amount, account_type, account_no);
                } else {
                    System.out.println("Failed!....Incorrect Input");
                }
                break;

            case 2:
                ICICI icici = new ICICI();
                if (option == 1) {
                    try {
                        icici.amountWithdraw(amount, account_no);
                    } catch (InsufficientAmountException | IOException e) {
                        System.out.println("Insufficient Fund.....");
                    }
                } else if (option == 2) {
                    icici.getDetails(amount, account_type, account_no);
                } else {
                    System.out.println("Failed!....Incorrect Input");
                }
                break;

            case 3:
                BOI boi = new BOI();
                if (option == 1) {
                    try {
                        boi.amountWithdraw(amount, account_no);
                    } catch (InsufficientAmountException | IOException e) {
                        System.out.println("Insufficient Fund.....");
                    }
                } else if (option == 2) {
                    boi.getDetails(amount, account_type, account_no);
                } else {
                    System.out.println("Failed!....Incorrect Input");
                }

        }

    }
}
