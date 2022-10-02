
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
}