public class File extends RootFileSystem {

    private int size;
    private byte[] contents;

    public File() {
        super.files = null;
        super.folder = null;
    }

}