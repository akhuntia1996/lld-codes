
@Service
class UserService {

    List<User> users = new ArrayList<>();

    public UserService() {
        User u1 = new User("ABC", "abc@g.com" ,"12345");
        User u1 = new User("DEF", "def@g.com" ,"12345");
    }

    public User doAuthenticate(String email, String password){
        // Check the user from database.

        for(User u:users){
            if(u.getEmail().equalsIgnoreCase(email) && 
                    u.getPassword().equalsIgnoreCase(password))
                return u;
        }
    }

    public void doBooking(User user, Show show, List<Seat> seats){
        try {
            for(Seat s:seats){
                show.doBooking(user, s);
            }
        } catch(Exception ee){
            ee.printStackTrace();
        }

        System.out.println("Booked Successfully");
    }

    public void cancelBooking(User user, Show show, List<Seat> seats){
        try {
            for(Seat s:seats){
                show.cancelBooking(user, s);
            }
        } catch(Exception ee){
            ee.printStackTrace();
        }

        System.out.println("Cancelled Booking Successfully");
    }

    // CRUD on Users -- only by admin
}