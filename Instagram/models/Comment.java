public class Comment {

    private int id;
    private String text;

    // Should be resticted to for 1 level only...
    private int commentLevel;
    private Comment comment;

    public void addComment(User user, String text){
        if(this.commentLevel > 2)
            return;
        
        this.text = text;
        this.commentLevel++;
    }
}