public class VendingMachineService {

    List<Product> products;

    public VendingMachineService(){
        // intialize products 
    }

    public void startProcessing(){

        User user;
        State currentState = new IdleState();

        // 1. Insert Money 
        int amount;
        currentState = currentState.insertMoney(user, amount);

        // 2. Choose Product
        int productCode;
        currentState = currentState.validateInputProduct(products, productCode);

        // 3. Check Vending Machine state
        // If HasBalance State then dispenseProduct
        // If HasMoney State then go for refund or choose another product
        if(currentState instanceof HasBalanceState){
            currentState = currentState.dispenseProduct(products, productCode);
        } else {
            
            // 4. Refund 
            currentState = currentState.refund(user);

            // Or Go to step 2 - depending on the user decision
        }

        
    }
}