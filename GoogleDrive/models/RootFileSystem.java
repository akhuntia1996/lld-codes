public abstract class RootFileSystem {

    private int id;
    private String name;
    private List<Folder> folders;
    private List<File> files;

    public RootFileSystem(){}

    public void add(User user, File file){
        files.add(file);
    }

    public void add(User user, Folder folder){
        folders.add(folder);
    }

    public void remove(User user, File file){
        files.remove(file);
    }

    public void remove(User user, Folder folder){
        folders.remove(folder);
    }

    public void update(User user, File file, int oldFilename){
        File oldFile = null;

        for(File file:files){
            if(file.getName().equals(oldFileName))
                oldFile = file;
        }
        this.remove(oldFile);

        oldFile.setContents(file.getContents());
        oldFile.setSize(file.getSize());

        this.add(oldFile);
    }

    public void update(User user, Folder folder, int oldFolderName){
        Folder oldFolder = null;

        for(Folder folder : folders){
            if(folder.getName().equals(oldFolderName))
                oldFolder = folder;
        }
        this.remove(oldFolder);

        oldFolder.setNumberOfFiles(oldFolder.getNumberOfFile());
        oldFolder.setFiles(folder.getFile());

        this.add(oldFolder);
    }

}