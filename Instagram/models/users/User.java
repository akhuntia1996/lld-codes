public class User {

    private Account account;
    private DateTime lastOnline;
    private AccountStatus accountStatus;
    private UserRole userRole;

    private List<Status> status;
    
    private Follower followers;
    private Timeline timeLines;

    private List<Message> messages;

    private AccountType accountType;

    public User() {
        this.userRole = UserRole.CUSTOMER;
        this.accountStatus = AccountStatus.ACTIVE;
        this.accountType = AccountType.NORMAL;
    }
    
}