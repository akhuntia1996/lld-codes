public class User {

    private int id;
    private String name;

    private UserRole userRole;

    public User() {
        this.userRole = UserRole.CUSTOMER;
    }
}