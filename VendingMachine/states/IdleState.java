public class IdleState extends State {

    @Override
    public State insertMoney(User user, int amount) {
        user.setaAmount(amount);
        return new HasMoneyState();
    }

    @Override
    public State validateInputProduct(User user, List<Product> products, 
            int productCode) {
        
        if(user.getAmount() == 0)
            return IdleState();
        
        State newState = new HashMoneyState();

        return newState.validateInputProduct(user, products, productCode);
    }
}