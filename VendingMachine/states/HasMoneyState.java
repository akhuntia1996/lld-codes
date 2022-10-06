public class HasMoneyState extends State {

    @Override 
    public State validateInputProduct(User user, List<Product> products, int productCode) {
        for(Product product:products){
            if(product.getCode() == productCode)
                return checkBalance(user, product.getPrice());
        }
        return new HasMoneyState();
    }

    @Override 
    public State checkBalance(User user, int price){
        if(user.getAmount() == price)
            return new HasBalanceState();
        else{
            if(user.getAmount() < price){
                System.out.println("Insufficent Balance");
                return new HasMoneyState();
            } else{
                System.out.println("Balance Left " + user.getAmount() - price );
                user.setAmount(user.getAmount() - price);
                return new HasBalanceState();
            }
        }
    }
}