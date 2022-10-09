public class Instagram {

    public static void main(String args[]){
        
        UserService userService = new UserService();

        User user = new User();
        Post post = new Post();

        userService.createPost(user, post);

        List<Post> homeTimeline = userService.getHomeTimeLine(user);
    }
}