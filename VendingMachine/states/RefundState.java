public class RefundState extends State {

    @Override
    public State refund(User user){
        user.setAmount(0);
        return new IdleState();
    }
}