package Machine.splitwise;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;



public class ExpenseSharing {
    public static void main(String[] args){

        ExpenseManager expenseManager = new ExpenseManager();

        // Adding some users
        expenseManager.addUser(new User("u1", "User1", "user1@example.com", "1234567890"));
        expenseManager.addUser(new User("u2", "User2", "user2@example.com", "1234567891"));
        expenseManager.addUser(new User("u3", "User3", "user3@example.com", "1234567892"));

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] inputParts = input.split(" ");

            switch (inputParts[0]) {
                case "EXPENSE":
                    handleExpense(inputParts, expenseManager);
                    break;
                case "SHOW":
                    handleShow(inputParts, expenseManager);
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }

    }
    private static void handleExpense(String[]inputParts,ExpenseManager expenseManager){
        String paidBy=inputParts[1];
        int numUsers = Integer.parseInt(inputParts[2]);
        List<String> participants = Arrays.asList(inputParts).subList(3,3+numUsers);
        ExpenseType expenseType= ExpenseType.valueOf(inputParts[3+numUsers]);
        double amount = Double.parseDouble(inputParts[4+numUsers]);

        List<Double> shares = new ArrayList<>();
        if(expenseType!=ExpenseType.EQUAL){
            for(int i=5+numUsers;i<inputParts.length;i++){
                shares.add(Double.parseDouble(inputParts[i]));
            }
        }
        Expense expense = new Expense(paidBy,participants,expenseType,amount,shares);
        expenseManager.addExpense(expense);
    }

    public static void handleShow(String[]inputParts,ExpenseManager expenseManager){
        if(inputParts.length==1){
            expenseManager.showBalances();
        }
        else{
            expenseManager.showBalances(inputParts[1]);
        }
    }
}
