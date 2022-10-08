public class FileSystemService {

    public void doImplement() {

        User user = new User();

        RootFileSystem root = new RootFileSystem();

        File f1 = new File("f1.txt");
        Folder d1 = new Folder("ABC");
        File f2 = new File("f2.txt");
        File f3 = new File("f3.txt");

        List<File> files = new ArrayList<>();
        files.add(f2);
        files.add(f3);
        d1.add(files);

        root.add(user, f1);
        root.add(user, d1);

        // Adding 2 new file in Folder ABC
        File f4 = new File("f4.txt");
        File f5 = new File("f5.txt");

        root.getFolders("ABC").add(user, f4);
        
        root.getFolders("ABC").update(user, f5, "f4.txt");
        
        root.getFolders("ABC").remove(user, f5);

    }
}