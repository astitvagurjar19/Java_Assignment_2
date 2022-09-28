import java.util.Scanner;

enum AccountType{
    SAVINGS,
    CURRENT
}
class Bank {
    double rateOfInterest=8.0;
    Scanner sc =new Scanner(System.in);
    static int  selectBank(){
        Scanner sc =new Scanner(System.in);
        System.out.println("<-------------Welcome To Bank----------->");
        System.out.println("Select your bank: 1. SBI  2. ICICI  3.BOI");
        int option= sc.nextInt();
        if (option==1 || option==2 || option==3)
            return option;
        else{
            System.out.println("OOPS!......Incorrect Input.");
            System.exit(0);
            return 0;
        }
    }
    static AccountType selectAccountType(){
        Scanner sc =new Scanner(System.in);
        System.out.println("Select your Account Type: 1. Savings  2. Current");
        int option= sc.nextInt();
        if(option==1){
            return AccountType.SAVINGS;
        } else if (option==2) {
            return AccountType.CURRENT;
        }
        else {
            System.out.println("OOPS!......Incorrect Input.");
            System.exit(0);
            return null;
        }
    }
    void amountWithdraw(float amount)  throws InsufficientAmountException{
        float withdraw_amount;
        System.out.println("Enter Amount to withdraw:");
        withdraw_amount= sc.nextFloat();
            if(amount<withdraw_amount) {
                throw new InsufficientAmountException("OOPS! Insufficient fund.");
            }
        else {
            amount=amount-withdraw_amount;
            System.out.println("Amount Debited: "+withdraw_amount);
            System.out.println("Clear balance: "+amount);
        }
    }

}
class SBI extends Bank{
    double  rateOfInterest=7.14;
    //float amount=5000;
     void getDetails(float amount, AccountType account_type){
         System.out.println("Bank Name: SBI");
         System.out.println("Rate of interest: "+rateOfInterest+"%");
         System.out.println("Account Type: "+account_type);
         System.out.println("Current  Balance: "+amount);
     }
}
class ICICI extends Bank{
    double  rateOfInterest=12.5;
    //float amount=7000;
    void getDetails(float amount, AccountType account_type){
        System.out.println("Bank Name: ICICI");
        System.out.println("Rate of interest: "+rateOfInterest+"%");
        System.out.println("Account Type: "+account_type);
        System.out.println("Current Balance:"+amount);
    }
}
class BOI extends Bank{
    double  rateOfInterest=10.6;
    //float amount=4000;
    void getDetails(float amount, AccountType account_type){
        System.out.println("Bank Name: SBI");
        System.out.println("Rate of interest: "+rateOfInterest+"%");
        System.out.println("Account Type: "+account_type);
        System.out.println("Current Balance: "+amount);
    }
}
public class Main{
    static int selectMenu(){
        Scanner sc =new Scanner(System.in);
        System.out.println("Select menu: 1. Withdraw Amount  2. Show Account details");
        return sc.nextInt();
    }
    public static void main(String args[]) throws InsufficientAmountException {
        float amount=10000;

        int bank = Bank.selectBank();
        AccountType account_type=Bank.selectAccountType();
        int option = selectMenu();
        switch (bank){
            case 1:
                SBI sbi=new SBI();
                if(option==1){
                    try {
                        sbi.amountWithdraw(amount);
                    } catch (InsufficientAmountException e) {
                        System.out.println("Insufficient Fund.....");
                    }
                } else if (option==2) {
                    sbi.getDetails(amount,account_type);
                }
                else {
                    System.out.println("Failed!....Incorrect Input");
                }
                break;

            case 2:
                ICICI icici=new ICICI();
                if(option==1){
                    try {
                        icici.amountWithdraw(amount);
                    } catch (InsufficientAmountException e) {
                        System.out.println("Insufficient Fund.....");
                    }
                } else if (option==2) {
                    icici.getDetails(amount,account_type);
                }
                else {
                    System.out.println("Failed!....Incorrect Input");
                }
                break;

            case 3:
                BOI boi = new BOI();
                if(option==1){
                    try {
                        boi.amountWithdraw(amount);
                    } catch (InsufficientAmountException e) {
                        System.out.println("Insufficient Fund.....");
                    }
                } else if (option==2) {
                    boi.getDetails(amount,account_type);
                }
                else {
                    System.out.println("Failed!....Incorrect Input");
                }

        }

    }
}
