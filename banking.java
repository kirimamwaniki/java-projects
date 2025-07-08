import java.util.*;

class account{
    Scanner scan = new Scanner(System.in);

    int acc_no;
    String name;
    float amount;
    boolean hasMoney = true;
    public account(int acc_no, String name, float amount){
        this.acc_no = acc_no;
        this.name = name;
        this.amount = amount;
    }

    void deposit(){
        //verifying the account
        /*System.out.printf("Enter account no: ");
        acc_no = scan.nextInt();
        scan.nextLine();*/

        //depositing amount
        System.out.printf("Enter amount to deposit: ");
        float dep = scan.nextFloat();
        amount = amount + dep;
    }

    void withdraw(){
        //user entering value to withdraw
        System.out.printf("How much do you want to deposit: ");
        float withdraw = scan.nextFloat();

        if(withdraw > amount){
            System.out.println("Not enough money in bank");
        }else{
            amount = amount - withdraw;
        }
    }
    void prntDetails(){
        System.out.println("Account no: " +acc_no + "\n" + "name: " + name + "\n" + "amount: " + amount );
    }

}


public class banking{
    static Scanner scan = new Scanner(System.in);
    static ArrayList<account> accounts = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("hello world");
        welcome();
    }


    static void welcome(){
        System.out.println("welcome to the banking system. Choose an option: ");
        System.out.println("1. login" + "\n" + "2. create account" + "\n3. exit");
        int choice = scan.nextInt();
        scan.nextLine();

        //choosing a path
        while(true){
            if(choice == 1){
                login();
            }else if(choice == 2){
                signup();
            }else if(choice == 3){
                System.out.println("Thank you for banking with us");
                break;
            }else{
                System.out.println("Invalid comand");
                System.out.println("Choose an option: ");
                choice = scan.nextInt();
            }
        }
    }


    static void signup(){
        // creating an account
        account a1 = new account(0, "", 0);
        
        // accepting name of user
        System.out.println("enter name: ");
        String name = scan.nextLine();
        scan.nextLine();
        a1.name = name;

        //initial deposit
        System.out.println("Would you like to deposit");
        String choice = scan.nextLine();

        if (choice.equalsIgnoreCase("yes")){
            a1.deposit();
        }

        //assigning account no
        a1.acc_no = accounts.size() + 1;


        System.out.println("Account created succesfully");
        System.out.println("your account no is: " + a1.acc_no);
        accounts.add(a1);
        welcome();
    };


    static void login() {
    System.out.println("Enter account no: ");
    int loginAccNo = scan.nextInt();

    // Search for the account
    account found = null;
    for (account acc : accounts) {
        if (acc.acc_no == loginAccNo) {
            found = acc;
            break;
        }
    }

    if (found != null) {
        System.out.println("Login successful. Welcome " + found.name);
        accoInterface(found); // pass the logged-in account
    } else {
        System.out.println("Account not found.\n");
        welcome();  // return to main menu
    }
}


    //account interface
    static void accoInterface(account acc) {
    while (true) {
        System.out.println("Choose the preferred action: ");
        System.out.println("1. Deposit \n2. Withdraw \n3. Print Details \n4. Log out");
        int choice = scan.nextInt();

        if (choice == 1) {
            acc.deposit();
            accoInterface(acc);
        } else if (choice == 2) {
            acc.withdraw();
            accoInterface(acc);
        } else if (choice == 3) {
            acc.prntDetails();
            accoInterface(acc);
        } else if (choice == 4) {
            System.out.println("Logged out.");
            break;
        } else {
            System.out.println("Invalid command, try again.");
        }
    }

    welcome(); // back to main menu after logout
}

}