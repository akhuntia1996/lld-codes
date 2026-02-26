/*
Splitwise --
----------------
Functional --
Add Expense on a person directly
Add Group and add friends to it
Add Expense on group
    choose whom to share the expense
    Change the percent share
View balance for each user
View group balance
Settle up payments

Optional
Support currencies

Non functional --
Consistency
Thread safe

Entities --
Expense
User
Group

Classes --
User
- id
- details

Group
- id
- details
- List<User>
- List<Expense>

Balance 
- currency
- amount

Expense
- id
- isSettled
- amount
- SplitStrategy
- groupId
- Map<UserId, Units>

Ledger
- id
- source
- dest
- expense

PaymentGraph 
- Node = userid
- Edge = ExpenseId
- Map<Userid, Map<Userid, ExpenseId>>
* addEdge(source, dest, expenseId) -> fetch the graph from the ledger, 
    add the egde to the graph, 
    call mst for source and dest, 
    the Update the ledger
* mst(userid) -> minimum spanning tree
* removeEdge(source, dest, expenseId)
    get the graph from the ledger for the source and dest
    remove the edge
    update the ledger

Client 
* showExpenses(userId)
* showGroupExpenses(groupId)
* addExpenseDirect(userid, userid, amount)
* addExpenseGroup(userId, groupId, amount)
* addExpenseDirect(userId, userId, amount, Map<UserId, Units>)
* addExpenseGroup(userId, groupId, amount, Map<UserId, Units>)

*/

import javax.swing.GroupLayout.Group;

class Client {
    public static void main(String[] args) {
        Splitwise splitwise = new Splitwise(new PercentageSplitStrategy());
        
        // Directly adding expense in 2 users
        User u1, u2;
        splitwise.addExpenseDirect(u1, u2, 100);

        // Add expense in a group
        splitwise.addExpenseGroup(u1, g1, 100);

        // find all expends in a group
        splitwise.getGroupExpenses(g1);

        // find all expense for myself
        splitwise.getMyExpenses(u1);

        // find all final payment 
        splitwise.getFinalAmounts(u1);
    }
}

class User {
    int userid;
}

class Group {
    int id;
    List<Integer> userIds;  
    List<Integer> expenseIds;
}

class Expense {
    int id;
    int paidBy;
    int groupId;
    int amount;
    boolean isSettled;
    SplitStrategy splitStrategy;
    Map<Integer, Integer> units;
}

class Splitwise {
    public SplitStrategy splitStrategy;
    public Splitwise(SplitStrategy splitStrategy) {
        this.splitStrategy = splitStrategy();
    }

    public List<Expense> getGroupExpenses(int groupId) {
        return expenseRepo.findByGroupId(groupId);
    }

    public List<Expense> getMyExpenses(int userId) {
        return expenseRepo.findByPaidBy(userId);
    }

    public Map<Integer, Integer> getFinalAmounts(int userId) {
        List<Ledger> ledger = ledgerRepository.findByUser(userid);
        // convert Ledger to map
        return map;
    }

    public void addExpenseDirect(User u1, User u2, int amt){
        Expense expense = new Expense(u1, null, 100, false, this.splitStrategy, new HashMap<>(){put(u1, this.splitStrategy.getEqualContribution(2))});
        splitStrategy.addExpense(u1, u2, 100);
    }
    public void addExpenseGroup(User u1, Group g1, int amt){
        Map<Integer, Integer> units = new HashMap<>();
        for(User u : g1.getUsers()){
            units.add(u.getUserId(), this.splitStrategy.getEqualContribution(g1.getUserIds().size()));
            splitStrategy.addExpense(u1, u, 100);
        }
        Expense expense = new Expense(u1, g1, 100, false, this.splitStrategy, units);
    }
    public void addExpenseDirect(User u1, User u2, int amt, Map<User, Integer> units){
        if(units.size() > 2)
            return;
        splitStrategy.addExpense(u1, u2, 100, units);
    }
    public void addExpenseGroup(User u1, Group g1, int amt, Map<User, Integer> units){
        if(g1.getUsers.size() != units.size())
            return;
        for(User u : g1.getUsers())
            splitStrategy.addExpense(u1, u, 100, units);
    }
}

abstract class SplitStrategy {
    public abstract void addExpense(User u1, User u2, int amt);
    public abstract void addExpense(User u1, Group g1, int amt, Map<Integer, Integer> units);
}

class PercentageSplitStrategy extends SplitStrategy {
    PaymentGraphService pg = new PaymentGraph();
    public void addExpense(User u1, User u2, int amt){
        pg.addExpense(u1, u2, amt);
    }
    public void addExpense(User u1, User u2, int amt, Map<User, Integer> units){
        pg.addExpense(u1, u2, amt * (units.get(u2)/ 100));
    }

    public int getEqualContribution(int noOfUsers) {
        return 100 / noOfUsers;
    }
}

class PaymentGraph {
    List<Ledger> ledger;
    public KafkaTemplate<Long, Ledger> kafkaTemplate;
    public void addExpense(int u1, int u2, int amt) {
        ledger.add(new Ledger(u1, u2, amt));
        kafkaTemplate.send("build-graph-topic", ledger.getLedgerId(), ledger);
    }
    public void removeExpense(Ledger l1) {
        list.remove(ledger);
        kafkaTemplate.send("build-graph-topic", ledger.getLedgerId(), ledger);
    }

    @KafkaListener(topics = "build-graph-topic", consumerId = "build-graph-consumer")
    public void buildGraph(Ledger ledger) {
        try {
            buildGraphService.build(ledger);
        } catch (Exception ee) {
            handleRetry(ledger);
        }
    }

    public void handleRetry(Ledger ledger){
        kafkaTemplate.send("build-graph-topic", ledger.getLedgerId(), ledger);
    }
}
