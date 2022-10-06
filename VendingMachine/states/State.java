public abstract class State {

    public State insertMoney(User user, int amount) {
        System.out.println("Not allowed !!");
        return null;
    }

    public State validateInputProduct(User user, List<Product> products, 
            int productCode) {
        System.out.println("Not allowed !!");
        return null;
    }

    public State checkBalance(User user) {
        System.out.println("Not allowed !!");
        return null;
    }

    public State dispenseProduct(List<Product> products, int productCode){
        System.out.println("Not allowed !!");
        return null;
    }

    public State refund(User user){
        System.out.println("Not allowed !!");
        return null;
    }

}