package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String HOMEDIR = System.getProperty("user.home");
    private static final String DEFAULT = "output.txt";
    private File file;
    private String filePath;
    private PrintStream printStream;
    /**
     * @throws IOException
     */
    public Controller() throws IOException {
        this.file = new File(HOMEDIR + SEPARATOR + DEFAULT);
        this.file.createNewFile();
        this.filePath = this.file.getCanonicalPath();
        this.printStream = new PrintStream(this.filePath);
    }
    /**
     * @param file File to be set as current
     * @throws IOException 
     */
    public void setCurrentFile(final File file) throws IOException {
        this.file = file;
        this.filePath = this.file.getCanonicalPath();
        this.printStream = new PrintStream(this.filePath);
    }
    /**
     * @return current file
     */
    public File getCurrentFile() {
        return this.file;
    }
    /**
     * @return path of current file in String form
     */
    public String getCurrentFilePath() {
        return this.filePath;
    }
    /**
     * @param s String to be written in current file
     */
    public void writeString(final String s) {
        this.printStream.println(s);
    }
}
