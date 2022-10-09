public class Follower {
        
    private List<User> followers;
    private List<User> following;

    public void addFollower(User user){
        followers.add(user);
    }

    public void removeFollower(User user){
        followers.remove(user);
    }

    public void addFollowing(User user){
        following.add(user);
    }

    public void removeFollowing(User user){
        following.remove(user);
    }
}