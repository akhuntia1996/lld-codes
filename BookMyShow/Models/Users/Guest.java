public class Guest extends User {

    public Guest(){
        super();
        this.userRole = UserRole.GUEST;
    }
}