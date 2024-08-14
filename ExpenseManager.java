package Machine.splitwise;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ExpenseManager {
    private Map<String,User> users;
    private Map<String,Map<String,Double>> balances;


    public ExpenseManager() {
        users = new HashMap<>();
        balances = new HashMap<>();
    }

    public void addUser(User user){
        users.put(user.getId(),user);
        balances.put(user.getId(),new HashMap<>());

    }
    public void addExpense(Expense expense){
        String paidBy = expense.getPaidBy();
        List<String>participants = expense.getParticipants();
        Double amount = expense.getAmount();
        List<Double> shares = expense.getShares();
        switch (expense.getExpense()){
            case EQUAL :
                double equalShaes = Math.round((amount/ participants.size())*100.0/100.0);
                for(String particpant:participants){
                    updateBalances(paidBy,particpant,equalShaes);
                }
                break;

            case EXACT:
                double totalShare=0;
                for(double share:shares){
                    totalShare+=share;
                }
                if(totalShare!=amount){
                    throw  new IllegalArgumentException("Total Exact shares does not Match the amount");
                }
                for(int i=0;i< participants.size();i++){
                    updateBalances(paidBy, participants.get(i),shares.get(i) );
                }
                break;

            case PERCENT:
                double totalPercentage=0;
                for(double share : shares){
                    totalPercentage+=share;
                }
                if(totalPercentage!=100){
                    throw new IllegalArgumentException("totalShare does not Match the percentage amount");
                }
                for(int i=0;i< participants.size();i++){
                    updateBalances(paidBy,participants.get(i),(shares.get(i)*amount)/100.0);
                }
                break;
        }
    }
    private void updateBalances(String paidBy,String paidTo,double amount){
        if(!paidBy.equals(paidTo)){
            balances.get(paidBy).put(paidTo,balances.get(paidBy).getOrDefault(paidTo,0.0)+amount);
            balances.get(paidTo).put(paidBy,balances.get(paidTo).getOrDefault(paidBy,0.0)-amount);
        }
    }
    public void showBalances(){
        boolean noBalances=true;
        for(Map.Entry<String,Map<String,Double>>userBalance : balances.entrySet()){
            for(Map.Entry<String,Double> balance : userBalance.getValue().entrySet()){
                    if(balance.getValue()!=0){
                        noBalances=false;
                        printBalance(userBalance.getKey(),balance.getKey(),balance.getValue());
                    }
            }
        }
        if(noBalances){
            System.out.println("No Balance");
        }

    }
    public void showBalances(String Id){
        boolean noBalances=true;
        Map<String,Double> userBalances = balances.get(Id);
        for(Map.Entry<String,Double>balance:userBalances.entrySet()){
                if(balance.getValue()!=0){
                    noBalances=true;
                    printBalance(Id,balance.getKey(),balance.getValue());
                }
        }
        if(noBalances){
            System.out.println("No Balance");
        }
    }

    public void printBalance(String user1,String user2, double amount){
        if(amount>0){
            System.out.printf("%s owes %s: %.2f%n", user2, user1, Math.abs(amount));
        }
        else{
            System.out.printf("%s owes %s:%.2f%n",user1,user2,Math.abs(amount));
        }
    }
}
