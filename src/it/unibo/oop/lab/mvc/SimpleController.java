package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple implementation of the {@link Controller} interface.
 *
 */
public class SimpleController implements Controller {

    private String current;
    private final List<String> history;

    /**
     * Builds a new {@link SimpleController}.
     */
    public SimpleController() {
        this.current = null;
        this.history = new ArrayList<>();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setCurrentString(final String s) {
        if (s == null) {
            final IllegalArgumentException e = new IllegalArgumentException(s);
            throw e;
        } else {
            this.current = s;
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getCurrentString() {
        return this.current;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public List<String> getHistory() {
        return new ArrayList<>(this.history);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void printCurrentString() {
        System.out.println(this.current);
        this.history.add(current);
        this.current = null;
    }

}
