public class HasBalanceState extends State {

    @Override
    public State dispenseProduct(List<Product> products, int productCode){
         for(Product product:products){
            if(product.getCode() == productCode){
                products.remove(product);
                break;
            }
        }
        return IdleState();
    }
}