package it.unibo.oop.lab.mvc;

import java.util.List;

/**
 * A controller that prints strings and has memory of the strings it printed.
 */
public interface Controller {

    /*
     * This interface must model a simple controller responsible of I/O access. It
     * considers only the standard output, and it is able to print on it.
     * 
     * Write the interface and implement it in a class in such a way that it
     * includes:
     * 
     * 1) A method for setting the next string to print. Null values are not
     * acceptable, and an exception should be produced
     * 
     * 2) A method for getting the next string to print
     * 
     * 3) A method for getting the history of the printed strings (in form of a List
     * of Strings)
     * 
     * 4) A method that prints the current string. If the current string is unset,
     * an IllegalStateException should be thrown
     * 
     */
    /**
     * @param s String to be set as current.
     * @throws IllegalArgumentException is the {@link String} given as argument is null.
     */
    void setCurrentString(String s);

    /**
     * @return The {@link String} that will be printed next.
     */
    String getCurrentString();

    /**
     * @return The {@link List} of Strings representing all strings previously printed.
     */
    List<String> getHistory();

    /**
     * Prints the current {@link String} to standard output.
     */
    void printCurrentString();

}
