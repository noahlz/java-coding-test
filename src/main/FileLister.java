import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * FileLister is a utility class and should not be instantiated.
 */
public class FileLister {

    /**
     * Returns a list of all files contained in the specified directory and all
     * of its subdirectories, if any.  The list will not contain any File
     * objects representing directories.  This behavior is different from
     * {@link File#listFiles()}, which returns files and directories only
     * one level down from the root.
     */
    public static List<File> findFiles(File rootDir)
    throws FileNotFoundException, IllegalArgumentException {
        return null;
    }

}
