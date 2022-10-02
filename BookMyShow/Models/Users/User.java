class User {
    
    private int id;
    private String name;
    private String email;
    private String password;

    private UserRole userRole;

    public User(){}
    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public List<Movie> fetchMovies(String city){
        // check from the database 
        return movieRepoistory
    }

    // Getter and Setters
}