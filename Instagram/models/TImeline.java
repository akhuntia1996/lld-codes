public class Timeline {

    private List<Post> userTimeline;
    private List<Post> homeTimeline;

    public void addPost(User user, Post post){
        // This is Step 2 
        // Step 1 is to add the post to the database

        // For Celebrity Users, we will only add to user timeline (PULL Architecture) 
        // When the user comes only, they will PULL the posts.

        // For Normal Users, we will PUSH to all the followers Home time lines.
        // This is also called Observer Pattern
        // PUSH Architecture

        if(user.getAccountType() == AccountType.CELIBRITY)
            userTimeline.add(post);
        else{

            // Observer Design Pattern
            List<User> followers = user.getFollower().getFollowers();
            for(User tempUser : followers){
                tempUser.getHomeTimeline().add(post);
            }

        }
    }

    public void removePost(User user, Post post){
        userTimeline.remove(post);
    }

    public List<Post> getHomeTimeline(User user){

        // home time line will already have all the post of Normal users
        // we need to add the posts of Celibrity users.
        
        List<User> following = user.getFollower().getFollowing();
        for(User tempUser : following){
            if(user.getAccountType() == AccountType.CELIBRITY){
                
                // get all the post that are not fetched (Based on time)
                List<Post> leftOverPosts;
                homeTimeline.addAll(leftOverPosts);

            }
        }

        return homeTimeline;

    }

}