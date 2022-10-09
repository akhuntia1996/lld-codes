// Facade Design Pattern

public class UserService {

    public void createPost(User user, Post post){
        user.getTimeline().getUserTimeline().add(post);
    }

    // Methods for like share and comment

    public void getHomeTimeline(User user){
        user.getTimeline().getHomeTimeline();
    }

    public Account getAccountDetails(User user){
        return user.getAccount();
    }

    public void EditAccount(){}
}