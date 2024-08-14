package Machine.splitwise;
import java.util.List;

public class Expense {
    public String getPaidBy() {
        return paidBy;
    }



    public List<String> getParticipants() {
        return participants;
    }



    public ExpenseType getExpense() {
        return expense;
    }



    public double getAmount() {
        return amount;
    }



    public List<Double> getShares() {
        return shares;
    }


    private String paidBy;

    public Expense(String paidBy, List<String> participants, ExpenseType expense, double amount, List<Double> shares) {
        this.paidBy = paidBy;
        this.participants = participants;
        this.expense = expense;
        this.amount = amount;
        this.shares = shares;
    }

    private List<String> participants;
    private ExpenseType expense;
    private double amount;
    private List<Double>shares;
}
